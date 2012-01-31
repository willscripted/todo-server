/*
 * File: UserNotary.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.security.authority.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import todo.security.authority.Notary;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Issues user based authorities.
 *
 * @author Will O'Brien
 */
@Component
public class UserNotary implements Notary {

    @Override
    public Collection<GrantedAuthority> getAuthorities(Serializable userId) {

        GrantedAuthority auth = new SimpleGrantedAuthority("u" + userId);

        // Add auth to set
        Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
        auths.add(auth);

        return auths;
    }

}
