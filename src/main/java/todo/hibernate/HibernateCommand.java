package todo.hibernate;

import org.hibernate.SessionFactory;
import todo.persistence.commands.Command;

/**
 * Command whose execution logic relies on the hibernate framework.
 *
 * @author Will O'Brien
 */
public abstract class HibernateCommand implements Command {

    /**
     * Session factory to be used for executing persistence logic.
     */
    private SessionFactory sessionFactory;

    /**
     * Retrieve session factory.
     * @return SessionFactory
     */
    protected final SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Create new.
     * @param sf SessionFactory to use.
     */
    public HibernateCommand(final SessionFactory sf) {
        sessionFactory = sf;
    }

}
