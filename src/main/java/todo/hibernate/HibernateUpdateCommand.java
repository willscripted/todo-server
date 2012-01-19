/*
 * File: UpdateCommand.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import todo.persistence.commands.UpdateCommand;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Method;

/**
 * Command that encapsulates logic for a hibernate update.
 *
 * @author Will O'Brien
 */
public final class HibernateUpdateCommand extends HibernateCommand implements
                                                             UpdateCommand {
    /**
     * Create new.
     * @param sf SessionFactory.
     */
    public HibernateUpdateCommand(final SessionFactory sf) {
        super(sf);
    }

    /**
     * Execute update with hibernate persistence logic.
     *
     * @param method Method whose execution is being handled
     * @param args   [0] - entity to be updated
     * @return null
     * @throws EntityNotFoundException if an entry for the the given id does not
     * exist
     */
    @Override
    public Object execute(final Method method,
                          final Object[] args)
            throws EntityNotFoundException {

        Session session = getSessionFactory().getCurrentSession();
        session.update(args[0]);

        return null;
    }
}
