/*
* File: TaskController.java
* Author: Will O'Brien
* Copyright: Will O'Brien (c) 2011
*/
package todo.webapp.controllers.api.tasks;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import todo.domain.Task;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Controller of all tasks - unfiltered.
 *
 * @author Will O'Brien
 */
@Controller
public class TasksRootController {
    
    private static final String RESOURCE_URI = "/api/tasks/";

    /**
     * Get a list of all Tasks in the application.
     *
     * @return List<Task> list of all tasks in the app.
     */
    @RequestMapping(value = RESOURCE_URI,
                    method = RequestMethod.GET)
    public List<Task> get() {
        throw new UnsupportedOperationException();
    }

    /**
     * Use of PUT, POST, and DELETE methods not allowed for this resource.
     *
     * @param response Http method not allowed response.
     */
    @RequestMapping(value = RESOURCE_URI,
                    method = {RequestMethod.PUT,
                              RequestMethod.POST,
                              RequestMethod.DELETE})
    public @ResponseBody void unsupported(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

}