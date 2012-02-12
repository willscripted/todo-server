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
public class UsersRootControllerURIMappingTest
        extends URIMappingTest<UsersRootController> {

    private static final String RESOURCE_URI = "/api/users/";


    @Before
    public void setUp() throws Exception {
        super.setUp();

        request.setRequestURI(RESOURCE_URI);
    }

    @Override
    protected Class getT() {
        return UsersRootController.class;
    }

    @Override
    protected List<MediaType> getSupportedMediaTypes() {
        ArrayList<MediaType> mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(new MediaType("application",
                                     "todo.webapp.dto"
                                     + ".RegistrationForm"
                                     + "+json"));
        return mediaTypes;
    }

    @After
    public void tearDown() throws Exception {
        replay(controller);

        handle();

        verify(controller);
    }

    @Test
    public void testGet() throws Exception {
        request.setMethod("GET");

        expect(controller.getUsers()).andReturn(null);
    }

    @Test
    public void testPost() throws Exception {
        request.setMethod("POST");
        request.setContentType("application/todo.webapp.dto"
                               + ".RegistrationForm+json");

        expect(controller.registerUsername(anyObject(RegistrationForm.class),
                                               anyObject(HttpServletResponse.class))
        ).andReturn("");
    }

    @Test
    public void testPut() throws Exception {
        request.setMethod("PUT");

        controller.unsupported(anyObject(HttpServletResponse.class));
    }

    @Test
    public void testDelete() throws Exception {
        request.setMethod("DELETE");

        controller.unsupported(anyObject(HttpServletResponse.class));
    }
}
