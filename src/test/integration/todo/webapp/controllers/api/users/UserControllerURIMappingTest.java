package todo.webapp.controllers.api.users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isNull;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.springframework.http.MediaType;
import todo.domain.User;
import todo.webapp.controllers.URIMappingTest;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Assert correct handling methods of controller are being executed.
 */
public class UserControllerURIMappingTest
        extends URIMappingTest<UserController> {

    private static final String RESOURCE_URI = "/api/users/joe/";
    
    private UserController mockController;

    public UserControllerURIMappingTest() {
        super(UserController.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();

        { // Set media types
            List<MediaType> types = new ArrayList<MediaType>();
            types.add(new MediaType("application",
                                    "todo.domain.User+json"));
            setSupportedMediaTypes(types);
        }

        mockController = getMockController();
        
        request.setRequestURI(RESOURCE_URI);
    }

    @After
    public void after() throws Exception {
        replay(mockController);
        
        handle();
        
        verify(mockController);
    }

    @Test
    public void testGet() throws Exception {
        // Set up request
        request.setMethod("GET");
        request.addHeader("accept",
                          "application/todo.domain.User+json");

        // Set expected behavior
        expect(mockController.get("joe",
                                  response)).andReturn(null);
    }

    @Test
    public void testPutUpdate() throws Exception {
        // Set up request
        request.setMethod("PUT");
        request.setContentType("application/todo.domain.User+json");

        // Program mock
        mockController.putUpdate(eq("joe"),
                                 isNull(User.class),
                                 anyObject(HttpServletResponse.class));
    }

    @Test
    public void testPost() throws Exception {
        request.setMethod("POST");

        mockController.post(response);
    }

    @Test
    public void testDelete() throws Exception {
        request.setMethod("DELETE");

        mockController.delete("joe");
    }
}
