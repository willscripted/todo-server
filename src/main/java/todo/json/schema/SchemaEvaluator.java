package todo.json.schema;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Will O'Brien
 * Date: 1/10/12
 * Time: 9:35 AM
 */
class SchemaEvaluator {

    private final CompiledScript compiledScript;
    private final Map<String, Object> schemas;
    private final JsSchemaFactory schemaFactory;

    SchemaEvaluator() {

        compiledScript = initValidationScript();

        schemas = new HashMap<String, Object>();
        schemaFactory = new JsSchemaFactory();

    }

    private CompiledScript initValidationScript() {
        // Get script engine
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = engineManager.getEngineByName("js");

        //cast to Compilable engine, this is safe for Rhino
        Compilable c = (Compilable) scriptEngine;

        // Retrieve script
        Reader in = getSchemaEvalScript();

        // Return script after compiling
        CompiledScript script = null;
        try {
            script = c.compile(in);    //compile
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return script;
    }

    String eval(String json, String schemaName) {
        Object schemaObj = schemas.get(schemaName);

        Bindings bindings = initBindings(json,
                                         schemaObj);

        try {
            compiledScript.eval(bindings);
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        return (String) bindings.get("errors");
    }

    private Bindings initBindings(String json, Object schemaObj) {
        Bindings bindings = compiledScript.getEngine().createBindings();
        bindings.put("schema",
                     schemaObj);
        bindings.put("instance",
                     json);
        bindings.put("errors",
                     null);
        bindings.put("valid",
                     null);
        return bindings;
    }


    private Reader getSchemaEvalScript() {
        Reader in = null;

        URL url = SchemaEvaluator.class.getResource("jsonschema.js");

        File file = FileUtils.toFile(url);

        try {
            in = new FileReader(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return in;
    }

    boolean hasSchema(String schemaName) {
        return schemas.containsKey(schemaName);
    }

    void addSchema(String name, File schema) {
        Object jsSchemaObject = null;
        try {
            jsSchemaObject = schemaFactory.getJsSchema(schema);
            schemas.put(name,
                        jsSchemaObject);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) throws IOException {

    }

}
