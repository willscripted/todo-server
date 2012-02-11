package todo.webapp.controllers.api.users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import todo.webapp.controllers.URIMappingTest;
import todo.webapp.dto.RegistrationForm;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

/**
 *
 */
public class RootControllerURIMappingTest
        extends URIMappingTest<RootController> {

    private static final String RESOURCE_URI = "/api/users/";

    private RootController mockController;

    public RootControllerURIMappingTest() {
        super(RootController.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();

        {
            // Set media types
            List<MediaType> types = new ArrayList<MediaType>();
            types.add(new MediaType("application",
                                    "todo.webapp.dto.RegistrationForm+json"));
            setSupportedMediaTypes(types);
        }

        mockController = getMockController();

        request.setRequestURI(RESOURCE_URI);
    }

    @After
    public void tearDown() throws Exception {
        replay(mockController);

        handle();

        verify(mockController);
    }

    @Test
    public void testGet() throws Exception {
        request.setMethod("GET");

        expect(mockController.getUsers()).andReturn(null);
    }

    @Test
    public void testPost() throws Exception {
        request.setMethod("POST");
        request.setContentType("application/todo.webapp.dto"
                               + ".RegistrationForm+json");

        expect(mockController.registerUsername(anyObject(RegistrationForm.class),
                                               anyObject(HttpServletResponse.class))
        ).andReturn("");
    }

    @Test
    public void testPut() throws Exception {
        request.setMethod("PUT");

        mockController.unsupported(anyObject(HttpServletResponse.class));
    }

    @Test
    public void testDelete() throws Exception {
        request.setMethod("DELETE");

        mockController.unsupported(anyObject(HttpServletResponse.class));
    }
}
