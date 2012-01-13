//package todo.json.schema;
//
//import org.springframework.core.io.Resource;
//
//import java.io.File;
//import java.io.IOException;
//
///**
// * User: Will O'Brien
// * Date: 1/9/12
// * Time: 2:55 PM
// */
//public class JSONSchema {
//
//    private final String name;
//    private final File jsonSchemaReader;
//
//    public JSONSchema(String name, Resource jsonSchemaResource) throws
//                                                            IOException {
//        this.name = name;
//
//        this.jsonSchemaReader = jsonSchemaResource.getFile();
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public File getJsonSchemaFile() {
//        return jsonSchemaReader;
//    }
//}
