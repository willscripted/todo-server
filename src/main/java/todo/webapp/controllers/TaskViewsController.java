/*
* File: TaskController.java
* Author: Will O'Brien
* Copyright: Will O'Brien (c) 2011
*/
package todo.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import todo.persistence.TaskDao;

import java.util.Collection;

/**
* @author Will O'Brien
*/
@Controller
@RequestMapping("/tasks/")
public class TaskViewsController {

    private TaskDao taskDao;

    @Autowired
    public TaskViewsController(TaskDao taskDao) {
        this.taskDao = taskDao;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewCurrent() {
        ModelAndView mav = new ModelAndView("tasks/current");

        // Add current tasks
        Collection tasks = taskDao.findCurrentTasks();
        mav.addObject("tasks",
                      tasks);

        return mav;
    }

    @RequestMapping(method = RequestMethod.GET,
                    value = "pending/")
    public ModelAndView viewPending() {
        ModelAndView mav = new ModelAndView("tasks/pending");

        // Add pending tasks
        Collection tasks = taskDao.findPendingTasks();
        mav.addObject("tasks",
                      tasks);

        return mav;
    }

    @RequestMapping(method = RequestMethod.GET,
                    value = "add/")
    public String addTasks() {
        return "tasks/add";
    }

}
