package webapp.controllers;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * User: Will O'Brien
 * Date: 12/5/11
 * Time: 5:13 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:WEB-INF/applicationContext-security.xml",
                       "classpath:WEB-INF/todo-servlet.xml"})
public class SessionFactoryIntegrationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testSessionFactoryExists() {
        SessionFactory sf = applicationContext.getBean(SessionFactory.class);
        assertNotNull(sf);
        assertNotNull(sf.getCurrentSession());
    }
}
