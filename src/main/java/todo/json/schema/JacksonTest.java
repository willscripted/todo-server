package todo.json.schema;

import eu.vahlas.json.schema.JSONSchema;
import eu.vahlas.json.schema.JSONSchemaProvider;
import eu.vahlas.json.schema.impl.JacksonSchemaProvider;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.impl.JsonGeneratorBase;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.JsonGeneratorDelegate;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import todo.hibernate.entities.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: Will O'Brien
 * Date: 1/11/12
 * Time: 6:25 PM
 */
public class JacksonTest {
    
    
    public static void main(String[] args) throws IOException {

        MappingJacksonHttpMessageConverter jacksonMain = new
                MappingJacksonHttpMessageConverter();



        ObjectMapper mapper = new ObjectMapper();
        

        
        JSONSchemaProvider schemaProvider = new JacksonSchemaProvider(mapper);
        
        InputStream schemaIS = new FileInputStream("schema.json");
        JSONSchema schema = schemaProvider.getSchema(schemaIS);
        
        String json = "{SOME JSON FROM INPUT STREAM";
        JsonNode jsonNode = mapper.readTree(json);

        User user = mapper.treeToValue(jsonNode,
                                       User.class);



    }
    
    
}
