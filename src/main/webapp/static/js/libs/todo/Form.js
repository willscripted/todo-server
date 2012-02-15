dojo.provide('todo.Form');

require(['dojo/_base/declare', 'dojo/dom-form'], function (declare) {
    declare("todo.Form", null, {
        constructor:function (/*Object*/ args) {

            this._id = args['formId'];
            this.formHandle = $("form#" + this._id);

            // Return a new form object
            this.method = "POST";

            var location = this.formHandle.attr("json-schema");
            this.getSchema(location);

            this._validators = {};
            this._responseHandlers = {};
            this.contentType = "application/json";

            var handler = {
                todoForm:this,

                handle:function handle(event) {
                    dojo.stopEvent(event);
                    if (this.todoForm._validate()) {
                        this.todoForm._send();
                    }
                }
            };


            dojo.connect(dojo.byId(this._id), "onsubmit", function (event) {
                handler.handle(event);
            });
        },

        setContentType:function (type) {
            this.contentType = type;
        },

        setCustomValidation:function (validators) {
            this._validators = validators;
        },

        setMethod:function (method) {
            this.method = method;
        },

        setResponseHandler:function (handlers) {
            this._responseHandlers = handlers;
        },

        _validate:function () {
            this._clearErrors();

            var isValid = true;

            isValid = isValid && this._jsonSchemaValidation();

            isValid = isValid && this._customValidation();

            return isValid;
        },

        _customValidation:function () {
            var isValid = true;
            var instance = dojo.formToJson(dojo.byId(this._id));

            for (v in this._validators) {
                if (!this._validators[v].test(instance)) {
                    this._setError(this._validators[v].error);
                    isValid = false;
                }
            }

            return isValid;
        },

        _jsonSchemaValidation:function () {

            var instance = dojo.formToJson(dojo.byId(this._id));
            var errors =
                json.schema.lib.validate(JSON.parse(instance),
                                           this._schema).errors;

            for (error in errors) {
                this._setError(errors[error]);
            }

            if (errors.length === 0) {
                return true;
            }
        },

        _setError:function (error) {
            var errorDiv = $("#" + error.property).siblings("div.errors");

            if(errorDiv.length === 0) {
                console.log("Undisplayed Error - Property: " + error.property + " Message: " + error.message);
            }

            errorDiv.text(error.message);
        },

        _clearErrors:function () {
            $(".errors").text("");
        },

        getURI:function () {
            return this.formHandle.attr("action");
        },

        _send:function () {
            $.ajax(this.getURI(), {
                data:dojo.formToJson(dojo.byId(this._id)),
                contentType:this.contentType,
                type:this.method,
                statusCode:this._responseHandlers
            });
        },

        getSchema:function (location) {

            var handler = {
                form:this,
                handle:function (data) {
                    this.form._schema = data;
                }
            };

            $.ajax(location, {
                dataType:"json",
                success:function (data) {
                    handler.handle(data)
                }
            });
        }

    });

});
