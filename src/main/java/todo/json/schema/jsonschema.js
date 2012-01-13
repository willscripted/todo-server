/*
 http://www.JSON.org/json2.js
 2011-10-19

 Public Domain.

 NO WARRANTY EXPRESSED OR IMPLIED. USE AT YOUR OWN RISK.

 See http://www.JSON.org/js.html


 This code should be minified before deployment.
 See http://javascript.crockford.com/jsmin.html

 USE YOUR OWN COPY. IT IS EXTREMELY UNWISE TO LOAD CODE FROM SERVERS YOU DO
 NOT CONTROL.


 This file creates a global JSON object containing two methods: stringify
 and parse.

 JSON.stringify(value, replacer, space)
 value       any JavaScript value, usually an object or array.

 replacer    an optional parameter that determines how object
 values are stringified for objects. It can be a
 function or an array of strings.

 space       an optional parameter that specifies the indentation
 of nested structures. If it is omitted, the text will
 be packed without extra whitespace. If it is a number,
 it will specify the number of spaces to indent at each
 level. If it is a string (such as '\t' or '&nbsp;'),
 it contains the characters used to indent at each level.

 This method produces a JSON text from a JavaScript value.

 When an object value is found, if the object contains a toJSON
 method, its toJSON method will be called and the result will be
 stringified. A toJSON method does not serialize: it returns the
 value represented by the name/value pair that should be serialized,
 or undefined if nothing should be serialized. The toJSON method
 will be passed the key associated with the value, and this will be
 bound to the value

 For example, this would serialize Dates as ISO strings.

 Date.prototype.toJSON = function (key) {
 function f(n) {
 // Format integers to have at least two digits.
 return n < 10 ? '0' + n : n;
 }

 return this.getUTCFullYear()   + '-' +
 f(this.getUTCMonth() + 1) + '-' +
 f(this.getUTCDate())      + 'T' +
 f(this.getUTCHours())     + ':' +
 f(this.getUTCMinutes())   + ':' +
 f(this.getUTCSeconds())   + 'Z';
 };

 You can provide an optional replacer method. It will be passed the
 key and value of each member, with this bound to the containing
 object. The value that is returned from your method will be
 serialized. If your method returns undefined, then the member will
 be excluded from the serialization.

 If the replacer parameter is an array of strings, then it will be
 used to select the members to be serialized. It filters the results
 such that only members with keys listed in the replacer array are
 stringified.

 Values that do not have JSON representations, such as undefined or
 functions, will not be serialized. Such values in objects will be
 dropped; in arrays they will be replaced with null. You can use
 a replacer function to replace those with JSON values.
 JSON.stringify(undefined) returns undefined.

 The optional space parameter produces a stringification of the
 value that is filled with line breaks and indentation to make it
 easier to read.

 If the space parameter is a non-empty string, then that string will
 be used for indentation. If the space parameter is a number, then
 the indentation will be that many spaces.

 Example:

 text = JSON.stringify(['e', {pluribus: 'unum'}]);
 // text is '["e",{"pluribus":"unum"}]'


 text = JSON.stringify(['e', {pluribus: 'unum'}], null, '\t');
 // text is '[\n\t"e",\n\t{\n\t\t"pluribus": "unum"\n\t}\n]'

 text = JSON.stringify([new Date()], function (key, value) {
 return this[key] instanceof Date ?
 'Date(' + this[key] + ')' : value;
 });
 // text is '["Date(---current time---)"]'


 JSON.parse(text, reviver)
 This method parses a JSON text to produce an object or array.
 It can throw a SyntaxError exception.

 The optional reviver parameter is a function that can filter and
 transform the results. It receives each of the keys and values,
 and its return value is used instead of the original value.
 If it returns what it received, then the structure is not modified.
 If it returns undefined then the member is deleted.

 Example:

 // Parse the text. Values that look like ISO date strings will
 // be converted to Date objects.

 myData = JSON.parse(text, function (key, value) {
 var a;
 if (typeof value === 'string') {
 a =
 /^(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2}(?:\.\d*)?)Z$/.exec(value);
 if (a) {
 return new Date(Date.UTC(+a[1], +a[2] - 1, +a[3], +a[4],
 +a[5], +a[6]));
 }
 }
 return value;
 });

 myData = JSON.parse('["Date(09/09/2001)"]', function (key, value) {
 var d;
 if (typeof value === 'string' &&
 value.slice(0, 5) === 'Date(' &&
 value.slice(-1) === ')') {
 d = new Date(value.slice(5, -1));
 if (d) {
 return d;
 }
 }
 return value;
 });


 This is a reference implementation. You are free to copy, modify, or
 redistribute.
 */

