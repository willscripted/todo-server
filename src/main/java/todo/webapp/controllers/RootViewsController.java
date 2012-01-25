/*
 * File: RootController.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.tiles2.TilesView;

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
    public final String index() {
        return "base";
    }
}
