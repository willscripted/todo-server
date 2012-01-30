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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Controller for managing a specific user resource.
 *
 * @author Will O'Brien
 */
@Controller
@RequestMapping("/api/users/{username}/")
public class SpecificUserController {

    @Autowired
    private UserService userService;

    /**
     * Get specific user of application.
     *
     * @param username Username of user entity.
     * @return User
     */
    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    User get(@PathVariable String username) {
        // Ensure password is not sent
        throw new UnsupportedOperationException();
    }

    @RequestMapping(method = RequestMethod.PUT,
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
        // Todo - consider moving registration to POST /users/ to maintain
        // method idempotence
        else {
            User user = new User();
            user.setCreated(new Date());
            user.setPassword(form.getPassword());
            user.setEmail(form.getEmail());
            user.setUsername(form.getUsername());

            userService.createUser(user);

            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        }

    }

    /**
     * PUT - Update user entity for username.
     *
     * @param username
     */
    @RequestMapping(method = RequestMethod.PUT)
    public void putUpdate(@PathVariable String username,
                          @RequestBody User user,
                          HttpServletRequest request) {
        System.out
                .println("Old: " + request.getContentType());

    }

    /**
     * POST - method not allowed.
     *
     * @param response set response status code to http method not allowed.
     */
    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    void post(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    /**
     * DELETE - remove user from application.
     *
     * @param username String username to remove
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public
    @ResponseBody
    void delete(@PathVariable String username) {
        throw new UnsupportedOperationException();
    }


}
