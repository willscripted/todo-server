package todo.webapp.controllers.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import todo.webapp.dto.RegistrationForm;
import todo.domain.User;
import todo.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

/**
 * RESTful controller of User resource
 *
 * @author Will O'Brien
 */
@Controller
public class RootController {

    private static final String CLASS_REQUEST_MAPPING = "/api/users/";

    /**
     * Service for accessing User resources.
     */
    @Autowired
    private UserService userService;

    /**
     * Returns a collection of all the usernames in the application.
     *
     * @return Collection<String>
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<String> getUsers() {

        return userService.getUsernames();

    }

    /**
     * Create a new User from data contained in the RegistrationForm.
     * If username is taken, a status code 400 - Bad Request is sent.
     *
     * @param form     Valid RegistrationForm
     * @param response HttpServletResponse
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.POST,
                    consumes = "application/todo.webapp.dto.RegistrationForm+json")
    public
    @ResponseBody
    String registerUsername(final @RequestBody RegistrationForm form,
                          final HttpServletResponse response)
            throws IOException {

        String username = userService.createUser(form);

        if (username == null) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                               "Username \'"
                               + form.getUsername()
                               + "\' taken");
            return null;

        } else {

            response.setStatus(HttpServletResponse.SC_CREATED);
            return username;

        }
    }


    /**
     * PUT and DELETE return a response with status code 405 - unsupported
     * operation.
     *
     * @param response Response to return.
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = {RequestMethod.PUT,
                              RequestMethod.DELETE})
    public
    @ResponseBody
    void unsupported(final HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }


}
