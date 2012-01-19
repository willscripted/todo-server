/*
 * File: ClassArgInvocationHandlerDecorator.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */
package todo.persistence;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Decorator for an invocation h that appends a class type,
 * for which the implemented dao was designed to handle,
 * to the end of the args array.
 *
 * @author Will O'Brien
 */
public class ClassArgInvocationHandlerDecorator implements InvocationHandler {

    /**
     * Handler to receive decorated invocation.
     */
    private final InvocationHandler handler;

    /**
     * Class that will be appended to the args array.
     */
    private final Class clazz;

    /**
     * @param c the Class to append to the end of the ars array.
     * @param h Handler to receive decorated invocation calls.
     */
    public ClassArgInvocationHandlerDecorator(final Class c,
                                              final InvocationHandler h) {
        this.clazz = c;
        this.handler = h;
    }


    @Override
    public final Object invoke(final Object proxy,
                               final Method method,
                               final Object[] args)
            throws Throwable {

        // Create new args array and populate it with old args
        Object[] newArgs = null;

        if (args == null) {
            newArgs = new Object[1];
        } else {
            newArgs = new Object[args.length + 1];
            System.arraycopy(args,
                             0,
                             newArgs,
                             0,
                             args.length);
        }

        // Add class as last arg
        newArgs[newArgs.length - 1] = clazz;

        // Pass the request on to the real h
        return handler.invoke(proxy,
                              method,
                              newArgs);
    }
}
