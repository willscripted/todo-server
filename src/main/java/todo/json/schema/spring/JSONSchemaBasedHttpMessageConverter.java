package todo.json.schema.spring;

import eu.vahlas.json.schema.JSONSchema;
import eu.vahlas.json.schema.JSONSchemaProvider;
import eu.vahlas.json.schema.impl.JacksonSchemaProvider;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import todo.hibernate.entities.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An Http message converter that converts set media types to instances of
 * their corresponding java type. Similar to Jackson's
 *
 * @author Will O'Brien
 */
public class JSONSchemaBasedHttpMessageConverter implements
                                                 HttpMessageConverter<Object> {

    // Map holding the media type each class can be converted to/from
    private final Map<Class, MediaType> convertables;

    // Map with schema for each media type
    private final Map<MediaType, JSONSchema> schemas;

    // Object mapper
    private final ObjectMapper mapper;

    private final JSONSchemaProvider schemaProvider;


    public JSONSchemaBasedHttpMessageConverter() {
        convertables = new HashMap<Class, MediaType>();
        schemas = new HashMap<MediaType, JSONSchema>();
        mapper = new ObjectMapper();
        schemaProvider = new JacksonSchemaProvider(mapper);
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return convertables.containsKey(clazz);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return new ArrayList<MediaType>(schemas.keySet());
    }

    @Override
    public Object read(Class<? extends Object> clazz,
                       HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        // Get schema to use
        MediaType mediaType = inputMessage.getHeaders()
                                          .getContentType();
        JSONSchema schema = schemas.get(mediaType);

        // Get Message
        String json = extractMessageEntityAsString(inputMessage);

        // Validate message against schema
        List<String> errors = schema.validate(json);
        if (!errors.isEmpty()) {
            throw new HttpMessageUnprocessableException("Entity does not "
                                                        + "conform to schema.");
        }

        // Get object from valid json
        JsonNode jsonNode = mapper.readTree(json);
        Object constructedObject = mapper.treeToValue(jsonNode, clazz);

        // Return object
        return constructedObject;
    }

    /**
     * Convert message body (InputStream) to String
     *
     * @param inputMessage
     * @return String message body
     * @throws IOException
     */
    private String extractMessageEntityAsString(HttpInputMessage inputMessage)
            throws IOException {
        InputStream inputStream = inputMessage.getBody();
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream,
                     writer);
        return writer.toString();
    }

    @Override
    public void write(Object o, MediaType contentType,
                      HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        throw new UnsupportedOperationException();
    }

    public void setSchemas(Set<SchemaConfig> schemaConfigs) throws
                                                      IOException {
        for (SchemaConfig config : schemaConfigs) {
            Class clazz = config.getClazz();
            Resource schemaLocation = config.getSchemaLocation();

            MediaType mediaType = config.getMediaType();
            InputStream inputStream = schemaLocation.getInputStream();
            JSONSchema schema = schemaProvider.getSchema(inputStream);

            schemas.put(mediaType, schema);
            convertables.put(clazz, mediaType);

        }
    }

}
