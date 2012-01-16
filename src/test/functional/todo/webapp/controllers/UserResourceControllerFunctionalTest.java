package todo.webapp.controllers;

import org.junit.Test;
import todo.test.functional.ControllerFunctionalTestBase;

import static org.junit.Assert.assertTrue;

/**
 * User: Will O'Brien
 * Date: 1/16/12
 * Time: 2:46 PM
 */
public class UserResourceControllerFunctionalTest extends
                                                  ControllerFunctionalTestBase {

    @Test
    public void test_GET(){

        request.setMethod("GET");
        request.setRequestURI("/users/");

        handle(request, response);
        
        assertTrue(response.getStatus() == 200);

    }




}