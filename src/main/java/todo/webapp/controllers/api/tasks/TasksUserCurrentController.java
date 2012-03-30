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
import todo.json.schema.ValidationDelegator;
import todo.services.TaskService;
import todo.webapp.dto.TaskDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Controller for accessing/modifying the current tasks of a user.
 *
 * @author Will O'Brien
 */
@Controller
public class TasksUserCurrentController {

    private static final String CLASS_REQUEST_MAPPING =
            "/api/tasks/current";

    @Autowired
    private TaskService taskService;

    @Autowired
    private Mapper mapper;

    @Autowired
    private TasksUserController tasksUserController;

    @Autowired
    private ValidationDelegator jsonSchemaValidator;

    /**
     * GET - Retrieve a list of the user's current tasks.
     *
     * @return List<Task>
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.GET)
    public
    @ResponseBody
    List<TaskDTO> get(Principal principal) {

        Collection<Task> currentTasksOfUser =
                taskService.getCurrentTasksOfUser(principal.getName());

        List<TaskDTO> tasks = new ArrayList<TaskDTO>(currentTasksOfUser.size
                ());
        for (Task t : currentTasksOfUser) {
            TaskDTO task = mapper.map(t, TaskDTO.class);
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * PUT - Update the list of current tasks
     *
     * @param taskIds  Ids of tasks to add.
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.PUT)
    public
    @ResponseBody
    void put(Principal principal,
             @RequestBody Collection<Long> taskIds) {
        throw new UnsupportedOperationException();
    }

    /**
     * POST - Add a new task to a user's collection of current tasks.
     *
     * @param username String username of user to add current task to
     * @param taskDTO  Task to add.
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.POST,
                    consumes = {"application/todo.webapp.dto.TaskDTO+json",
                            "application/json"})
    public
    @ResponseBody
    Map<String, Object> post(Principal principal,
              @RequestBody TaskDTO taskDTO,
              HttpServletRequest request,
              HttpServletResponse response) {

        taskDTO.setComplete(false);
        return tasksUserController.post(taskDTO, principal, response);
    }

    /**
     * DELETE - method not supported.
     *
     * @param response Set response status code to http method not allowed.
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.DELETE)
    public
    @ResponseBody
    void delete(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

}
