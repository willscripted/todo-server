package todo.json.schema;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.ServletContextResource;
import sun.net.idn.StringPrep;

import javax.script.Bindings;
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

/**
 * User: Will O'Brien
 * Date: 1/10/12
 * Time: 9:40 AM
 */
class JsSchemaFactory {

    private FileReader script;
    private final ScriptEngine scriptEngine;

    JsSchemaFactory() {
        {
            ScriptEngineManager engineManager = new ScriptEngineManager();
            scriptEngine = engineManager.getEngineByName("js");
        }
        {
            URL url = JsSchemaFactory.class.getResource("jsSchemaCompiler.js");
            
            File file = FileUtils.toFile(url);
            
            try {
                script = new FileReader(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    Object getJsSchema(File schema) throws IOException {

        String schemaScript = FileUtils.readFileToString(schema);

        scriptEngine.put("schema",
                         schemaScript);
        scriptEngine.put("compiled",
                         null);

        try {
            scriptEngine.eval(script);
        } catch (ScriptException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid schema");
        }

        return scriptEngine.get("compiled");
    }


    public static void main(String[] args) throws IOException, ScriptException {


        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = engineManager.getEngineByName("js");

        FileReader reader = new FileReader("/home/will/In Progress/Honors "
                                           + "Project/Server "
                                           +
                                           "App/src/java/todo/json/schema/jsSchemaCompiler.js");

        String userSchema = FileUtils.readFileToString(new File
                                                       ("/home/will/In "
                                                        + "Progress/Honors "
                                                        + "Project/Server "
                                                        + "App/web/WEB-INF/user.json"));

        scriptEngine.put("schema",
                         userSchema);
        scriptEngine.put("compiled",
                         null);

        scriptEngine.eval(reader);

        System.out
                .println(scriptEngine.get("compiled"));

    }

}
