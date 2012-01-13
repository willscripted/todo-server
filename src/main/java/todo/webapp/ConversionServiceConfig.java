package todo.webapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: will
 * Date: 11/3/11
 * Time: 12:36 PM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class ConversionServiceConfig {

    @Bean
    public ConversionService conversionService() {
        FormattingConversionService cs =
                new DefaultFormattingConversionService();
        {
            cs.removeConvertible(Date.class,
                                 String.class);

            cs.removeConvertible(String.class,
                                 Date.class);
            
            cs.addFormatter(dateFormatter());
        }


        return (ConversionService) cs;
    }

    public DateFormatter dateFormatter() {
        return new DateFormatter("dd MMM yyyy");
    }

}
