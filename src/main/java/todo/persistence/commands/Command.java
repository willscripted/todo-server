/*
 * File: Command.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.persistence.commands;

import javax.persistence.PersistenceException;
import java.lang.reflect.Method;

/**
 * A stateless, object that encapsulates persistence logic.
 *
 * @author Will O'Brien
 */
public interface Command {

    /**
     * Perform command specific action.
     *
     * @param method Method whose execution is being handled
     * @param args   Object[] of arguments passed to the method
     * @return Object to be returned
     * @throws PersistenceException if unable to execute successfully
     */
    Object execute(Method method, Object[] args) throws PersistenceException;

}
