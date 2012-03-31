package todo.webapp.controllers.api.tasks;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import todo.domain.Task;
import todo.domain.User;
import todo.services.TaskService;
import todo.services.UserService;
import todo.webapp.dto.TaskDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * Controller for managing a specific task of a user.
 *
 * @author Will O'Brien
 */
@Controller
public class TasksUserTaskController {

    private static final String CLASS_REQUEST_MAPPING =
            "/api/tasks/{taskId:[0-9]+}";

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

    /**
     * Retrieve a task of a user.
     *
     * @param taskId   Id of task to retrieve
     * @return application/todo.Task+json Task to retrieve
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.GET)
    public
    @ResponseBody
    TaskDTO get(@PathVariable Long taskId,
                Principal principal,
                HttpServletResponse response) throws IOException {

        Task task = taskService.getTask(taskId);

        if (task == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        return mapper.map(task, TaskDTO.class);


    }

    /**
     * PUT - update a task with new information.
     *
     * @param taskDTO  TaskDTO entity
     * @param taskId   Id of task to update
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.PUT,
                    consumes = "application/todo.webapp.dto.TaskDTO+json")
    public
    @ResponseBody
    void put(@PathVariable Long taskId,
             Principal principal,
             @RequestBody TaskDTO taskDTO) {

        Task task = mapper.map(taskDTO, Task.class);
        task.setId(taskId);

        User user = userService.getUserByUsername(principal.getName());
        task.setUser(user);

        taskService.updateTask(task);
    }

    /**
     * POST - method not allowed.
     *
     * @param response Response with http status code for method not allowed.
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.POST)
    public
    @ResponseBody
    void post(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    /**
     * DELETE - remove a task from the application.
     *
     * @param taskId   Id of task to remove.
     * @param response Response to be returned.
     */
    @RequestMapping(
            value = CLASS_REQUEST_MAPPING,
            method = RequestMethod.DELETE
    )
    public
    @ResponseBody
    void delete(@PathVariable Long taskId,
                Principal principal,
                HttpServletResponse response) {
        taskService.removeTask(taskId);
    }
}
