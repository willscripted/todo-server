/*
 * File: SiteNotary.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.security.authority.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import todo.persistence.UserDao;
import todo.security.authority.Notary;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Issues site-based authorities.
 * 
 * @author Will O'Brien
 */
@Service
public class SiteNotary extends RoleHierarchyImpl implements Notary {

    public static final String DEFAULT_SITE_AUTHORITY = "ROLE_USER";
    
    @Autowired
    private UserDao userDao;

    public SiteNotary() {
         super();
         this.setHierarchy(
                     "ROLE_ADMIN > ROLE_MODERATOR\n"
                   + "ROLE_MODERATOR > ROLE_USER\n"
                   );
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Collection<GrantedAuthority> getAuthorities(Serializable userId) {

        // Get String ranks from manager
        Collection<String> ranksOfUser = userDao.findByPrimaryKey(userId)
                                                .getAuthorities();

        // Convert to GrantedAuthority s
        Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
        for(String rank : ranksOfUser) {
            auths.add(new SimpleGrantedAuthority(rank));
        }

        // Get reachable auths from auths
        Collection<GrantedAuthority> reachableAuths = this.getReachableGrantedAuthorities(auths);

        return reachableAuths;

    }
}
