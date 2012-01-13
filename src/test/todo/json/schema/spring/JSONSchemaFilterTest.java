package todo.json.schema.spring;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * User: Will O'Brien
 * Date: 1/11/12
 * Time: 11:02 AM
 */
public class JSONSchemaFilterTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        request.setContent("Hello World".getBytes() );
        response = new MockHttpServletResponse();
    }

    @After
    public void after() {
        // TODO Assert getReader has not been called
        assertNotNull(request.getInputStream());
    }

    /**
     * Poorly formed JSON
     */
    @Test
    public void testMalformedJSONRequestPreHandle() {
    }

    /**
     * Request is not of type 'application/json'
     * No filtering is expected, true should always be returned by preHandle()
     */
    @Test
    public void testNonJSONRequestPreHandle() {
    }

    /**
     * Request is of MIME type 'application/json'. Json is valid and handler
     * does not constrain json to schema.
     */
    @Test
    public void testValidNonConstrainedJSONPreHandle() {

    }

    /**
     * Request is of MIME type 'application/json'. Json is valid and handler
     * requires json schema. Json does not conform to schema
     */
    @Test
    public void testSemanticallyInvalidJSONPreHandle() {

    }

    /**
     * Request is of MIME type 'application/json'. Json is valid and handler
     * requires json schema. Json conforms to schema.
     */
    @Test
    public void testValidConstrainedJSONPreHandle() {

    }

}
