package todo.json.schema.spring;

import org.junit.Before;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.assertTrue;

/**
 * User: Will O'Brien
 * Date: 1/14/12
 * Time: 10:53 AM
 */
public class SchemaConfigTest {
    private static final String TYPE_STRING = "application/test.schema+json";
    private static final String TYPE = "application";
    private static final String SUB_TYPE = "test.schema+json";
    private static final Class CLAZZ = String.class; // any class

    @Before
    public void setUp() throws Exception {

    }
    
    public void testMediaTypeConstruction()  {
        Resource resource = createMock(Resource.class);
        SchemaConfig config = new SchemaConfig(TYPE_STRING,
                                               resource,
                                               CLAZZ);
        
        MediaType mediaType = config.getMediaType();
        assertTrue(mediaType.getType().equals(TYPE));
        assertTrue(mediaType.getSubtype().equals(SUB_TYPE));
    }
}
