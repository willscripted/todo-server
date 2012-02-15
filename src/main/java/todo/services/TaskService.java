package todo.services;

import todo.domain.Task;

import java.util.Collection;

/**
 * Service for querying Tasks in repository.
 * @author Will O'Brien
 */
public interface TaskService {

    /**
     * Add a task.
     * @param task Task to add.
     * @return Long id of new task.
     */
    Long addTask(Task task);

    /**
     * Find all tasks belonging to user specified
     * @param username String username of user whose tasks should be returned.
     * @return Collection of tasks.
     */
    Collection<Task> getTasksOfUser(String username);

    /**
     * Find all current tasks of user specified by username.
     * @param username String username of user.
     * @return Collection<Task> of tasks.
     */
    Collection<Task> getCurrentTasksOfUser(String username);

    /**
     * Update an existing task.
     * @param task
     */
    void updateTask(Task task);

    /**
     * Delete a task.
     * @param id Long id of task.
     */
    void removeTask(Long id);
}
