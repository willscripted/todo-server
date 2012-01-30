package todo.services;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import todo.domain.User;
import todo.persistence.UserDao;
import todo.security.authority.impl.SiteNotary;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Will O'Brien
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean usernameExists(String username) {
        Criteria criteria = sessionFactory.getCurrentSession()
                                          .createCriteria(User.class);
        criteria.add(Restrictions.ilike("username", username));
        Object user = criteria.uniqueResult();

        // If user is not null, return true
        return (user != null);
    }

    @Override
    public User getUserByEmail(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User getUserByUsername(String username)
            throws UsernameNotFoundException {
        Criteria criteria = sessionFactory.getCurrentSession()
                                          .createCriteria(User.class);
        criteria.add(Restrictions.ilike("username", username));
        User user = (User) criteria.uniqueResult();
        System.out.println(user);
        return user;
    }

    @Override
    public final Long createUser(final User user) {
        { // Add initial authorities
            Set<String> auths = new HashSet<String>();
            auths.add(SiteNotary.DEFAULT_SITE_AUTHORITY);
            user.setAuthorities(auths);
        }
        { // Set created & hash password
            user.setCreated(new Date());
            String encodedPassword = encoder.encodePassword(user.getPassword(),
                                                            user.getCreated());

            user.setPassword(encodedPassword);
        }
        { // Enable user account
            // Todo - remove when email conformation is implemented
            user.setEnabled(true);
        }
        
        { // Create user
            Serializable id = createUserTransaction(user);
            if (id == null) {
                throw new IllegalArgumentException("Username already exists");
            }
            return (Long) id;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    private Serializable createUserTransaction(User user) {
        // If username does not exist, add the new user
        if (!usernameExists(user.getUsername())) {
            return userDao.add(user);
        }

        return null;
    }
}
