package todo.test.functional;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * From <a href="https://gist.github.com/1333097">max747's public gist</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"classpath:/WEB-INF/todo-servlet.xml",
                                    "classpath:/WEB-INF/applicationContext-security.xml"})
public abstract class ControllerFunctionalTestBase {

    @Autowired
    protected ApplicationContext context;

    protected MockHttpServletRequest request;
    protected MockHttpServletResponse response;
    protected HandlerAdapter ha;
    protected HandlerMapping mapping;


    
    @Before
    public void setup() {
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        
        response = new MockHttpServletResponse();
        response.setOutputStreamAccessAllowed(true);
        response.setCharacterEncoding("UTF-8");
        
        ha = context.getBean(RequestMappingHandlerAdapter.class);
        mapping = context.getBean(RequestMappingHandlerMapping.class);
    }


    public ModelAndView handle(HttpServletRequest req, 
                               HttpServletResponse resp) {
        try {
            return ha.handle(request, response, mapping.getHandler(request)
                                                       .getHandler());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
