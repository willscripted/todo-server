/*
 * File: FindByPrimaryKeyCommand.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.persistence.hibernate.commands;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @author Will O'Brien
 */
public class FindByPrimaryKeyCommand implements Command {

    @Transactional(propagation = Propagation.MANDATORY)
    public Object execute(Method method, Object[] args, Session session)
            throws Exception {
        try {
            Object ret = session.get((Class) args[args.length - 1],
                                     (Serializable) args[0]);
            return ret;
        } catch (HibernateException e) {
            throw new PersistenceException("Could not retrieve object from "
                                           + "data store",
                                           e);
        }
    }

}
