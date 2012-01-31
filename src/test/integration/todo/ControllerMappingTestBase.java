package todo;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Base test class for asserting the expected handler is handling the request.
 *
 * @author Will O'Brien
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"classpath:/WEB-INF/todo-servlet.xml",
                                    "classpath:/WEB-INF/applicationContext-security.xml"})
public class ControllerMappingTestBase {

    @Autowired
    protected ApplicationContext context;

    protected MockHttpServletRequest request;
    protected RequestMappingInfoHandlerMapping mapping;

    @Before
    public void setup() {
        mapping = context.getBean(RequestMappingInfoHandlerMapping.class);
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
    }
    
    public Object getHandler(HttpServletRequest request) throws Exception {
        HandlerExecutionChain handler = mapping.getHandler(request);
        return handler.getHandler();
    }
    
    
    
}
