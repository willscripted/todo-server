package todo.webapp.controllers.api.tasks;

import org.apache.commons.collections.set.SynchronizedSet;
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
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller managing tasks of a specific user
 *
 * @author Will O'Brien
 */
@Controller
public class TasksUserController {

    private static final String CLASS_REQUEST_MAPPING = "/api/tasks/";

    @Autowired
    private TaskService taskService;

    @Autowired
    private Mapper mapper;

    @Autowired
    private UserService userService;

    /**
     * GET - Retrieve a list of user's tasks
     *
     * @return List<Task> application/json
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<TaskDTO> get(Principal principal) {

        Collection<Task> tasks =
                taskService.getTasksOfUser(principal.getName());
        Collection<TaskDTO> taskDTOs = new ArrayList<TaskDTO>(tasks.size());

        for (Task t : tasks) {
            taskDTOs.add(mapper.map(t,
                                    TaskDTO.class));
        }

        return taskDTOs;
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
     * @param taskDTO TaskDTO
     * @return Serializable id of new task if post is successful,
     *         else response with appropriate status code.
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.POST,
                    consumes = "application/todo.webapp.dto.TaskDTO+json")
    public
    @ResponseBody
    Map<String, Object> post(@RequestBody TaskDTO taskDTO,
              Principal principal,
              HttpServletResponse response) {
        
        if( ! this.isLong(taskDTO.getId())) {
            taskDTO.setId("");
        }
        
        Task task = mapper.map(taskDTO,
                               Task.class);
        User user = userService.getUserByUsername(principal.getName());
        task.setUser(user);
        Long id = taskService.addTask(task);
        task.setId(id);

        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setHeader("Location",
                           id.toString());
        
        Map<String, Object> jsonResponse = new HashMap<String, Object>();
        jsonResponse.put("success", true);
        jsonResponse.put("id", id);

        ArrayList<TaskDTO> returnArray = new ArrayList<TaskDTO>();
        returnArray.add(mapper.map(task, TaskDTO.class));
        jsonResponse.put("tasks", returnArray);

        return jsonResponse;
    }

    /**
     * DELETE - remove all tasks of user.
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.DELETE)
    public
    @ResponseBody
    void delete(Principal principal) {
        throw new UnsupportedOperationException();
    }

    private boolean isLong(String string) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(string);
        return m.matches();
    }
}
