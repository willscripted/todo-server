package todo.webapp.controllers.api.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import todo.domain.User;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * RESTful controller of User resource
 *
 * @author Will O'Brien
 */
@Controller
@RequestMapping("/api/users/")
public class UserResourceController {

    /**
     * Returns a collection of all the usernames in the application
     * @return Collection<String>
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Collection<String> getUsers() {

        throw new UnsupportedOperationException();
    }

    /**
     * POST, PUT, DELETE return responses with status code 405 - unsupported
     * operation.
     * @param response Response to return.
     */
    @RequestMapping(method = {RequestMethod.PUT,
                            RequestMethod.DELETE,
                            RequestMethod.POST} )
    public @ResponseBody void unsupported(final HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);

        throw new UnsupportedOperationException();
    }


}
