/*
 * File: PersistenceConfig.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.webapp.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import todo.hibernate.entities.Task;
import todo.hibernate.entities.User;
import todo.persistence.GenericDao;
import todo.persistence.TaskDao;
import todo.persistence.UserDao;
import todo.persistence.hibernate.ClassArgInvocationHandlerDecorator;
import todo.persistence.hibernate.HibernateSupportedDaoInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author Will O'Brien
 */
@Configuration()
public class PersistenceConfig implements ApplicationContextAware {

    private HibernateSupportedDaoInvocationHandler hibernateHandler;

    @Bean
    public TaskDao taskDao() {
        return (TaskDao) getDao(Task.class,
                                TaskDao.class);
    }

    @Bean
    public UserDao userDao() {
        return (UserDao) getDao(User.class,
                                UserDao.class);
    }


    public GenericDao getDao(Class instanceClass,
                             Class<? extends GenericDao> daoInterface) {

        ClassLoader loader = instanceClass.getClassLoader();

        InvocationHandler decoratedHandler = new
                ClassArgInvocationHandlerDecorator(instanceClass,
                                                   this.hibernateHandler);

        return (GenericDao) Proxy.newProxyInstance(loader,
                                                   new Class[]{daoInterface},
                                                   decoratedHandler);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.hibernateHandler = applicationContext.getBean
                (HibernateSupportedDaoInvocationHandler.class);
    }
}
