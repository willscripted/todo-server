package todo.json.schema.spring;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import todo.hibernate.entities.User;
import todo.json.schema.spring.HttpMessageUnprocessableException;

import java.lang.Object;

import static org.junit.Assert.assertTrue;

/**
 * User: Will O'Brien
 * Date: 1/14/12
 * Time: 4:55 PM
 */
public class TodoHandlerExceptionResolverTest {

    private TodoHandlerExceptionResolver exceptionResolver;

    @Before
    public void setUp() throws Exception {
        exceptionResolver = new TodoHandlerExceptionResolver();
    }

    @Test
    public void testResolveException() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse resp = new MockHttpServletResponse();
        Exception ex = new HttpMessageUnprocessableException("test");
        Object handler = new Object();

        exceptionResolver.resolveException(req, resp, handler, ex);

        assertTrue(resp.getStatus() == 422);
    }
}
