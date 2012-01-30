package todo.security.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import todo.domain.User;
import todo.security.authority.AuthorityRegistry;
import todo.services.UserService;

import java.util.Collection;

/**
 * Used to retrieve UserDetails objects for users.
 * 
 * @author Will O'Brien
 */
@Service(value="todoUserDetailsService")
public class TodoUserDetailsService implements UserDetailsService {

    /**
     * Object responsible for providing the appropriate GrantedAuthorities
     */
    @Autowired
    private AuthorityRegistry authorityRegistry;

    /**
     * Service used to retrieve a User object.
     */
    @Autowired
    private UserService userService;

    /**
     * Get a UserDetails object from the application's records.
     *
     * @param username - Username of user to create a UserDetails object for.
     * @return UserDetails of user.
     * @throws UsernameNotFoundException - User with passed username not
     * found.
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        System.out.println("User: " + user.toString());
        Collection<GrantedAuthority> authorities = authorityRegistry
                .getAuthorities(user.getId());

        return new TodoUserDetails(user, authorities);
    }
}
