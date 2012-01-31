package todo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import todo.domain.RegistrationForm;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class ConverterIntegrationTest {

    @Autowired
    private ApplicationContext context;


    @Test
    public void testConvertersWired() {

        AnnotationMethodHandlerAdapter bean =
                context.getBean(AnnotationMethodHandlerAdapter.class);

        HttpMessageConverter<?>[] messageConverters =
                bean.getMessageConverters();

        boolean canRead = false;
        for (int i = 0; i < messageConverters.length; i++) {
            List<MediaType> mediaTypes =
                    messageConverters[i].getSupportedMediaTypes();
            for (MediaType m : mediaTypes) {
                System.out
                        .println(m);
            }
            if (
                    messageConverters[i].canRead(RegistrationForm.class,
                                                 new MediaType(
                                                         "application", "todo.domain"
                                                         + ".RegistrationForm+json")
                    )) {
                canRead = true;
            }
        }
        assertTrue(canRead);

        System.out
                .println(messageConverters);

    }


}
