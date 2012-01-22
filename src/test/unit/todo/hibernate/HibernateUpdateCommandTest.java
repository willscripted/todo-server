package todo.hibernate;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import todo.domain.Task;
import todo.domain.User;

import static org.easymock.EasyMock.createMock;

/**
 * Test HibernateUpdateCommand
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"HibernateTestContext.xml"})
public class HibernateUpdateCommandTest {

    private HibernateUpdateCommand command;

    @Autowired
    public void setUp(SessionFactory sf) {
        command = new HibernateUpdateCommand(sf);
    }
    
    @Test
    @Transactional
    public void testExecute() {

        Task task = new Task();
        task.setId(43L);
        task.setUser(createMock(User.class));
        Object[] args = new Object[] {task};

        command.execute(null, args);
    }
}
