package todo.webapp.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import todo.persistence.UserDao;

import java.util.ArrayList;
import java.util.Collection;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertTrue;

/**
 * User: Will O'Brien
 * Date: 1/15/12
 * Time: 6:24 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:WEB-INF/todo-servlet.xml")
public class UserResourceControllerTest {

    @Autowired
    private ApplicationContext applicationContext;
    private AnnotationMethodHandlerAdapter handlerAdapter;
    
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Autowired
    private UserResourceController controller;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        handlerAdapter = new AnnotationMethodHandlerAdapter();
        handlerAdapter.setApplicationContext(applicationContext);
    }

    @Test
    public void testGetUsers() throws Exception {

        // Program request
        request.setRequestURI("/users/");
        request.setMethod("GET");
        
        // Test
        handlerAdapter.handle(request, response, controller);

        // Assert
        assertTrue(response.getStatus() == 200);
        assertTrue(response.getContentType().equals("application/json"));

    }

    @Test
    public void testUnsupported_PUT() throws Exception {
        // Program request
        request.setMethod("PUT");

        // Test
        handlerAdapter.handle(request, response, controller);

        // Assert
        assertTrue(response.getBufferSize() == 0);
        assertTrue(response.getStatus() == 405);

    }

    @Test
    public void testUnsupported_POST() throws Exception {
        // Program request
        request.setMethod("POST");

        // Test
        handlerAdapter.handle(request, response, controller);

        // Assert
        assertTrue(response.getBufferSize() == 0);
        assertTrue(response.getStatus() == 405);

    }

    @Test
    public void testUnsupported_DELETE() throws Exception {
        // Program request
        request.setMethod("DELETE");

        // Test
        handlerAdapter.handle(request, response, controller);

        // Assert
        assertTrue(response.getBufferSize() == 0);
        assertTrue(response.getStatus() == 405);

    }
}
