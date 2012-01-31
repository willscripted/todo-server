package todo.webapp.controllers;

import org.junit.Test;
import todo.test.functional.ControllerFunctionalTestBase;

import static org.junit.Assert.assertTrue;

/**
 *
 */
public class RegistrationFormControllerFunctionalTest extends 
                                                      ControllerFunctionalTestBase {

    @Test
    public void test_GET(){

        request.setMethod("GET");
        request.setRequestURI("/registration/");

        handle(request, response);

        assertTrue(response.getStatus() != 4030);
        System.out.println("%%%%" + response.getStatus());

    }

}
