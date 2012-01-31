/*
 * File: AuthorityRegistry.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.security.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

/**
 * Source of authorities.
 * 
 * @author Will O'Brien
 */
@Service
public class AuthorityRegistry {

    private Collection<Notary> notaries;

    @Autowired
    public void setNotaries(Collection<Notary> notaries) {
        this.notaries = notaries;
    }

    public Collection<GrantedAuthority> getAuthorities(Serializable userId) {
        Collection<GrantedAuthority> auths = new HashSet<GrantedAuthority>();

        // Get authorities from each notary in the registry
        for(Notary notary: notaries)
        {
            Collection<GrantedAuthority> newAuths = notary.getAuthorities(userId);
            auths.addAll(newAuths);
        }

        return auths;
    }
}
