package todo.webapp.controllers;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import java.io.IOException;
import java.util.List;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;

/**
 * Support class for ensuring controller methods are properly mapped.
 */
public abstract class URIMappingTest<T> {

    private AnnotationMethodHandlerAdapter handlerAdapter;

    protected T controller;
    protected MockHttpServletRequest request;
    protected MockHttpServletResponse response;

    public void setUp() throws Exception {

        controller = (T) createMock(getT());

        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        handlerAdapter = new AnnotationMethodHandlerAdapter();
        handlerAdapter.setMessageConverters(new HttpMessageConverter[]{getConverter()});
    }

    private HttpMessageConverter getConverter()
            throws IOException {

        HttpMessageConverter converter = createNiceMock
                (HttpMessageConverter.class);

        expect(converter.getSupportedMediaTypes())
                .andReturn(getSupportedMediaTypes());

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

        return converter;
    }

    public void handle() throws Exception {
        handlerAdapter.handle(request,
                              response,
                              controller);
    }

    protected abstract Class getT();

    protected abstract List<MediaType> getSupportedMediaTypes();
}