/*jslint evil: true, regexp: true */

/*members "", "\b", "\t", "\n", "\f", "\r", "\"", JSON, "\\", apply,
 call, charCodeAt, getUTCDate, getUTCFullYear, getUTCHours,
 getUTCMinutes, getUTCMonth, getUTCSeconds, hasOwnProperty, join,
 lastIndex, length, parse, prototype, push, replace, slice, stringify,
 test, toJSON, toString, valueOf
 */


// Create a JSON object only if one does not already exist. We create the
// methods in a closure to avoid creating global variables.

var JSON;
if (!JSON) {
    JSON = {};
}

(function () {
    'use strict';

    function f(n) {
        // Format integers to have at least two digits.
        return n < 10 ? '0' + n : n;
    }

    if (typeof Date.prototype.toJSON !== 'function') {

        Date.prototype.toJSON = function (key) {

            return isFinite(this.valueOf())
                ? this.getUTCFullYear() + '-' +
                  f(this.getUTCMonth() + 1) + '-' +
                  f(this.getUTCDate()) + 'T' +
                  f(this.getUTCHours()) + ':' +
                  f(this.getUTCMinutes()) + ':' +
                  f(this.getUTCSeconds()) + 'Z'
                : null;
        };

        String.prototype.toJSON =
        Number.prototype.toJSON =
        Boolean.prototype.toJSON = function (key) {
            return this.valueOf();
        };
    }

    var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        gap,
        indent,
        meta = {    // table of character substitutions
            '\b':'\\b',
            '\t':'\\t',
            '\n':'\\n',
            '\f':'\\f',
            '\r':'\\r',
            '"':'\\"',
            '\\':'\\\\'
        },
        rep;


    function quote(string) {

// If the string contains no control characters, no quote characters, and no
// backslash characters, then we can safely slap some quotes around it.
// Otherwise we must also replace the offending characters with safe escape
// sequences.

        escapable.lastIndex = 0;
        return escapable.test(string) ?
            '"' + string.replace(escapable, function (a) {
                var c = meta[a];
                return typeof c === 'string'
                    ? c
                    : '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
            }) + '"' : '"' + string + '"';
    }


    function str(key, holder) {

// Produce a string from holder[key].

        var i, // The loop counter.
            k, // The member key.
            v, // The member value.
            length,
            mind = gap,
            partial,
            value = holder[key];

// If the value has a toJSON method, call it to obtain a replacement value.

        if (value && typeof value === 'object' &&
            typeof value.toJSON === 'function') {
            value = value.toJSON(key);
        }

// If we were called with a replacer function, then call the replacer to
// obtain a replacement value.

        if (typeof rep === 'function') {
            value = rep.call(holder, key, value);
        }

// What happens next depends on the value's type.

        switch (typeof value) {
            case 'string':
                return quote(value);

            case 'number':

// JSON numbers must be finite. Encode non-finite numbers as null.

                return isFinite(value) ? String(value) : 'null';

            case 'boolean':
            case 'null':

// If the value is a boolean or null, convert it to a string. Note:
// typeof null does not produce 'null'. The case is included here in
// the remote chance that this gets fixed someday.

                return String(value);

// If the type is 'object', we might be dealing with an object or an array or
// null.

            case 'object':

// Due to a specification blunder in ECMAScript, typeof null is 'object',
// so watch out for that case.

                if (!value) {
                    return 'null';
                }

// Make an array to hold the partial results of stringifying this object value.

                gap += indent;
                partial = [];

// Is the value an array?

                if (Object.prototype.toString.apply(value) === '[object Array]') {

// The value is an array. Stringify every element. Use null as a placeholder
// for non-JSON values.

                    length = value.length;
                    for (i = 0; i < length; i += 1) {
                        partial[i] = str(i, value) || 'null';
                    }

// Join all of the elements together, separated with commas, and wrap them in
// brackets.

                    v = partial.length === 0
                        ? '[]'
                        : gap
                        ?
                        '[\n' + gap + partial.join(',\n' + gap) + '\n' + mind + ']'
                        : '[' + partial.join(',') + ']';
                    gap = mind;
                    return v;
                }

// If the replacer is an array, use it to select the members to be stringified.

                if (rep && typeof rep === 'object') {
                    length = rep.length;
                    for (i = 0; i < length; i += 1) {
                        if (typeof rep[i] === 'string') {
                            k = rep[i];
                            v = str(k, value);
                            if (v) {
                                partial.push(quote(k) + (gap ? ': ' : ':') + v);
                            }
                        }
                    }
                } else {

// Otherwise, iterate through all of the keys in the object.

                    for (k in value) {
                        if (Object.prototype.hasOwnProperty.call(value, k)) {
                            v = str(k, value);
                            if (v) {
                                partial.push(quote(k) + (gap ? ': ' : ':') + v);
                            }
                        }
                    }
                }

// Join all of the member texts together, separated with commas,
// and wrap them in braces.

                v = partial.length === 0
                    ? '{}'
                    : gap
                    ?
                    '{\n' + gap + partial.join(',\n' + gap) + '\n' + mind + '}'
                    : '{' + partial.join(',') + '}';
                gap = mind;
                return v;
        }
    }

// If the JSON object does not yet have a stringify method, give it one.

    if (typeof JSON.stringify !== 'function') {
        JSON.stringify = function (value, replacer, space) {

// The stringify method takes a value and an optional replacer, and an optional
// space parameter, and returns a JSON text. The replacer can be a function
// that can replace values, or an array of strings that will select the keys.
// A default replacer method can be provided. Use of the space parameter can
// produce text that is more easily readable.

            var i;
            gap = '';
            indent = '';

// If the space parameter is a number, make an indent string containing that
// many spaces.

            if (typeof space === 'number') {
                for (i = 0; i < space; i += 1) {
                    indent += ' ';
                }

// If the space parameter is a string, it will be used as the indent string.

            } else if (typeof space === 'string') {
                indent = space;
            }

// If there is a replacer, it must be a function or an array.
// Otherwise, throw an error.

            rep = replacer;
            if (replacer && typeof replacer !== 'function' &&
                (typeof replacer !== 'object' ||
                 typeof replacer.length !== 'number')) {
                throw new Error('JSON.stringify');
            }

// Make a fake root object containing our value under the key of ''.
// Return the result of stringifying the value.

            return str('', {'':value});
        };
    }


// If the JSON object does not yet have a parse method, give it one.

    if (typeof JSON.parse !== 'function') {
        JSON.parse = function (text, reviver) {

// The parse method takes a text and an optional reviver function, and returns
// a JavaScript value if the text is a valid JSON text.

            var j;

            function walk(holder, key) {

// The walk method is used to recursively walk the resulting structure so
// that modifications can be made.

                var k, v, value = holder[key];
                if (value && typeof value === 'object') {
                    for (k in value) {
                        if (Object.prototype.hasOwnProperty.call(value, k)) {
                            v = walk(value, k);
                            if (v !== undefined) {
                                value[k] = v;
                            } else {
                                delete value[k];
                            }
                        }
                    }
                }
                return reviver.call(holder, key, value);
            }


// Parsing happens in four stages. In the first stage, we replace certain
// Unicode characters with escape sequences. JavaScript handles many characters
// incorrectly, either silently deleting them, or treating them as line endings.

            text = String(text);
            cx.lastIndex = 0;
            if (cx.test(text)) {
                text = text.replace(cx, function (a) {
                    return '\\u' +
                           ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
                });
            }

// In the second stage, we run the text against regular expressions that look
// for non-JSON patterns. We are especially concerned with '()' and 'new'
// because they can cause invocation, and '=' because it can cause mutation.
// But just to be safe, we want to reject all unexpected forms.

// We split the second stage into 4 regexp operations in order to work around
// crippling inefficiencies in IE's and Safari's regexp engines. First we
// replace the JSON backslash pairs with '@' (a non-JSON character). Second, we
// replace all simple value tokens with ']' characters. Third, we delete all
// open brackets that follow a colon or comma or that begin the text. Finally,
// we look to see that the remaining characters are only whitespace or ']' or
// ',' or ':' or '{' or '}'. If that is so, then the text is safe for eval.

            if (/^[\],:{}\s]*$/
                .test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@')
                          .replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,
                                   ']')
                          .replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {

// In the third stage we use the eval function to compile the text into a
// JavaScript structure. The '{' operator is subject to a syntactic ambiguity
// in JavaScript: it can begin a block or an object literal. We wrap the text
// in parens to eliminate the ambiguity.

                j = eval('(' + text + ')');

// In the optional fourth stage, we recursively walk the new structure, passing
// each name/value pair to a reviver function for possible transformation.

                return typeof reviver === 'function'
                    ? walk({'':j}, '')
                    : j;
            }

// If the text is not JSON parseable, then a SyntaxError is thrown.

            throw new SyntaxError('JSON.parse');
        };
    }
}());


