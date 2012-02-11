package todo.domain;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import todo.webapp.dto.TaskDTO;

import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/WEB-INF/todo-servlet.xml",
                                   "/WEB-INF/applicationContext-security.xml"})
public class UserConverterTest {

    private Mapper dozerMapper;
    private Task task;

    @Autowired
    public void setDozerMapper(Mapper dm) {
        this.dozerMapper = dm;
    }

    private Long USER_ID = 4234L;
    
    @Before
    public void setUp() throws Exception {
        User user = new User();
        user.setPassword("SOMEPASS");
        user.setCreated(new Date());
        user.setId(USER_ID);
        
        task = new Task();
        task.setId(1234L);
        task.setUser(user);
        task.setTitle("hello");
    }

    @Test
    public void testConvert() throws Exception {
        TaskDTO taskDTO = dozerMapper.map(task, TaskDTO.class);
        assertTrue(taskDTO.getUserId().equals(USER_ID));
        assertTrue(taskDTO.getTitle().equals("hello"));
    }
}
