package todo.webapp.controllers.api.tasks;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import todo.domain.Task;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Controller managing tasks of a specific user
 *
 * @author Will O'Brien
 */
@Controller
public class TasksUserController {

    private static final String CLASS_REQUEST_MAPPING = "/api/tasks/{username}";

    /**
     * GET - Retrieve a list of user's tasks
     *
     * @param username String username of user whose tasks should be retrieved.
     * @return List<Task> application/json
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.GET)
    public
    @ResponseBody
    List<Task> get(@PathVariable String username) {
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method not allowed on this resource.
     *
     * @param response Response - http status code method not allowed
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.PUT)
    public
    @ResponseBody
    void put(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    /**
     * POST - Add a task for user.
     *
     * @param username String username of user to create new task for.
     * @param task
     * @return Serializable id of new task if post is successful,
     *         else response with appropriate status code.
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.POST)
    public
    @ResponseBody
    Long post(@RequestBody Task task,
              @PathVariable String username,
              HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }

    /**
     * DELETE - remove all tasks of user.
     *
     * @param username
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.DELETE)
    public
    @ResponseBody
    void delete(@PathVariable String username) {
        throw new UnsupportedOperationException();
    }


}
