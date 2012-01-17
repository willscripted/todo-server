/*
 * File: FindAllCommand.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */
package todo.persistence.hibernate.commands;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.lang.reflect.Method;

/**
 * @author Will O'Brien
 */
public class FindAllCommand implements Command {

    @Transactional(propagation = Propagation.MANDATORY)
    public Object execute(Method method, Object[] args, Session session)
            throws Exception {
        Class target = (Class) args[args.length - 1];
        try {
            Criteria criteria = session.createCriteria(target);
            return criteria.list();
        } catch (HibernateException e) {
            throw new PersistenceException("Could not retrieve objects",
                                           e);
        }
    }
}
