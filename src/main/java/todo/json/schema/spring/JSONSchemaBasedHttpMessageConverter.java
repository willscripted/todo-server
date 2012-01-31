package todo.json.schema.spring;

import eu.vahlas.json.schema.JSONSchema;
import eu.vahlas.json.schema.JSONSchemaProvider;
import eu.vahlas.json.schema.impl.JacksonSchemaProvider;
import org.apache.commons.collections.map.MultiKeyMap;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An Http message converter that converts set media types to instances of
 * their corresponding java type. Similar to Jackson's
 *
 * @author Will O'Brien
 */
public final class JSONSchemaBasedHttpMessageConverter implements
                                                 HttpMessageConverter<Object> {

    /**
     * Map that contains information on how to convert from key1 to key2. If it
     * can be done, a schema will exist for value of k1 and
     * k2 in the map. Else null.
     */
    private final MultiKeyMap convertionMap;

    /**
     * MediaTypes that this converter can convert.
     */
    private final Set<MediaType> mediaTypes;

    /**
     * Object mapper used to convert to/from json and target classes.
     */
    private final ObjectMapper mapper;

    /**
     * Creates schemas from InputStreams (of json).
     */
    private final JSONSchemaProvider schemaProvider;


    /**
     * New converter with no set mappings.
     */
    public JSONSchemaBasedHttpMessageConverter() {

        convertionMap = new MultiKeyMap();
        mediaTypes = new HashSet<MediaType>();

        mapper = new ObjectMapper();
        schemaProvider = new JacksonSchemaProvider(mapper);
    }

    /**
     * Return true if an instance of Class clazz can be created from a
     * message of MediaType mediaType by this converter.
     *
     * @param clazz     Class desired.
     * @param mediaType MediaType to convert from.
     * @return boolean
     */
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {

        // Note: Schema for k1 (mediaType) -> k2 (clazz)
        JSONSchema schema = (JSONSchema) convertionMap.get(mediaType,
                                                           clazz);

        // If schema is not null, return true
        return (schema != null);
    }

    /**
     * Returns true if an instance of Class clazz can be written to the
     * specified MediaType.
     *
     * @param clazz     Class to be written to output.
     * @param mediaType MediaType to be written to output.
     * @return boolean
     */
    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {

        // Note: Schema for k1 (mediatype) -> k2 (clazz)
        JSONSchema schema = (JSONSchema) convertionMap.get(mediaType,
                                                           clazz);
        // If schema is not null, return true
        return (schema != null);
    }

    /**
     * Return the list of MediaType objects supported by this converter.
     *
     * @return
     */
    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return new ArrayList<MediaType>(mediaTypes);
    }


    @Override
    public Object read(Class<? extends Object> clazz,
                       HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {

        // Get schema to use
        MediaType mediaType = inputMessage.getHeaders()
                                          .getContentType();
        JSONSchema schema = (JSONSchema) convertionMap.get(mediaType,
                                                           clazz);
        if (schema == null) {
            throw new HttpMessageNotReadableException("Message with invalid "
                                                      + "MediaType passed. Call "
                                                      + "canRead first.");
        }

        // Get Message
        String json = extractMessageEntityAsString(inputMessage);

        // Validate message against schema

        // Undeclared IOException thrown by validate on invalid json. Must
        // declare general exception so it can be rethrown as invalid message
        // format. or match
        List<String> errors = null;
        try {
            errors = schema.validate(json);
        } catch (Exception e) {
            throw new HttpMessageNotReadableException("Unexpected end of "
                                                      + "json");
        }
        if (!errors.isEmpty()) {
            throw new HttpMessageUnprocessableException("Entity does not "
                                                        + "conform to schema.");
        }

        // Get object from valid json
        JsonNode jsonNode = mapper.readTree(json);
        Object constructedObject = mapper.treeToValue(jsonNode,
                                                      clazz);

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

    /**
     * Write Object o to MediaType'd outputMessage.
     *
     * @param o             Object to write.
     * @param contentType   MediaType to write.
     * @param outputMessage Outgoing http message.
     * @throws IOException
     * @throws HttpMessageNotWritableException
     *
     */
    @Override
    public void write(Object o, MediaType contentType,
                      HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        throw new UnsupportedOperationException();
    }

    public void setSchemas(Set<SchemaConfigInstance> schemaConfigInstances)
            throws
            IOException {

        for (SchemaConfigInstance configInstance : schemaConfigInstances) {

            MediaType mediaType = configInstance.getMediaType();

            // Schema input stream
            InputStream inputStream = configInstance
                    .getSchema()
                    .getInputStream();

            JSONSchema schema = schemaProvider.getSchema(inputStream);

            this.convertionMap
                    .put(mediaType,
                         configInstance.getClazz(),
                         schema);
            this.mediaTypes
                    .add(mediaType);

        }
    }


}
