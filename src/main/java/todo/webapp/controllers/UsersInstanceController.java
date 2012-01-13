package todo.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import todo.hibernate.entities.User;
import todo.json.schema.spring.SchemaConstrainedJSONConsumer;

import javax.servlet.http.HttpServletResponse;

/**
 * User: Will O'Brien
 * Date: 1/8/12
 * Time: 11:06 AM
 */
@Controller
@RequestMapping("/users/{id}")
public class UsersInstanceController {

    // GET
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody User get(@PathVariable Long id) {
        throw new UnsupportedOperationException();

        // if exists

        // if does not exist
    }

    // PUT
    @RequestMapping(method = RequestMethod.PUT)
    @SchemaConstrainedJSONConsumer("/path/to/schema")
    public @ResponseBody void put(@PathVariable Long id,
                                  @RequestBody User user) {
        // Validate - if invalid syntactics rc400 - if invalid semantics rc422

        // if exists - updates entity

        // if does not exist - return status code 404
    }

    /**
     * Unsupported operation - Returns status code 405
     * @param response
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody void post(HttpServletResponse response) {
        response.setStatus(405);
    }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody void delete(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }
}
