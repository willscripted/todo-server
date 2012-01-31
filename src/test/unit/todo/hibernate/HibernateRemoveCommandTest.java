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
 * Test HibernateRemoveCommand
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"HibernateTestContext.xml"})
public class HibernateRemoveCommandTest {

    private HibernateRemoveCommand command;

    @Autowired
    private SessionFactory sf;

    @Before
    public void setUp() throws Exception {
        command = new HibernateRemoveCommand(sf);
    }

    /**
     * Assert no errors thrown when deleting non-existent objects
     * @throws Exception
     */
    @Test
    @Transactional
    public void testExecute() throws Exception {

        Task task = new Task();
        task.setUser(createMock(User.class));
        task.setId(3L);
        
        Object[] args = new Object[] {task};

        command.execute(null, args);
        
    }

    


}
