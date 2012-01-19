package todo.persistence.commands;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Add an object to the repository.
 *
 * @author Will O'Brien
 */
public interface AddCommand extends Command {

    /**
     * Add the object at args[0] to the repository.
     *
     * @param method Method whose execution is being handled
     * @param args   [0] - Entity to be added
     * @return Serializable id of the new object
     * @throws EntityExistsException if object to persist has an id set that
     * already exists in the repository
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    Serializable execute(Method method, Object[] args)
            throws EntityExistsException;
}
