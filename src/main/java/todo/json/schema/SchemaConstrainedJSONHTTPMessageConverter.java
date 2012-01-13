package todo.json.schema;

import eu.vahlas.json.schema.JSONSchema;
import eu.vahlas.json.schema.JSONSchemaProvider;
import eu.vahlas.json.schema.impl.JacksonSchemaProvider;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import todo.hibernate.entities.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

/**
 * User: Will O'Brien
 * Date: 1/12/12
 * Time: 10:30 AM
 */
public class SchemaConstrainedJSONHTTPMessageConverter implements
                                                       HttpMessageConverter<Object> {

    public boolean canRead(Class<?> aClass, MediaType mediaType) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean canWrite(Class<?> aClass, MediaType mediaType) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<MediaType> getSupportedMediaTypes() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object read(Class<? extends Object> aClass,
                       HttpInputMessage httpInputMessage)
            throws IOException, HttpMessageNotReadableException {

        // Get Mapper
        ObjectMapper mapper = new ObjectMapper();

        // Get schema
        JSONSchemaProvider schemaProvider = new JacksonSchemaProvider(mapper);
        InputStream schemaIS = new FileInputStream("schema.json");
        JSONSchema schema = schemaProvider.getSchema(schemaIS);

        // Get Message
        InputStream inputStream = httpInputMessage.getBody();
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer);
        String json = writer.toString();

        // Validate json against schema
        schema.validate(json);

        // Get object from valid json
        JsonNode jsonNode = mapper.readTree(json);
        User user = mapper.treeToValue(jsonNode,
                                       User.class);


        // Return object
        return user;


    }

    public void write(Object o, MediaType mediaType,
                      HttpOutputMessage httpOutputMessage)
            throws IOException, HttpMessageNotWritableException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
