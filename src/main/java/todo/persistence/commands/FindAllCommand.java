package todo.persistence.commands;

import javax.persistence.PersistenceException;
import java.lang.reflect.Method;

/**
 * Command that encapsulates logic for executing logic to find all persistent
 * objects of a particular type.
 *
 * @author Will O'Brien
 */
public interface FindAllCommand extends Command {

    /**
     * Execute find all logic.
     *
     * @param method Method whose execution is being handled
     * @param args   [0] - Target class type
     * @return List of found entities
     */
    @Override
    Object execute(Method method, Object[] args);
}
