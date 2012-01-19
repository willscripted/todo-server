package todo.persistence.commands;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Method;

/**
 * Encapsulates logic to find an entity in a repository by its
 * primary identifier.
 *
 * @author Will O'Brien
 */
public interface FindByPrimaryKeyCommand extends Command {

    /**
     * Execute find entity logic.
     *
     * @param method Method whose execution is being handled
     * @param args   [0] - the primary key
     *               [1] - the entity's class type
     * @return the entity object
     * @throws EntityNotFoundException if an entity does not exist for the
     * given type and key.
     */
    @Override
    Object execute(Method method, Object[] args)
            throws EntityNotFoundException;
}
