package todo.webapp.controllers;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;

/**
 * Support class for ensuring controller methods are properly mapped.
 */
public class URIMappingTest<T> {

    private final Class controllerClass;
    private T controller;

    private AnnotationMethodHandlerAdapter handlerAdapter;
    private List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();

    protected MockHttpServletRequest request;
    protected MockHttpServletResponse response;


    protected URIMappingTest(Class<T> clazz) {
        controllerClass = clazz;
    }


    protected void setSupportedMediaTypes(List<MediaType> types) {
        supportedMediaTypes = types;
    }

    public void setUp() throws Exception {

        controller = (T) createMock(controllerClass);

        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        handlerAdapter = new AnnotationMethodHandlerAdapter();

        HttpMessageConverter converter = createNiceMock
                (HttpMessageConverter.class);

        { // Create custom converters
            expect(converter.getSupportedMediaTypes())
                    .andReturn(supportedMediaTypes);

            expect(converter.canRead(isA(Class.class),
                                     isA(MediaType.class)))
                    .andReturn(true)
                    .anyTimes();

            expect(converter.canWrite(isA(Class.class),
                                      isA(MediaType.class)))
                    .andReturn(true)
                    .anyTimes();


            expect(converter.read(isA(Class.class),
                                  isA(HttpInputMessage.class)))
                    .andReturn(null)
                    .anyTimes();
            replay(converter);
        }

        handlerAdapter.setMessageConverters(new HttpMessageConverter[]{converter});

    }

    public void handle() throws Exception {
        handlerAdapter.handle(request,
                              response,
                              controller);
    }

    public T getMockController() {
        return controller;
    }
}
