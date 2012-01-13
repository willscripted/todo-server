/*
 * File: HibernateSupportedDaoInvocationHandler.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */
package todo.persistence.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import todo.persistence.hibernate.commands.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Will O'Brien
 */
public class HibernateSupportedDaoInvocationHandler implements
                                                    InvocationHandler {

    private static final Map<String, Command> COMMANDS = new HashMap<String,
            Command>();
    private static final Command FIND_WITH_NAMED_QUERY_COMMAND =
            new FindWithNamedQueryCommand();
    private static final Command COUNT_NAMED_QUERY_COMMAND = new CountCommand
            ();

    private SessionFactory sessionFactory;

    public HibernateSupportedDaoInvocationHandler(SessionFactory
                                                          sessionFactory) {
        this.sessionFactory = sessionFactory;
        COMMANDS.put("add",
                     new AddCommand());
        COMMANDS.put("remove",
                     new RemoveCommand());
        COMMANDS.put("update",
                     new UpdateCommand());
        COMMANDS.put("findByPrimaryKey",
                     new FindByPrimaryKeyCommand());
        COMMANDS.put("findAll",
                     new FindAllCommand());
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Exception {
        Command command = resolveCommand(method);

        if (command == null) {
            throw new UnsupportedOperationException();
        }

        Session session = sessionFactory.openSession();
        System.out
                .println(session);
        Object ret = command.execute(method,
                                     args,
                                     session);
        return ret;
    }

    private Command resolveCommand(Method method) {

        Command command = null;

        String name = method.getName();

        if (COMMANDS.containsKey(name)) {
            command = COMMANDS.get(name);
        } else if (name.startsWith("find")) {
            command = FIND_WITH_NAMED_QUERY_COMMAND;
        } else if (name.startsWith("count")) {
            command = COUNT_NAMED_QUERY_COMMAND;
        }

        return command;
    }
}
