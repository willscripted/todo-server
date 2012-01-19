package todo.persistence.commands;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.lang.reflect.Method;

/**
 * Command that encapsulates logic to remove an object from a repository.
 *
 * @author Will O'Brien
 */
public interface RemoveCommand extends Command {


    /**
     * Executes logic for removing an object from the repository.
     *
     * Note: Removing a non-existent object will not throw an exception - ie
     * multiple identical calls can be made
     *
     * @param method Method whose execution is being handled
     * @param args   [0] - object to remove.
     * @return null
     * @throws PersistenceException
     *
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    Object execute(Method method, Object[] args);

}
