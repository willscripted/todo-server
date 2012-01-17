package todo.persistence.hibernate.commands;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: will
 * Date: 11/1/11
 * Time: 10:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class CountCommand implements Command {

    @Transactional(propagation = Propagation.MANDATORY)
    public Object execute(Method method, Object[] args, Session session)
            throws Exception {
        String name = method.getName();
        Query namedQuery = session.getNamedQuery(name);

        return namedQuery.uniqueResult();
    }
}
