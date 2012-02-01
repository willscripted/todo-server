package todo.services;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import todo.domain.User;
import todo.persistence.UserDao;
import todo.security.authority.impl.SiteNotary;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of UserService.
 *
 * @author Will O'Brien
 */
@Service
public final class UserServiceImpl implements UserService {

    /**
     * UserDao for user CRUD.
     */
    @Autowired
    private UserDao userDao;

    /**
     * Encoder used to encode passwords of new users.
     */
    @Autowired
    private PasswordEncoder encoder;

    /**
     * Hibernate SessionFactory for building queries.
     */
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
                   readOnly = true)
    public boolean usernameExists(String username) {
        Criteria criteria = sessionFactory.getCurrentSession()
                                          .createCriteria(User.class);
        criteria.add(Restrictions.ilike("username",
                                        username));
        Object user = criteria.uniqueResult();

        // If user is not null, return true
        return (user != null);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,
                   readOnly = true)
    public User getUserByEmail(String email) {

        Criteria criteria = sessionFactory.getCurrentSession()
                                          .createCriteria(User.class);

        criteria.add(Restrictions.ilike("email",
                                        email));

        return (User) criteria.uniqueResult();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
                   readOnly = true)
    public User getUserByUsername(String username) {
        Criteria criteria = sessionFactory.getCurrentSession()
                                          .createCriteria(User.class);

        criteria.add(Restrictions.ilike("username",
                                        username));

        return (User) criteria.uniqueResult();

    }

    @Override
    public final Serializable createUser(final User user) {
        addDefaultAuthorities(user);

        user.setCreated(new Date());
        encodeNewUsersPassword(user);

        Serializable id = createUserTransaction(user);

        if (id == null) {
            throw new IllegalArgumentException("Username already exists");
        }

        return id;
    }

    /**
     * Encode the user's raw password using the application's encoding method.
     * Set the user's password to the new, encoded, password.
     *
     * @param user User with raw password set.
     */
    private void encodeNewUsersPassword(User user) {
        if (user.getPassword() == null) { throw new IllegalArgumentException();}

        String encodedPassword = encoder.encodePassword(user.getPassword(),
                                                        user.getCreated());

        user.setPassword(encodedPassword);
    }

    /**
     * Add the application's default authorities.
     *
     * @param user User who should be assigned the default authorities.
     */
    private void addDefaultAuthorities(User user) {
        Set<String> auths = new HashSet<String>();
        auths.add(SiteNotary.DEFAULT_SITE_AUTHORITY);
        user.setAuthorities(auths);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
                   readOnly = true)
    public Collection<String> getUsernames() {

        Query query = sessionFactory.getCurrentSession()
                                    .createQuery("select "
                                                 + "u.username from User u");
        return new HashSet<String>(query.list());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
                   readOnly = false)
    public void updateUser(User user) {
        // Todo, this smells
        User currentRecord = getUserByUsername(user.getUsername());

        user.setCreated(currentRecord.getCreated());
        user.setPassword(currentRecord.getPassword());

        userDao.update(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
                   readOnly = false)
    public void removeUserByUsername(String username) {
        Query query = sessionFactory.getCurrentSession()
                                    .getNamedQuery("removeUserWithUsername");

        query.setParameter("username",
                           username);

        query.executeUpdate();
    }

    @Override
    public void changePassword(String username, String newPassword) {
        User user = getUserByUsername(username);

        user.setPassword(newPassword);

        encodeNewUsersPassword(user);
    }

    /**
     * Transaction for creating a user.
     *
     * @param user User to add, as is.
     * @return Serializable id of the new user.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    private Serializable createUserTransaction(User user) {
        // If username does not exist, add the new user
        if (!usernameExists(user.getUsername())) {
            return userDao.add(user);
        }

        return null;
    }
}
