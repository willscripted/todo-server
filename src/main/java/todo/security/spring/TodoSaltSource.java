/*
 * File: DibitSaltSource.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.security.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;
import todo.domain.User;
import todo.services.UserService;

/**
 *
 * @author Will O'Brien
 */
public class TodoSaltSource implements SaltSource{

    @Autowired
    private UserService userService;

    @Override
    public Object getSalt(UserDetails user) {
        User someUser = userService.getUserByUsername(user.getUsername());
        return someUser.getCreated();
    }

}
