package todo.webapp.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: will
 * Date: 11/10/11
 * Time: 1:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class AuthenticationViewsControllerTest {

    AuthenticationViewsController instance;

    @Before
    public void setUp() throws Exception {
        instance = new AuthenticationViewsController();
    }

    @Test
    public void testLogin() throws Exception {
        String expected = "auth-reg/login";
        String result = instance.login();

        assertTrue(expected.equals(result));

    }

    @Test
    public void testInvalidLogin() throws Exception {
        ModelAndView result = instance.invalidLogin();

        { // Assert correct view name
            String expectedViewName = "auth-reg/login";
            String resultViewName = result.getViewName();
            assertTrue(expectedViewName.equals(resultViewName));
        }

        { // Assert invalid message is set
            Map<String, Object> map = result.getModel();
            assertTrue(map.containsKey("invalid"));
            assertTrue((Boolean) map.get("invalid"));
        }
    }
}
