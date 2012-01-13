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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation
        .AnnotationMethodHandlerAdapter;

import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: will
 * Date: 11/10/11
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:WEB-INF/applicationContext-security.xml",
                       "classpath:WEB-INF/todo-servlet.xml"})
public class AuthenticationViewsControllerIntegrationTest {

    @Autowired
    private ApplicationContext applicationContext;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private AuthenticationViewsController controller;
    private AnnotationMethodHandlerAdapter handlerAdapter;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        controller =
                applicationContext.getBean(AuthenticationViewsController
                                                   .class);
        handlerAdapter =
                applicationContext.getBean(AnnotationMethodHandlerAdapter
                                                   .class);
    }

    @Test
    public void testLogin() throws Exception {
        // Set up Request
        request.setRequestURI("/login/");
        request.setMethod("GET");

        // Process Request
        final ModelAndView mav = handlerAdapter.handle(request,
                                                       response,
                                                       controller);

        // Verify correct response

        { // Model
            Map<String, Object> model = mav.getModel();
            assertTrue(model.isEmpty());
        }

        { // View
            String expected = "auth-reg/login";
            String returned = mav.getViewName();
            assertTrue(expected.equals(returned));
        }


    }

    @Test
    public void testInvalidLogin() throws Exception {
        // Set up Request
        request.setRequestURI("/login/invalid/");
        request.setMethod("GET");

        // Process Request
        final ModelAndView mav = handlerAdapter.handle(request,
                                                       response,
                                                       controller);

        // Verify correct response

        { // Model
            Map<String, Object> model = mav.getModel();
            assertTrue(model.containsKey("invalid"));
            assertTrue((Boolean) model.get("invalid") == true);
        }

        { // View
            String expected = "auth-reg/login";
            String returned = mav.getViewName();
            assertTrue(expected.equals(returned));
        }
    }
}
