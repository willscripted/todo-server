package todo.persistence.commands;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Method;

/**
 * Encapsulates logic for updating an object in a repository.
 *
 * @author Will O'Brien
 */
public interface UpdateCommand extends Command {

    /**
     * Method that handles execution of an update to a repository.
     *
     * @param method Method whose execution is being handled
     * @param args   [0] - entity to be updated
     * @return
     * @throws EntityNotFoundException if an entry for the the given id does not
     * exist
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    Object execute(Method method, Object[] args) throws EntityNotFoundException;
}
