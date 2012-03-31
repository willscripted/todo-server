/*
 * File: RootController.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.webapp.controllers.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * Serves static pages at root of host path.
 *
 * @author Will O'Brien
 */
@Controller
public final class RootViewsController {

    /**
     * Return index page.
     *
     * @return ModelAndView Prepped with data to support all displayed modules.
     */
    @RequestMapping("")
    public String index() {
        return "base";
    }
    
    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/tos")
    public ModelAndView termsOfService(final HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("tos");

        String referer = request.getHeader("referer");
        if(referer.equals("/registration/")) {
            mav.addObject("fromRegistration", true);
        }

        return mav;
    }

    @RequestMapping("/humans.txt")
    public String humansTxt() {
        return "humans.txt";
    }
    
    @RequestMapping("/robots.txt")
    public String robotsTxt() {
        return "robots.txt";
    }

}
