package todo.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import todo.domain.User;

/**
 * Service for querying the users in the repository.
 * @author Will O'Brien
 */
public interface UserService {
    
    boolean usernameExists(String username);

    User getUserByEmail(String username);

    User getUserByUsername(String username) throws UsernameNotFoundException;

    Long createUser(User user);
}