/*
 * File: PersistenceConfig.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.persistence;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import todo.hibernate.entities.Task;
import todo.persistence.hibernate.ClassArgInvocationHandlerDecorator;
import todo.persistence.hibernate.HibernateSupportedDaoInvocationHandler;

import java.beans.EventHandler;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Will O'Brien
 */
@Configuration
public class PersistenceConfig {

//    @Bean
//    public LocalSessionFactoryBean getSessionFactory() {
//        return new LocalSessionFactoryBean();
//    }
//
//    @Bean
//    public HibernateSupportedDaoInvocationHandler handler(SessionFactory sf) {
//        return new HibernateSupportedDaoInvocationHandler(sf);
//    }
//
//    @Bean
//    public TaskDao taskDao() {
//        Class taskDaoClass = TaskDao.class;
//        return (TaskDao) Proxy.newProxyInstance(taskDaoClass.getClassLoader(),
//                                                new Class[]{taskDaoClass},
//                                                this.getHandler(Task.class,
//                                                                this.getSessionFactory().getObject()));
//    }
//
//    public InvocationHandler getHandler(Class clazz, SessionFactory sf) {
//        return new ClassArgInvocationHandlerDecorator(clazz,
//                                                      handler(sf));
//    }
    
//    @Bean
//    public TaskDao taskDao() {
//        Class[] classes = new Class[]{TaskDao.class};
//        Class clazz = TaskDao.class;
//        InvocationHandler handler = new InvocationHandler() {
//            @Override
//            public Object invoke(Object o, Method method, Object[] objects)
//                    throws Throwable {
//                return null;
//            }
//        };
//
//        return (TaskDao) Proxy.newProxyInstance(clazz.getClassLoader(),
//                                                    classes, handler);
//    }

}
