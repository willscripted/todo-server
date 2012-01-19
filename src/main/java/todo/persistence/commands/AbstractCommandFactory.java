package todo.persistence.commands;

/**
 * Create commands for executing persistence logic.
 *
 * @author Will O'Brien
 */
public interface AbstractCommandFactory {

    /**
     * Get command of type passed. Available types extend the Command
     * interface in the same package.
     *
     * @param command Class of command to retrieve.
     * @param <T>     Expected return type of command.
     * @return Command of type <T>
     *
     *         If command type cannot be created by the factory,
     *         UnsupportedOperationException is thrown.
     */
    <T extends Command> T getCommand(Class<T> command);
}
