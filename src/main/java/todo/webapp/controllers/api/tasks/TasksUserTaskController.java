package todo.webapp.controllers.api.tasks;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import todo.domain.Task;

import javax.servlet.http.HttpServletResponse;

/**
 * Controller for managing a specific task of a user.
 *
 * @author Will O'Brien
 */
@Controller
@RequestMapping("/api/tasks/{username}/{taskId}")
public class TasksUserTaskController {

    /**
     * Retrieve a task of a user.
     *
     * @param taskId Id of task to retrieve
     * @param username Username of user owning this task
     * @return application/todo.Task+json Task to retrieve
     */
    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    Task get(@PathVariable Long taskId,
             @PathVariable String username) {
        throw new UnsupportedOperationException();
    }

    /**
     * PUT - update a task with new information.
     *
     * @param task Task entity (application/todo.Task+json)
     * @param taskId Id of task to update
     * @param username owner of the task to update
     */
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody void put(@PathVariable Long taskId,
                                  @PathVariable String username,
                                  @RequestBody Task task) {
        throw new UnsupportedOperationException();
    }

    /**
     * POST - method not allowed.
     *
     * @param response Response with http status code for method not allowed.
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody void post(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    /**
     * DELETE - remove a task from the application.
     *
     * @param taskId Id of task to remove.
     * @param username Username of user owning the task.
     * @param response Response to be returned.
     */
    public @ResponseBody void delete(@PathVariable Long taskId,
                                     @PathVariable String username,
                                     HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }
}
