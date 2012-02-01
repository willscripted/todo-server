package todo.webapp.controllers.api.users;

import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Assert correct handling methods of controller are being executed.
 */
public class SpecificUserControllerURIMappingTest {


    private AnnotationMethodHandlerAdapter handlerAdapter;
    private HttpMessageConverter converter;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    private SpecificUserController controller;

    @Before
    public void setUp() throws Exception {

        controller = createMock(SpecificUserController.class);

        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        handlerAdapter = new AnnotationMethodHandlerAdapter();

        { // Create custom converters
            converter = createNiceMock(HttpMessageConverter.class);
            List<MediaType> supportedTypes = new ArrayList<MediaType>();
            supportedTypes.add(new MediaType("application",
                                             "todo.domain.User+json"));
            expect(converter.getSupportedMediaTypes())
                    .andReturn(supportedTypes);
            expect(converter.canRead(isA(Class.class), isA(MediaType.class)))
                    .andReturn(true);

            replay(converter);
        }

        handlerAdapter.setMessageConverters(new HttpMessageConverter[]{converter});
    }

    @Test
    public void testGet() throws Exception {
        // Set up request
        request.setRequestURI("/api/users/joe/");
        request.setMethod("GET");
        request.addHeader("accept",
                          "application/todo.domain.User+json");

        expect(controller.get("joe",
                              response)).andReturn(null);
        replay(controller);

        // Get handler, then handle
        handlerAdapter.handle(request,
                              response,
                              controller);

        // assert correct mocks were caught
        verify(controller);


    }

    @Test
    public void testPutUpdate() throws Exception {
        request.setRequestURI("/api/users/joe/");
        request.setMethod("PUT");
        request.setContentType("application/todo.domain.User+json");

        // Program mock
        controller.putUpdate("joe",
                             null,
                             response);
        replay(controller);

        handlerAdapter.handle(request,
                              response,
                              controller);

        verify(controller);

    }

    @Test
    public void testPost() throws Exception {
        request.setRequestURI("/api/users/joe/");
        request.setMethod("POST");
        
        controller.post(response);
        replay(controller);

        handlerAdapter.handle(request, response, controller);

        verify(controller);
    }

    @Test
    public void testDelete() throws Exception {
        request.setRequestURI("/api/users/joe/");
        request.setMethod("DELETE");

        controller.delete("joe");
        replay(controller);

        handlerAdapter.handle(request, response, controller);

        verify(controller);
    }
}
