/*
 * File: PersistenceConfig.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.persistence;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import todo.domain.Task;
import todo.domain.User;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author Will O'Brien
 */
@Configuration()
public class PersistenceConfig implements ApplicationContextAware {

    private DaoInvocationHandler hibernateHandler;

    @Bean
    public TaskDao taskDao() {
        return getDao(Task.class,
                      TaskDao.class);
    }

    @Bean
    public UserDao userDao() {
        return getDao(User.class,
                      UserDao.class);
    }

    public <T extends GenericDao> T getDao(Class instanceClass,
                                           Class<T> daoInterface) {


        ClassLoader loader = daoInterface.getClassLoader();

        InvocationHandler decoratedHandler = new
                ClassArgInvocationHandlerDecorator(instanceClass,
                                                   this.hibernateHandler);

        return (T) Proxy.newProxyInstance(loader,
                                          new Class[]{daoInterface},
                                          decoratedHandler);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.hibernateHandler = applicationContext.getBean
                (DaoInvocationHandler.class);
    }
}
