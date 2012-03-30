Ext.define('BSMobile.data.writer.JsonSchemaWriter', {

    jsonSchema: undefined,

    extend: 'Ext.data.writer.Json',
    alias: 'writer.jsonschemawriter',

    write: function(request) {

        if(this.jsonSchema == undefined) {
            this.jsonSchema = request._headers['Content-Type'];
        }

        if(request._method === "GET" || request._method === "DELETE") {
            request._headers['Content-Type'] = "application/json";
        }

        if(request._method === "PUT" || request._method === "POST") {
            request._headers['Content-Type'] = this.jsonSchema;
        }

        return this.superclass.write.apply(this, [request]);

    }


});