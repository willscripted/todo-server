Ext.define('BS.data.proxy.JsonSchemaRest', {

    extend:'Ext.data.proxy.Rest',
    alias:'proxy.JsonSchemaRest',

    buildRequest:function (operation) {

        // Save schema type
        if(this.jsonSchema == undefined) {
            this.jsonSchema = this.headers['Content-Type'];
        }

        var request = this.superclass.buildRequest.apply(this, arguments);
        var headers = this.headers;

        // Apply appropriate content-type for target action
        var action = operation['action'];
        if (action == "read" || action == "destroy") {
            Ext.apply(headers, {
                'Content-Type':'application/json'
            });
        } else if(action == "update" || action == "create") {
            Ext.apply(headers, {
                'Content-Type': this.jsonSchema
            });
        }

        request.headers = headers;

        return request;
    },


    doRequest:function (operation, callback, scope) {
        var writer = this.getWriter(),
            request = this.buildRequest(operation, callback, scope);

        if (operation.allowWrite()) {
            request = writer.write(request);
        }

        Ext.apply(request, {
            timeout:this.timeout,
            scope:this,
            callback:this.createRequestCallback(request, operation, callback,
                                                scope),
            method:this.getMethod(request),
            disableCaching:false // explicitly set it to false, ServerProxy handles caching
        });

        Ext.Ajax.request(request);

        return request;
    }

});