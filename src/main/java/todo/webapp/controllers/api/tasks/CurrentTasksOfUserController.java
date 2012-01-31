package todo.webapp.controllers.api.tasks;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import todo.domain.Task;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;

/**
 * Controller for accessing/modifying the current tasks of a user.
 *
 * @author Will O'Brien
 */
@Controller
@RequestMapping("/api/tasks/{username}/current")
public class CurrentTasksOfUserController {

    /**
     * GET - Retrieve a list of the user's current tasks.
     *
     * @param username Username of user whose current tasks will be retrieved.
     * @return List<Task>
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<Task> get(@PathVariable String username) {
        throw new UnsupportedOperationException();
    }

    /**
     * PUT - Update the list of current tasks
     * @param username String username of user whose current tasks should be
     *                 updated.
     * @param taskIds Ids of tasks to add.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody void put(@PathVariable String username,
                                  @RequestBody Collection<Long> taskIds) {
        throw new UnsupportedOperationException();
    }

    /**
     * POST - Add a new task to a user's collection of current tasks.
     * 
     * @param username String username of user to add current task to
     * @param task Task to add.
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody void post(@PathVariable String username,
                                  @RequestBody Task task) {
        throw new UnsupportedOperationException();
    }

    /**
     * DELETE - method not supported.
     *
     * @param response Set response status code to http method not allowed.
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody void delete(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
    
}
