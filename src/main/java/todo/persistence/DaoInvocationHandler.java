/*
 * File: HibernateSupportedDaoInvocationHandler.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */
package todo.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import todo.persistence.commands.AbstractCommandFactory;
import todo.persistence.commands.AddCommand;
import todo.persistence.commands.Command;
import todo.persistence.commands.FindAllCommand;
import todo.persistence.commands.FindByPrimaryKeyCommand;
import todo.persistence.commands.RemoveCommand;
import todo.persistence.commands.UpdateCommand;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Invocation handler that uses predefined commands to execute persistence
 * logic.
 *
 * @author Will O'Brien
 */
public final class DaoInvocationHandler implements InvocationHandler {

    /**
     * Map of available commands.
     */
    private final Map<String, Command> commands = new HashMap<String,
            Command>();

    /**
     * Create and invocation handler with commands available from command
     * factory passed.
     *
     * @param factory Factory to use in generating commands that support the
     *                invocation handling of this object.
     */
    @Autowired
    public DaoInvocationHandler(final AbstractCommandFactory factory) {
        commands.put("add",
                     factory.getCommand(AddCommand.class));
        commands.put("remove",
                     factory.getCommand(RemoveCommand.class));
        commands.put("update",
                     factory.getCommand(UpdateCommand.class));
        commands.put("findByPrimaryKey",
                     factory.getCommand(
                             FindByPrimaryKeyCommand.class));
        commands.put("findAll",
                     factory.getCommand(FindAllCommand.class));
    }

    /**
     * Handle invocation of method.
     * @param proxy Object whose method is being handled.
     * @param method Method being invoked on the proxy object.
     * @param args Arguments passed with call to proxy object method.
     * @return Object, result of handled invocation.
     * @throws Exception if unable to fulfil invocation.
     */
    public Object invoke(final Object proxy,
                         final Method method,
                         final Object[] args)
            throws Exception {

        Command command = resolveCommand(method);

        if (command == null) {
            throw new UnsupportedOperationException();
        }

        return command.execute(method,
                               args);
    }

    /**
     * Decide which command to execute given the method to resolve.
     * @param method Method that is being handled.
     * @return Command if this handler can handle the method, else null.
     */
    private Command resolveCommand(final Method method) {

        Command command = null;

        String name = method.getName();

        if (commands.containsKey(name)) {
            command = commands.get(name);
        }

        return command;
    }
}
