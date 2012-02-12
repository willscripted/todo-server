package todo.hibernate;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import todo.domain.Task;
import todo.domain.User;

import java.util.Date;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * @author Will O'Brien
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"HibernateTestContext.xml"})
public class HibernateAddCommandTest {

    private HibernateAddCommand command;

    @Autowired
    private SessionFactory sf;

    @Before
    public void setUp() throws Exception {
        command = new HibernateAddCommand(sf);
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Best case scenario. Everything exists, including transaction.
     *
     * @throws Exception
     */
    @Test
    @Transactional
    public void testExecute() throws Exception {

        // Create entity / args
        User user = new User();
        user.setCreated(new Date());
        user.setEmail("someEmail");
        user.setUsername("username");
        user.setPassword("password");
        Object[] args = new Object[]{user};

        command.execute(null,
                        args);

    }

    @Test
    @Transactional
    public void testExecute_taskIdAlreadyInUse() throws Exception {
        Long id = 500L;
        {
            User user = new User();
            user.setCreated(new Date());
            user.setEmail("someEmail");
            user.setUsername("username");
            user.setPassword("password");

            Object[] args = new Object[]{user};
            user.setId(id);
            command.execute(null,
                            args);
        }

        {

            User user = new User();
            user.setCreated(new Date());
            user.setEmail("someEmail");
            user.setUsername("username");
            user.setPassword("password");


            Object[] args = new Object[]{user};
            user.setId(id);
            command.execute(null,
                            args);

            assertFalse(user.getId()
                            .equals(id));
        }

    }

}
