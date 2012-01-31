package todo.persistence;

import todo.domain.User;

import java.util.Collection;

/**
 * @author Will O'Brien
 */
public interface UserDao extends GenericDao<User> {

    /**
     * Find all the usernames
     * @return Collection<String> usernames
     */
    Collection<String> findUsernames();

}
