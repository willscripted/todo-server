/*
 * File: FindByPrimaryKeyCommand.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import todo.persistence.commands.FindByPrimaryKeyCommand;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Command that encapsulates the logic of a finding an entity by its primary
 * key.
 *
 * @author Will O'Brien
 */
public final class HibernateFindByPrimaryKeyCommand extends HibernateCommand
        implements FindByPrimaryKeyCommand {

    /**
     * Create new.
     * @param sf SessionFactory to use.
     */
    public HibernateFindByPrimaryKeyCommand(final SessionFactory sf) {
        super(sf);
    }

    /**
     * Execute retrieval of entity belonging to the primary key passed.
     *
     * @param method Method whose execution is being handled
     * @param args   [0] - Serializable - the primary key
     *               [1] - Class - class of entity to find
     * @return Entity found
     * @throws EntityNotFoundException if no entity was found
     */
    @Override
    public Object execute(final Method method,
                          final Object[] args)
            throws EntityNotFoundException {

        Session session = getSessionFactory().getCurrentSession();
        return session.get((Class) args[1],
                           (Serializable) args[0]);

    }
}

