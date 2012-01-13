/*
 * File: Command.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.persistence.hibernate.commands;

import org.hibernate.Session;

import java.lang.reflect.Method;

/**
 * An object that can be executed by a method handler.
 *
 * @author Will O'Brien
 */
public interface Command {

    /**
     * Perform command specific action.
     *
     * @param method  Method whos execution is being handled
     * @param args    Object[] of arguments passed to the method
     * @param session Hibernate session with which to perform this command
     * @return Object to be returned
     * @throws Exception Exception in persistence command execution
     */
    Object execute(Method method, Object[] args, Session session)
            throws Exception;

}
