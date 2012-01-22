package todo.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import todo.domain.RegistrationForm;
import todo.persistence.RegistrationFormDao;
import todo.services.UserService;

import javax.servlet.http.HttpServletResponse;

/**
 * Controls the registration-form resource.
 *
 * @author Will O'Brien
 */
@Controller
@RequestMapping("/registration-form/")
public class RegistrationFormController {

    @Autowired
    private RegistrationFormDao formDao;

    private UserService userService;

    /**
     * GET registration splash page.
     *
     * @return String view.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "registration-form/splash";
    }

    /**
     * Update not allowed. Return http status code 405 (Method not allowed).
     *
     * @param response HttpServletResponse
     * @return String empty string
     */
    @RequestMapping(method = RequestMethod.PUT)
    public
    @ResponseBody
    String update(HttpServletResponse response) {

        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);

        return "";
    }

    /**
     * Create a form and redirect to view the form.
     *
     * @return redirect to form
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createNew() {
        RegistrationForm form = new RegistrationForm();

        formDao.add(form);

        return "redirect:" + form.getId();
    }

    /**
     * Delete note allowed. Return http status code 405 (Method not allowed)
     *
     * @param response HttpServletResponse
     * @return String empty string
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public
    @ResponseBody
    String delete(HttpServletResponse response) {

        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);

        return "";
    }

    /**
     * Soft redirect to incomplete form part, if form is valid,
     * displays form for submitting.
     */
    @RequestMapping(value = "{id}",
                    method = RequestMethod.GET)
    public String getForm(@PathVariable Long id) {

        RegistrationForm form = formDao.findByPrimaryKey(id);

        if (userService.usernameExists(form.getUsername())) {
            return "registration-form/form";
        } else if (!form.getAgreeToTOS()) {
            return "registration-form/tos";
        } else {
            return "registration-form/complete";
        }
    }

    /**
     * Update not allowed. Return http status code 405 (Method not allowed).
     *
     * @param response HttpServletResponse
     * @return String empty string
     */
    @RequestMapping(value = "{id}",
                    method = RequestMethod.PUT)
    public
    @ResponseBody
    String updateForm(HttpServletResponse response) {

        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);

        return "";
    }

    /**
     * POST not allowed on registration form. Return http status code 405
     * (Method not allowed).
     *
     * @param response HttpServletResponse
     * @return String empty string
     */
    @RequestMapping(value = "{id}",
                    method = RequestMethod.POST)
    public
    @ResponseBody
    String postToId(HttpServletResponse response) {

        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);

        return "";
    }


    /**
     * Delete registration form if it exists. Redirect to root page..
     *
     * @param id Long id of registration form to delete.
     * @return redirect to root page.
     */
    @RequestMapping(value = "{id}",
                    method = RequestMethod.DELETE)
    public String deleteRegistration(@PathVariable Long id) {

        RegistrationForm form = formDao.findByPrimaryKey(id);
        if (form != null) {
            formDao.remove(form);
        }

        return "redirect:/";
    }

}
