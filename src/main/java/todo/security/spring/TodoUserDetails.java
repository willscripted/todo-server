package todo.security.spring;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import todo.domain.User;

import java.util.Collection;

/**
 * Creates a UserDetails object from a ImmutableUser object. UserDetails obj can be
 * plugged into the spring security system.
 *
 * @author whobrien
 */
public final class TodoUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final boolean isAccountLocked;
    private final boolean isEnabled;
    private final Collection<GrantedAuthority> authorities;

    /**
     * Create a UserDetails object from an object with ImmutableUser interface.
     *
     * @param user - The user whose access tolken is being created
     */
    public TodoUserDetails(User user, Collection<GrantedAuthority> authorities) {

        { // Get user info
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.isEnabled = user.isEnabled();
        }

        { // Set authorities
            this.authorities = authorities;
        }
        { // No accounts are lockable
            this.isAccountLocked = false;
        }
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * @return the ImmutableUser's email, which will serve as their 'Username' for Spring
     * Security purposes.
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isAccountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    @Override
    public String toString() {
        return "TodoUserDetails: " + "Username/Email: " + this.getUsername() +
                "Password: " + this.getPassword() +
                "Locked: " + this.isAccountLocked +
                "Enabled: " + this.isEnabled +
                "Auths: " + this.getAuthorities();
    }
}
