package todo.webapp.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import todo.persistence.UserDao;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

/**
 * User: Will O'Brien
 * Date: 1/15/12
 * Time: 6:24 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:WEB-INF/todo-servlet.xml")
public class UserResourceControllerTest {

    @Autowired
    private ApplicationContext applicationContext;
    private HandlerAdapter handlerAdapter;
    
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    
    private UserDao mockUserDao;
    private UserResourceController controller;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        handlerAdapter = applicationContext.getBean(HandlerAdapter.class);
        
        mockUserDao = createMock(UserDao.class);
        controller = new UserResourceController(mockUserDao);
    }

    @Test
    public void testGetUsers() throws Exception {
        // Program mock

        // Test

        // Assert
    }

    @Test
    public void testUnsupported() throws Exception {
        // Program mock
        replay(mockUserDao);

        // Test


        // Assert

    }
}
