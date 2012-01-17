/*
 * File: UpdateCommand.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.persistence.hibernate.commands;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.lang.reflect.Method;

/**
 * @author Will O'Brien
 */
public class UpdateCommand implements Command {

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Object execute(Method method, Object[] args, Session session)
            throws Exception {

        try {
            return session.merge(args[0]);
        } catch (HibernateException e) {
            throw new PersistenceException("Unable to update object",
                                           e);
        }

    }
}
