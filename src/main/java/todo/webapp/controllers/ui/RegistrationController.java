package todo.webapp.controllers.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Will O'Brien
 */
@Controller
@RequestMapping(value = "/registration",
                produces = "text/html",
                method = RequestMethod.GET)
public class RegistrationController {

    /**
     * Get a registration form for the new user.
     *
     * @return string redirect
     */
    @RequestMapping(value = "/")
    public String get(HttpServletRequest request) {

        return "registration/edit";

    }

    @RequestMapping(value = "/success")
    public String success() {
        return "registration/success";
    }

}
