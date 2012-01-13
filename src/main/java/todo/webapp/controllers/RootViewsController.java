/*
 * File: RootController.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author Will O'Brien
 */
@Controller
public class RootViewsController {




    /**
     * Return index page.
     *
     * @return ModelAndView Preped with data to support all displayed modules.
     */
    @RequestMapping("")
    public final ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");

        // Get data for populating count module
        Map<String, Object> model = mav.getModel();

        return mav;
    }
}
