package todo.webapp.controllers.api.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import todo.webapp.controllers.URIMappingTest;

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
public class TasksRootControllerURIMappingTest extends URIMappingTest<TasksRootController> {

    private static final String RESOURCE_URI = "/api/tasks/";


    @Before
    public void setUp() throws Exception {
        super.setUp();

        request.setRequestURI(RESOURCE_URI);
    }

    @Override
    protected Class getT() {
        return TasksRootController.class;
    }

    @Override
    protected List<MediaType> getSupportedMediaTypes() {
        List<MediaType> types = new ArrayList<MediaType>();
        return types;
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

        expect(controller.get()).andReturn(null);
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

    @Test
    public void testPost() throws Exception {
        request.setMethod("POST");

        controller.unsupported(anyObject(HttpServletResponse.class));
    }
}
