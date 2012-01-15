package todo.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import todo.hibernate.entities.User;
import todo.persistence.UserDao;

import javax.servlet.http.HttpServletResponse;

/**
 * RESTful controller to manipulate instances in the user model
 *
 * @author Will O'Brien
 */
@Controller
@RequestMapping("/users/{id}")
public class UserController {

    @Autowired
    private UserDao userDao;

    /**
     * Get a user by their id
     * @param id username of the user
     * @return User identified, or null and status 404
     */
    @RequestMapping( method = RequestMethod.GET)
    public @ResponseBody User get(@PathVariable String id) {
        throw new UnsupportedOperationException();

        // if exists

        // if does not exist
    }

    /**
     * Update a user with data from put request, or, if user does not exist,
     * create that user
     * @param id username of the user
     * @param user User
     */
    @RequestMapping( method = RequestMethod.PUT)
    public @ResponseBody void put(@PathVariable String id,
                                  @RequestBody User user) {
        // Server specific validation

        // if exists - updates entity

        // if does not exist - return status code 404
    }

    /**
     * Unsupported operation - Returns status code 405
     * @param response
     */
    @RequestMapping( method = RequestMethod.POST)
    public @ResponseBody void post(HttpServletResponse response) {
        response.setStatus(405);
    }

    /**
     * Remove a user by their
     * @param id username of the user
     */
    @RequestMapping( method = RequestMethod.DELETE)
    public @ResponseBody void delete(@PathVariable String id) {
        throw new UnsupportedOperationException();
    }


}
