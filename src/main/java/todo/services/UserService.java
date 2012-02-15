package todo.services;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import todo.domain.User;
import todo.webapp.dto.RegistrationForm;

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


    /**
     * Create a new user from form if username is available.
     *
     * The new user will have the default permissions.
     *
     * @param form RegistrationForm
     * @return String username
     * @see todo.security.authority.impl.SiteNotary
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    String createUser(RegistrationForm form);
}