// Require package
var fs = require('fs');
var validate = require('../static/js/libs/json/schema/lib/validate.js').validate;
var http = require('http');

// Dictionary of json-schemas
var schemaDictionary = {};

// Path to schemas / directories
var realSchemaDirPath = fs.realpathSync("../static/json/schema/");
var schemaDirPath = "../static/json/schema/";
var schemaDir = fs.readdirSync(schemaDirPath);

// Populate schemaDictionary with schemas
for (var file in schemaDir) {
    var contents = fs.readFileSync(realSchemaDirPath + '/' + schemaDir[file],
                                   "UTF-8");
    var key = schemaDir[file].toLowerCase();
    console.log(key);
//    validateSchema(contents);
    schemaDictionary[key] = eval('(' + contents + ')');

}

function getTypeFromContentType(contentType) {

    console.log(contentType);
    var entityTypeRegex = /application\/(.+)\+json.*/;

    var match = entityTypeRegex.exec(contentType);
    return match[1].toLowerCase();
}

//function validateSchema(schema) {
//    var contents = fs.readFileSync("schema", "UTF-8");
//    console.log(contents);
//    console.log(schema);
//    var metaSchema = eval('(' + contents + ')');
//
//    var result = validate(schema, metaSchema);
//    if(!result.valid) {
//        console.log("invalid schema");
//        for(error in result.errors) {
//            console.log(JSON.stringify(result.errors[error]));
//        }
//        throw "Invalid Schema";
//    }
//}


http.createServer(
    function (req, res) {

        var content = "";

        req.addListener("data", function (chunk) {
            content += chunk.toString();
        });

        req.addListener("end", function () {
            var json;

            // Attempt parse, reject if invalid json
            try {
                json = JSON.parse(content);
            } catch (err) {
                res.writeHead(400, {});
                res.end();
            }

            // If no entity is passed - return 400
            if (json === undefined) {
                res.writeHead(400, {});
                res.end();
                return;
            }


            // Get appropriate schema
            var schemaName = getTypeFromContentType(req.headers["content-type"]);



            var schema = schemaDictionary[schemaName];
            console.log(schemaName);
            console.log(schema);

            // If schema is not defined, return unsupported
            if (schema === undefined) {
                console.log("Schema undefined!");
                res.writeHead(415, {'Content-type':'application/json'});
                res.end();
                return;
            }

            var report = validate(json, schema);

            // If entity is valid, send success
            if (report.errors.length === 0) {
                console.log("Valid");
                res.writeHead(200, {'Content-type':'application/json'});
                res.end();
            } else {
                console.log("Invalid");
                // Else, send errors
                res.writeHead(422, {'Content-type':'application/json'});
                res.end(JSON.stringify(report.errors), "UTF-8");
            }


        });
    }).listen(3333, "127.0.0.1");
console.log('Server running at http://127.0.0.1:3333');