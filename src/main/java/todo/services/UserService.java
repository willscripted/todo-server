package todo.services;

import todo.domain.User;

import java.io.Serializable;
import java.util.Collection;

/**
 * Service for querying the users in the repository.
 * @author Will O'Brien
 */
public interface UserService {

    /**
     * Return true if a user with target username exists.
     * @param username String target username.
     * @return boolean.
     */
    boolean usernameExists(String username);

    /**
     * Get a user by their email address.
     *
     * @param username String target username.
     * @return User
     */
    User getUserByEmail(String username);

    /**
     * Retrieve a user by their username.
     *
     * @param username Existing username of a user.
     * @return User who has target username or null if no user exists.
     */
    User getUserByUsername(String username);

    /**
     * Create a new user. User's password should be raw. By default,
     * new users are not enabled but possess the default application
     * authorities.
     *
     * @param user User object describing the new user to create.
     * @return Serializable key for user.
     * @see todo.security.authority.impl.SiteNotary
     */
    Serializable createUser(User user);

    /**
     * Get a list of all usernames in use.
     * @return Collection<String> usernames.
     */
    Collection<String> getUsernames();

    /**
     * Update the user domain object. Does not update created date.
     * @param user User containing updated information.
     */
    void updateUser(User user);

    /**
     * Remove a user by their username, if user with target username does not
     * exist, do nothing.
     * @param username String target username.
     */
    void removeUserByUsername(String username);

    /**
     * Set a user's new password.
     *
     * @param username
     * @param newPassword
     */
    void changePassword(String username,
                        String newPassword);
}