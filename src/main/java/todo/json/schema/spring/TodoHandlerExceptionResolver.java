package todo.json.schema.spring;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: Will O'Brien
 * Date: 1/14/12
 * Time: 3:16 PM
 */
public class TodoHandlerExceptionResolver extends
                                          DefaultHandlerExceptionResolver {

    @Override
    protected ModelAndView handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpServletRequest request,
            HttpServletResponse response, Object handler) throws IOException {

        if(ex instanceof HttpMessageUnprocessableException) {
            response.setStatus(422);
            return null;
        }
        return super.handleHttpMessageNotReadable(ex,
                                                  request,
                                                  response,
                                                  handler);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
