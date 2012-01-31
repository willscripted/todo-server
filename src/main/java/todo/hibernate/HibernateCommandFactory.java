package todo.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import todo.persistence.commands.AbstractCommandFactory;
import todo.persistence.commands.AddCommand;
import todo.persistence.commands.Command;
import todo.persistence.commands.FindAllCommand;
import todo.persistence.commands.FindByPrimaryKeyCommand;
import todo.persistence.commands.RemoveCommand;
import todo.persistence.commands.UpdateCommand;

import java.util.HashMap;

/**
 * Factory that creates persistence commands that use hibernate logic.
 *
 * @author Will O'Brien
 */
@Component
public class HibernateCommandFactory implements AbstractCommandFactory {

    /**
     * Commands that this factory can produce.
     */
    private final HashMap<Class, HibernateCommand> commands;

    /**
     * Create new.
     * @param sf SessionFactory that provides sessions.
     */
    @Autowired
    public HibernateCommandFactory(final SessionFactory sf) {
        this.commands = new HashMap<Class, HibernateCommand>();
        
        commands.put(AddCommand.class, new HibernateAddCommand(sf));
        commands.put(UpdateCommand.class, new HibernateUpdateCommand(sf));
        commands.put(RemoveCommand.class, new HibernateRemoveCommand(sf));
        commands.put(FindAllCommand.class, new HibernateFindAllCommand(sf));
        commands.put(FindByPrimaryKeyCommand.class,
                     new HibernateFindByPrimaryKeyCommand(sf));
    }

    /**
     * Return command of type passed.
     * @param command Class of type to retrieve.
     * @param <T> Command object
     * @return T command
     */
    @Override
    public final <T extends Command> T getCommand(final Class<T> command) {

        if (!commands.containsKey(command)) {
            throw new UnsupportedOperationException("Command not supported");
        }

        return (T) commands.get(command);
    }
}
