/*
 * File: AddCommand.java
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
public class AddCommand extends TransactionalCommand {

    public AddCommand() {
    }

    @Override
    protected Object command(Object[] args, Session session) throws Exception {
        try {
            return session.save(args[0]);
        } catch (HibernateException e) {
            throw new PersistenceException("Unable to add object to datastore",
                                           e);
        }
    }

}
