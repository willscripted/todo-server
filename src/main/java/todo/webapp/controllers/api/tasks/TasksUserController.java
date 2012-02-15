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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Controller managing tasks of a specific user
 *
 * @author Will O'Brien
 */
@Controller
public class TasksUserController {

    private static final String CLASS_REQUEST_MAPPING = "/api/tasks/{username}";

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private Mapper mapper;

    @Autowired
    private UserService userService;

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
    Collection<TaskDTO> get(@PathVariable String username) {
        Collection<Task> tasks = taskService.getTasksOfUser(username);
        Collection<TaskDTO> taskDTOs = new ArrayList<TaskDTO>(tasks.size());
        
        for(Task t : tasks) {
            taskDTOs.add(mapper.map(t, TaskDTO.class));
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
     * @param username String username of user to create new task for.
     * @param taskDTO TaskDTO
     * @return Serializable id of new task if post is successful,
     *         else response with appropriate status code.
     */
    @RequestMapping(value = CLASS_REQUEST_MAPPING,
                    method = RequestMethod.POST,
                    consumes = "application/todo.webapp.dto.TaskDTO+json")
    public
    @ResponseBody
    Long post(@RequestBody TaskDTO taskDTO,
              @PathVariable String username,
              HttpServletResponse response) {
        Task task = mapper.map(taskDTO, Task.class);
        User user = userService.getUserByUsername(username);
        task.setUser(user);
        Long id = taskService.addTask(task);

        response.setStatus(HttpServletResponse.SC_CREATED);

        return id;
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
