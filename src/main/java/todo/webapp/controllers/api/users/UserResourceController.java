package todo.webapp.controllers.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import todo.domain.RegistrationForm;
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
@RequestMapping("/api/users/")
public final class UserResourceController {

    /**
     * Service for accessing User resources.
     */
    @Autowired
    private UserService userService;
    
    /**
     * Returns a collection of all the usernames in the application.
     * @return Collection<String>
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Collection<String> getUsers() {

        return userService.getUsernames();
        
    }

    /**
     * Create a new User from data contained in the RegistrationForm.
     * If username is taken, a status code 400 - Bad Request is sent.
     *
     * @param username Desired username.
     * @param form Valid RegistrationForm
     * @param response HttpServletResponse
     */
    @RequestMapping(method = RequestMethod.POST,
                    consumes = "application/todo.domain.RegistrationForm+json")
    public
    @ResponseBody
    void registerUsername(final @PathVariable String username,
                          final @RequestBody RegistrationForm form,
                          final HttpServletResponse response) {

        // If user exists, throw bad request
        if (userService.usernameExists(username)) {

            try {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                                   "Username taken");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        // Else create new user
        else {

            User user = new User();
            user.setCreated(new Date());
            user.setPassword(form.getPassword());
            user.setEmail(form.getEmail());
            user.setUsername(form.getUsername());

            userService.createUser(user);

            response.setStatus(HttpServletResponse.SC_CREATED);
        }

    }


    /**
     * PUT and DELETE return a response with status code 405 - unsupported
     * operation.
     * @param response Response to return.
     */
    @RequestMapping(method = {RequestMethod.PUT,
                            RequestMethod.DELETE} )
    public @ResponseBody void unsupported(final HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }


}
