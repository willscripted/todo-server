package todo.json.schema.spring;

import org.apache.tools.ant.filters.StringInputStream;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.schema.JsonSchema;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test JSONSchemaBasedHttpMessageConverter.
 *
 * @author Will O'Brien
 */
public class JSONSchemaBasedHttpMessageConverterTest {

    private final Class CLAZZ = Basic.class;
    private final Class INVALID_CLAZZ = InvalidBasic.class;
    private final String INVALID_MEDIA_TYPE_STRING = "application/todo"
                                                     + ".Mock+json";
    private final String MEDIA_TYPE_STRING = "application/todo.Basic+json";

    private final String VALID_JSON = "{ \"myInt\":4, "
                                      + "\"myString\":\"stringy\" }";

    // Note - "4" as string instead of 4 as int
    private final String INVALID_JSON = "{ \"myInt\":\"4\", "
                                        + "\"myString\":\"stringy\" }";



    private JSONSchemaBasedHttpMessageConverter converter;
    private String schema;
    private MediaType mediaType;
    private MediaType invalidMediaType;



    @Before
    public void setUp() throws Exception {
        converter = new JSONSchemaBasedHttpMessageConverter();

        { // Set json schema
            ObjectMapper mapper = new ObjectMapper();
            JsonSchema jsonSchema = mapper.generateJsonSchema(CLAZZ);
            schema = jsonSchema.toString();
        }

        { // Set valid MediaTypes
            String[] typeAndSubtype = MEDIA_TYPE_STRING.split("/");
            mediaType = new MediaType(typeAndSubtype[0],
                                      typeAndSubtype[1]);
        }
        { // Set invalid MediaType
            String[] typeAndSubtype = INVALID_MEDIA_TYPE_STRING.split("/");
            invalidMediaType = new MediaType(typeAndSubtype[0],
                                             typeAndSubtype[1]);
        }

        // Get schema as resource
        StringInputStream inputStream = new StringInputStream(schema);
        Resource resource = new InputStreamResource(inputStream);

        { // Create configs & add to converter
            Set<SchemaConfig> configs = new HashSet<SchemaConfig>();
            SchemaConfig basicConfig = new SchemaConfig("application/todo"
                                                        + ".Basic+json",
                                                        resource,
                                                        CLAZZ);
            configs.add(basicConfig);
            converter.setSchemas(configs);
        }
    }

    @Test
    public void testCanRead_Valid() throws Exception {
        assertTrue(converter.canRead(CLAZZ,
                                     mediaType));
    }

    @Test
    public void testCanRead_InvalidClassForGivenMediaType() {
        assertFalse(converter.canRead(INVALID_CLAZZ,
                                      mediaType));
    }

    @Test
    public void testCanRead_InvalidMediaTypeForGivenClass() {
        assertFalse(converter.canRead(CLAZZ,
                                      invalidMediaType));
    }

    @Test
    public void testCanRead_InvalidClassAndMediaType() {
        assertFalse(converter.canRead(INVALID_CLAZZ,
                                      invalidMediaType));
    }

    @Test
    public void testGetSupportedMediaTypes() throws Exception {
        // Get supported types
        Collection<MediaType> supportedTypes = converter
                .getSupportedMediaTypes();

        // Assert expected exists
        assertTrue(supportedTypes.contains(mediaType));
        // Assert unexpected does not exist
        assertFalse(supportedTypes.contains(invalidMediaType));
    }

    @Test
    public void testValidRead() throws Exception {

        HttpInputMessage message = createMock(HttpInputMessage.class);
        { // Prepare mock message
            HttpHeaders messageHeaders = createMock(HttpHeaders.class);
            { // Program mock headers
                expect(messageHeaders.getContentType()).andReturn(mediaType);
                replay(messageHeaders);
            }

            InputStream messageBody = new StringInputStream(VALID_JSON);
            { // Program mock message body
                expect(message.getHeaders()).andReturn(messageHeaders);
                expect(message.getBody()).andReturn(messageBody);
                replay(message);
            }
        }

        converter.read(CLAZZ,
                       message);
    }

    @Test(expected = HttpMessageUnprocessableException.class)
    public void testInvalidRead() throws IOException {

        HttpInputMessage message = createMock(HttpInputMessage.class);
        { // Prepare mock message
            HttpHeaders messageHeaders = createMock(HttpHeaders.class);
            { // Program mock headers
                expect(messageHeaders.getContentType()).andReturn(mediaType);
                replay(messageHeaders);
            }

            InputStream messageBody = new StringInputStream(INVALID_JSON);
            { // Program mock message body
                expect(message.getHeaders()).andReturn(messageHeaders);
                expect(message.getBody()).andReturn(messageBody);
                replay(message);
            }
        }

        converter.read(CLAZZ,
                       message);
    }

}
