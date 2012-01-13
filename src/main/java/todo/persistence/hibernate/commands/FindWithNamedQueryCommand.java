/*
 * File: FindWithNamedQueryCommand.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.persistence.hibernate.commands;

import org.hibernate.Query;
import org.hibernate.Session;

import java.lang.reflect.Method;

/**
 * @author Will O'Brien
 */
public class FindWithNamedQueryCommand implements Command {

    public FindWithNamedQueryCommand() {
    }

    public Object execute(Method method, Object[] args, Session session)
            throws Exception {
        session.beginTransaction();
        String name = method.getName();
        Query namedQuery = session.getNamedQuery(name);
        return namedQuery.list();
    }

}
