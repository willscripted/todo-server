/*
 * File: TransactionalCommand.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */
package todo.persistence.hibernate.commands;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.lang.reflect.Method;

/**
 * @author Will O'Brien
 */
abstract class TransactionalCommand implements Command {

    @Override
    public Object execute(Method method, Object[] args, Session session)
            throws Exception {

        if (args == null || args[0] == null) {
            throw new PersistenceException("Null target record cannot be "
                                           + "added, updated, or removed");
        }
        Transaction txn = session.beginTransaction();
        try {
            Object result = command(args,
                                    session);
            txn.commit();
            return result;
        }
        finally {
            if (!txn.wasCommitted()) {
                txn.rollback();
            }
        }
    }

    protected abstract Object command(Object[] args, Session session)
            throws Exception;

}
