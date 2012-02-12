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
public class UsersUserControllerURIMappingTest
        extends URIMappingTest<UsersUserController> {

    private static final String RESOURCE_URI = "/api/users/joe/";


    @Before
    public void setUp() throws Exception {
        super.setUp();

        request.setRequestURI(RESOURCE_URI);
    }

    @Override
    protected Class getT() {
        return UsersUserController.class;
    }

    @Override
    protected List<MediaType> getSupportedMediaTypes() {
        List<MediaType> types = new ArrayList<MediaType>();
        types.add(new MediaType("application",
                                "todo.webapp.dto"
                                + ".RegistrationForm"
                                + "+json"));
        return types;
    }

    @After
    public void after() throws Exception {
        replay(controller);
        
        handle();
        
        verify(controller);
    }

    @Test
    public void testGet() throws Exception {
        // Set up request
        request.setMethod("GET");
        request.addHeader("accept",
                          "application/todo.domain.User+json");

        // Set expected behavior
        expect(controller.get("joe",
                                  response)).andReturn(null);
    }

    @Test
    public void testPutUpdate() throws Exception {
        // Set up request
        request.setMethod("PUT");
        request.setContentType("application/todo.domain.User+json");

        // Program mock
        controller.putUpdate(eq("joe"),
                                 isNull(User.class),
                                 anyObject(HttpServletResponse.class));
    }

    @Test
    public void testPost() throws Exception {
        request.setMethod("POST");

        controller.post(response);
    }

    @Test
    public void testDelete() throws Exception {
        request.setMethod("DELETE");

        controller.delete("joe");
    }
}
