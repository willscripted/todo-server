/*
 * File: Notary.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.security.authority;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * Issuer of authorities.
 * @author Will O'Brien
 */
public interface Notary {

    public Collection<GrantedAuthority> getAuthorities(Serializable userId);

}
