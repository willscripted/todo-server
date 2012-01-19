/*
 * File: FindAllCommand.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */
package todo.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import todo.persistence.commands.FindAllCommand;

import java.lang.reflect.Method;

/**
 * Command that encapsulates logic for finding all instances of an entity.
 *
 * @author Will O'Brien
 */
public final class HibernateFindAllCommand extends HibernateCommand
        implements FindAllCommand {


    /**
     * Create new.
     * @param sf SessionFactory to use.
     */
    public HibernateFindAllCommand(final SessionFactory sf) {
        super(sf);
    }

    /**
     * Query repository for class type and return as list.
     *
     * @param method Method whose execution is being handled
     * @param args   [0] - Target class type
     * @return List of entities found.
     */
    @Override
    public Object execute(final Method method,
                          final Object[] args) {

        Class target = (Class) args[args.length - 1];

        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(target);

        return criteria.list();
    }
}
