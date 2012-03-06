package todo.webapp.controllers.api.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import todo.domain.Task;
import todo.services.TaskService;
import todo.webapp.dto.TaskDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@Controller
public class TasksUserCurrentTaskController {


    private static final String CLASS_REQUEST_MAPPING =
            "/api/tasks/{username}/current/{id}";

    @Autowired
    private TaskService taskService;

    @Autowired
    private TasksUserTaskController tasksUserTaskController;

    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.GET)
    public
    @ResponseBody
    void get(@PathVariable Long id,
             @PathVariable String username,
             HttpServletResponse response) throws IOException {

        response.sendRedirect("/api/tasks/" + username + "/" + id);

    }

    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.PUT,
                    consumes = "application/todo.webapp.dto.TaskDTO+json")
    public @ResponseBody void put(@PathVariable Long id,
                    @PathVariable String username,
                    @RequestBody TaskDTO taskDTO) {
        tasksUserTaskController.put(id, username, taskDTO);
    }

    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.DELETE)
    public
    @ResponseBody
    void delete(@PathVariable Long id) {
        Task task = taskService.getTask(id);
        task.setComplete(true);
        taskService.updateTask(task);
    }

    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.POST)
    public
    @ResponseBody
    void post(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }


}
