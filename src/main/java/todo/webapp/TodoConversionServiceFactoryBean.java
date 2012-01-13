package todo.webapp;

import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;

/**
 * Created by IntelliJ IDEA.
 * User: will
 * Date: 11/3/11
 * Time: 12:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class TodoConversionServiceFactoryBean
        extends ConversionServiceFactoryBean {

    private ConversionService cs;

    public TodoConversionServiceFactoryBean(ConversionService cs) {
        super();
        this.cs = cs;
    }

    @Override
    public ConversionService getObject() {
        return this.cs;
    }
}
