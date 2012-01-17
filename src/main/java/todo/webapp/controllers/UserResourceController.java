package todo.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import todo.persistence.UserDao;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * RESTful controller of User resource
 *
 * @author Will O'Brien
 */
@Controller
@RequestMapping("/users/")
public class UserResourceController {

    private UserDao userDao;

    @Autowired
    public UserResourceController(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Returns a collection of all the usernames in the application
     * @return Collection<String>
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Collection<String> getUsers() {
        return null;
    }

    /**
     * POST, PUT, DELETE return responses with status code 405 - unsupported
     * operation.
     * @param response
     */
    @RequestMapping(method = {RequestMethod.PUT,
                            RequestMethod.DELETE,
                            RequestMethod.POST})
    public @ResponseBody void unsupported(HttpServletResponse response) {
        response.setStatus(405);
    }
}
