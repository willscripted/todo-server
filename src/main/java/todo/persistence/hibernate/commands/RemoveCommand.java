/*
 * File: RemoveCommand.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.persistence.hibernate.commands;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.PersistenceException;

/**
 * @author Will O'Brien
 */
public class RemoveCommand extends TransactionalCommand {

    @Override
    protected Object command(Object[] args, Session session) throws Exception {
        try {
            session.delete(args[0]);
            return null;
        } catch (HibernateException e) {
            throw new PersistenceException("Could not delete object",
                                           e);
        }
    }


}
