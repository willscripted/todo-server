/*
 * File: RemoveCommand.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import todo.persistence.commands.RemoveCommand;

import java.lang.reflect.Method;

/**
 * Class that encapsulates hibernate logic for removing an entity from
 * persistence.
 *
 * @author Will O'Brien
 */
public final class HibernateRemoveCommand extends HibernateCommand implements
                                                             RemoveCommand {

    /**
     * Create command.
     * @param sf SessionFactory.
     */
    public HibernateRemoveCommand(final SessionFactory sf) {
        super(sf);
    }

    /**
     * Remove an entity from persistence.
     *
     * @param method Method whose execution is being handled
     * @param args   [0] - Entity to remove.Does not necessarily need to
     *               exist in the repository.
     * @return null
     */
    @Override
    public Object execute(final Method method,
                          final Object[] args) {
        Session session = getSessionFactory().getCurrentSession();
        session.delete(args[0]);

        return null;
    }
}
