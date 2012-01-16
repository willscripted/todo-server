package todo.test.functional;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.ServletContextAwareProcessor;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * From <a href="https://gist.github.com/1333097">max747's public gist</a>
 */
public class AnnotationConfigWebApplicationContextLoader extends
                                                         AnnotationConfigContextLoader {

    @Override
    protected void customizeBeanFactory(
            DefaultListableBeanFactory beanFactory) {

        MockServletContext servletContext = new MockServletContext();

        // copy of GenericWebApplicationContext#postProcessBeanFactory
        beanFactory.addBeanPostProcessor(new ServletContextAwareProcessor
                                         (servletContext));
        beanFactory.ignoreDependencyInterface(ServletContextAware.class);

        WebApplicationContextUtils.registerWebApplicationScopes(beanFactory,
                                                                servletContext);
        WebApplicationContextUtils.registerEnvironmentBeans(beanFactory, servletContext);

    }
}
