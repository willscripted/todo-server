package todo.services;

/**
 * Service for querying the users in the repository.
 * @author Will O'Brien
 */
public interface UserService {
    
    boolean usernameExists(String username);
    
}