/**
 * JSONSchema Validator - Validates JavaScript objects using JSON Schemas
 *    (http://www.json.com/json-schema-proposal/)
 *
 * Copyright (c) 2007 Kris Zyp SitePen (www.sitepen.com)
 * Licensed under the MIT (MIT-LICENSE.txt) license.
 To use the validator call JSONSchema.validate with an instance object and an optional schema object.
 If a schema is provided, it will be used to validate. If the instance object refers to a schema (self-validating),
 that schema will be used to validate and the schema parameter is not necessary (if both exist,
 both validations will occur).
 The validate method will return an array of validation errors. If there are no errors, then an
 empty list will be returned. A validation error will have two properties:
 "property" which indicates which property had the error
 "message" which indicates what the error was
 */

JSONSchema = {
    validate:function (/*Any*/instance, /*Object*/schema) {
        // Summary:
        //  	To use the validator call JSONSchema.validate with an instance object and an optional schema object.
        // 		If a schema is provided, it will be used to validate. If the instance object refers to a schema (self-validating),
        // 		that schema will be used to validate and the schema parameter is not necessary (if both exist,
        // 		both validations will occur).
        // 		The validate method will return an object with two properties:
        // 			valid: A boolean indicating if the instance is valid by the schema
        // 			errors: An array of validation errors. If there are no errors, then an
        // 					empty list will be returned. A validation error will have two properties:
        // 						property: which indicates which property had the error
        // 						message: which indicates what the error was
        //
        return this._validate(instance, schema, false);
    },
    checkPropertyChange:function (/*Any*/value, /*Object*/schema, /*String*/
                                  property) {
        // Summary:
        // 		The checkPropertyChange method will check to see if an value can legally be in property with the given schema
        // 		This is slightly different than the validate method in that it will fail if the schema is readonly and it will
        // 		not check for self-validation, it is assumed that the passed in value is already internally valid.
        // 		The checkPropertyChange method will return the same object type as validate, see JSONSchema.validate for
        // 		information.
        //
        return this._validate(value, schema, property || "property");
    },
    _validate:function (/*Any*/instance, /*Object*/schema, /*Boolean*/
                        _changing) {

        var errors = [];
        // validate a value against a property definition
        function checkProp(value, schema, path, i) {
            var l;
            path += path ? typeof i == 'number' ? '[' + i + ']' :
                typeof i == 'undefined' ? '' : '.' + i : i;
            function addError(message) {
                errors.push({property:path, message:message});
            }

            if ((typeof schema != 'object' || schema instanceof Array) && (path || typeof schema != 'function')) {
                if (typeof schema == 'function') {
                    if (!(value instanceof schema)) {
                        addError("is not an instance of the class/constructor " + schema.name);
                    }
                } else if (schema) {
                    addError("Invalid schema/property definition " + schema);
                }
                return null;
            }
            if (_changing && schema.readonly) {
                addError("is a readonly field, it can not be changed");
            }
            if (schema['extends']) { // if it extends another schema, it must pass that schema as well
                checkProp(value, schema['extends'], path, i);
            }
            // validate a value against a type definition
            function checkType(type, value) {
                if (type) {
                    if (typeof type == 'string' && type != 'any' &&
                        (type == 'null' ? value !== null :
                            typeof value != type) &&
                        !(value instanceof Array && type == 'array') &&
                        !(type == 'integer' && value % 1 === 0)) {
                        return [
                            {property:path, message:(typeof value) + " value found, but a " + type + " is required"}
                        ];
                    }
                    if (type instanceof Array) {
                        var unionErrors = [];
                        for (var j = 0; j < type.length; j++) { // a union type
                            if (!(unionErrors = checkType(type[j],
                                                          value)).length) {
                                break;
                            }
                        }
                        if (unionErrors.length) {
                            return unionErrors;
                        }
                    } else if (typeof type == 'object') {
                        var priorErrors = errors;
                        errors = [];
                        checkProp(value, type, path);
                        var theseErrors = errors;
                        errors = priorErrors;
                        return theseErrors;
                    }
                }
                return [];
            }

            if (value === undefined) {
                if (!schema.optional) {
                    addError("is missing and it is not optional");
                }
            } else {
                errors = errors.concat(checkType(schema.type, value));
                if (schema.disallow && !checkType(schema.disallow,
                                                  value).length) {
                    addError(" disallowed value was matched");
                }
                if (value !== null) {
                    if (value instanceof Array) {
                        if (schema.items) {
                            if (schema.items instanceof Array) {
                                for (i = 0, l = value.length; i < l; i++) {
                                    errors.concat(checkProp(value[i],
                                                            schema.items[i],
                                                            path, i));
                                }
                            } else {
                                for (i = 0, l = value.length; i < l; i++) {
                                    errors.concat(checkProp(value[i],
                                                            schema.items, path,
                                                            i));
                                }
                            }
                        }
                        if (schema.minItems && value.length < schema.minItems) {
                            addError("There must be a minimum of " + schema.minItems + " in the array");
                        }
                        if (schema.maxItems && value.length > schema.maxItems) {
                            addError("There must be a maximum of " + schema.maxItems + " in the array");
                        }
                    } else if (schema.properties) {
                        errors.concat(checkObj(value, schema.properties, path,
                                               schema.additionalProperties));
                    }
                    if (schema.pattern && typeof value == 'string' && !value.match(schema.pattern)) {
                        addError("does not match the regex pattern " + schema.pattern);
                    }
                    if (schema.maxLength && typeof value == 'string' && value.length > schema.maxLength) {
                        addError("may only be " + schema.maxLength + " characters long");
                    }
                    if (schema.minLength && typeof value == 'string' && value.length < schema.minLength) {
                        addError("must be at least " + schema.minLength + " characters long");
                    }
                    if (typeof schema.minimum !== undefined && typeof value == typeof schema.minimum &&
                        schema.minimum > value) {
                        addError("must have a minimum value of " + schema.minimum);
                    }
                    if (typeof schema.maximum !== undefined && typeof value == typeof schema.maximum &&
                        schema.maximum < value) {
                        addError("must have a maximum value of " + schema.maximum);
                    }
                    if (schema['enum']) {
                        var enumer = schema['enum'];
                        l = enumer.length;
                        var found;
                        for (var j = 0; j < l; j++) {
                            if (enumer[j] === value) {
                                found = 1;
                                break;
                            }
                        }
                        if (!found) {
                            addError("does not have a value in the enumeration " + enumer.join(", "));
                        }
                    }
                    if (typeof schema.maxDecimal == 'number' &&
                        (value.toString().match(new RegExp("\\.[0-9]{" + (schema.maxDecimal + 1) + ",}")))) {
                        addError("may only have " + schema.maxDecimal + " digits of decimal places");
                    }
                }
            }
            return null;
        }

        // validate an object against a schema
        function checkObj(instance, objTypeDef, path, additionalProp) {

            if (typeof objTypeDef == 'object') {
                if (typeof instance != 'object' || instance instanceof Array) {
                    errors.push({property:path, message:"an object is required"});
                }

                for (var i in objTypeDef) {
                    if (objTypeDef.hasOwnProperty(i) && !(i.charAt(0) == '_' && i.charAt(1) == '_')) {
                        var value = instance[i];
                        var propDef = objTypeDef[i];
                        checkProp(value, propDef, path, i);
                    }
                }
            }
            for (i in instance) {
                if (instance.hasOwnProperty(i) && !(i.charAt(0) == '_' && i.charAt(1) == '_') && objTypeDef && !objTypeDef[i] && additionalProp === false) {
                    errors.push({property:path, message:(typeof value) + "The property " + i +
                                                        " is not defined in the schema and the schema does not allow additional properties"});
                }
                var requires = objTypeDef && objTypeDef[i] && objTypeDef[i].requires;
                if (requires && !(requires in instance)) {
                    errors.push({property:path, message:"the presence of the property " + i + " requires that " + requires + " also be present"});
                }
                value = instance[i];
                if (objTypeDef && typeof objTypeDef == 'object' && !(i in objTypeDef)) {
                    checkProp(value, additionalProp, path, i);
                }
                if (!_changing && value && value.$schema) {
                    errors = errors.concat(checkProp(value, value.$schema, path,
                                                     i));
                }
            }
            return errors;
        }

        if (schema) {
            checkProp(instance, schema, '', _changing || '');
        }
        if (!_changing && instance && instance.$schema) {
            checkProp(instance, instance.$schema, '', '');
        }
        return {valid:!errors.length, errors:errors};
    }
    /* will add this later
     newFromSchema : function() {
     }
     */
}

var comp_instance = JSON.parse(instance);
var comp_schema = schema;

results = JSONSchema.validate(comp_instance, comp_schema);
valid = results.valid;
errors = JSON.stringify(results.errors);


