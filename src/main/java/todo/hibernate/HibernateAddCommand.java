/*
 * File: AddCommand.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import todo.persistence.commands.AddCommand;

import javax.persistence.EntityExistsException;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Command encapsulating hibernate's add entity logic.
 *
 * @author Will O'Brien
 */
public final class HibernateAddCommand extends HibernateCommand implements
                                                          AddCommand {


    /**
     * Create a new HibernateAddCommand for the given SessionFactory.
     * @param sf SessionFactory this add command should act upon.
     */
    public HibernateAddCommand(final SessionFactory sf) {
        super(sf);
    }

    /**
     *
     * @param method Method whose execution is being handled
     * @param args   [0] - Entity to be added
     * @return Serializable id of the added entity
     * @throws EntityExistsException if object to persist has an id set that
     * already exists in the repository
     */
    @Override
    public Serializable execute(final Method method,
                                final Object[] args)
            throws EntityExistsException {
        Session session = getSessionFactory().getCurrentSession();
        return session.save(args[0]);
    }
}

