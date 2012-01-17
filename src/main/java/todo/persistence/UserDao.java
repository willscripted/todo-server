package todo.persistence;

import todo.domain.User;

import java.util.Collection;

/**
 * User: Will O'Brien
 * Date: 1/15/12
 * Time: 6:11 PM
 */
public interface UserDao extends GenericDao<User> {

    /**
     * Find all the usernames
     * @return Collection<String> usernames
     */
    Collection<String> findUsernames();

}
