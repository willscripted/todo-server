package todo.webapp.controllers.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import todo.domain.User;
import todo.services.UserService;

import javax.servlet.http.HttpServletResponse;

/**
 * Controller for managing a specific user resource.
 *
 * @author Will O'Brien
 */
@Controller
public class UsersUserController {

    private static final String CLASS_REQUEST_MAPPING =
            "/api/users/{username}/";

    /**
     * UserService provides access to User resources.
     */
    @Autowired
    private UserService userService;

    /**
     * Get specific user of application.
     *
     * @param username Username of user entity.
     * @return User application/todo.domain.User+json - password censored
     */
    @RequestMapping(
            value = CLASS_REQUEST_MAPPING,
            method = RequestMethod.GET,
            produces = "application/todo.domain"
                       + ".User+json")
    public
    @ResponseBody
    User get(final @PathVariable String username,
             final HttpServletResponse response) {

        User user = userService.getUserByUsername(username);

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        // Ensure password is not sent
        user.setPassword("[### PROTECTED ###]");
        return user;

    }


    /**
     * PUT - Update user entity for username.
     *
     * @param username
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.PUT)
    public
    @ResponseBody
    void putUpdate(final @PathVariable String username,
                   final @RequestBody User user,
                   final HttpServletResponse response) {

        if (userService.usernameExists(username)) {
            userService.updateUser(user);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }


    }

    /**
     * POST - method not allowed.
     *
     * @param response set response status code to http method not allowed.
     */
    @RequestMapping(
            value = CLASS_REQUEST_MAPPING,
            method = RequestMethod.POST)
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
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.DELETE)
    public
    @ResponseBody
    void delete(final @PathVariable String username) {

        userService.removeUserByUsername(username);

    }


}
