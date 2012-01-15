/*
 * File: PersistenceConfig.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.persistence;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import todo.hibernate.entities.Task;
import todo.persistence.hibernate.ClassArgInvocationHandlerDecorator;
import todo.persistence.hibernate.HibernateSupportedDaoInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author Will O'Brien
 */
@Configuration
public class PersistenceConfig {

    @Bean
    public HibernateSupportedDaoInvocationHandler handler(SessionFactory sf) {

        return new HibernateSupportedDaoInvocationHandler(sf);

    }

    @Bean
    public TaskDao taskDao(
            HibernateSupportedDaoInvocationHandler hibernateHandler) {


        ClassLoader loader = TaskDao.class.getClassLoader();

        InvocationHandler decoratedHandler = new
                ClassArgInvocationHandlerDecorator(Task.class,
                                                   hibernateHandler);

        return (TaskDao) Proxy.newProxyInstance(loader,
                                                new Class[]{TaskDao.class},
                                                decoratedHandler);

    }
}
