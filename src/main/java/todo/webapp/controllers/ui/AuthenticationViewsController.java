/*
 * File: AuthenticationController.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */
package todo.webapp.controllers.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Will O'Brien
 */
@Controller
public class AuthenticationViewsController {

    /**
     * Retrieve the page with the login form.
     *
     * @return String - "auth-reg/login"
     */
    @RequestMapping("/login/")
    public final String login() {
        return "auth/login";
    }


    /**
     * Retrieve the page with the login form with an invalid indicator.
     *
     * @return ModelAndView - View name:    "auth-reg/login"
     *         Model:        { "invalid" : true }
     */
    @RequestMapping("/login/invalid/")
    public final ModelAndView invalidLogin() {
        ModelAndView mav = new ModelAndView("auth/login");
        mav.addObject("invalid",
                      true);
        return mav;
    }
}
