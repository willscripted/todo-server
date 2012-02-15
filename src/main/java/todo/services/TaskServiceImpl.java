package todo.services;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import todo.domain.Task;
import todo.persistence.TaskDao;

import java.util.Collection;

/**
 * Implementation of task service
 *
 * @author Will O'Brien
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long addTask(Task task) {
        return (Long) taskDao.add(task);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
                   readOnly = true)
    public Collection<Task> getTasksOfUser(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query findIncompleteTasks =
                session.getNamedQuery("findTasksOfUser");
        findIncompleteTasks.setParameter("username", username);
        return findIncompleteTasks.list();
    }

    @Override
    public Collection<Task> getCurrentTasksOfUser(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query findIncompleteTasks =
                session.getNamedQuery("findIncompleteTasksOfUser");
        findIncompleteTasks.setParameter("username", username);
        return findIncompleteTasks.list();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateTask(Task task) {
        taskDao.update(task);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeTask(Long id) {
        Task task = taskDao.findByPrimaryKey(id);
        if (task != null) {
            taskDao.remove(task);
        }

    }
}
