package todo.persistence.hibernate.commands;

import org.hibernate.Query;
import org.hibernate.Session;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: will
 * Date: 11/1/11
 * Time: 10:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class CountCommand implements Command {

    public Object execute(Method method, Object[] args, Session session)
            throws Exception {
        session.beginTransaction();
        String name = method.getName();
        Query namedQuery = session.getNamedQuery(name);

        return namedQuery.uniqueResult();
    }
}
