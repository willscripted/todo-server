/*
 * File: ClassArgInvocationHandlerDecorator.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */
package todo.persistence.hibernate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Will O'Brien
 */
public class ClassArgInvocationHandlerDecorator implements InvocationHandler {

    private final InvocationHandler handler;
    private final Class clazz;

    public ClassArgInvocationHandlerDecorator(Class clazz,
                                              InvocationHandler handler) {
        this.clazz = clazz;
        this.handler = handler;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
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

        // Pass the request on to the real handler
        return handler.invoke(proxy,
                              method,
                              newArgs);
    }
}
