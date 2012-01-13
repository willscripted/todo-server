/*
 * File: TaskDao.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.persistence;

import todo.hibernate.entities.Task;

import java.util.Collection;

/**
 * Data access object for Task s.
 *
 * @author Will O'Brien
 */
public interface TaskDao {

    /**
     * Add a new Task to persistence.
     *
     * @param task Task to add. Does not yet have a unique identifier.
     */
    void add(Task task);

    /**
     * Update an existing task with the state of the task passed.
     *
     * @param task Task to update. Must have a unique identifier.
     */
    void update(Task task);

    /**
     * Remove the task passed from persistence.
     *
     * @param task Task to remove. Must exist in persistence.
     */
    void remove(Task task);

    /**
     * Find all tasks.
     *
     * @return Collection of all Task s.
     */
    Collection findAll();

    /**
     * Find a specific task by its primary identifier.
     *
     * @param key Long unique key of target task
     * @return Task
     */
    Task findByPrimaryKey(Long key);

    /**
     * Retrieve a collection of all current tasks.
     *
     * @return Collection<Task>
     */
    Collection<Task> findCurrentTasks();

    /**
     * Retrieve a collection of all pending tasks.
     *
     * @return Collection<Task>
     */
    Collection<Task> findPendingTasks();

    /**
     * Count the number of tasks currently awaiting completion.
     *
     * @return Long
     */
    Long countCurrent();

    /**
     * Count the number of tasks waiting to be added to the list of current
     * tasks.
     *
     * @return Long
     */
    Long countPending();

    /**
     * Count the number of Tasks that have not been completed or removed.
     *
     * @return Long
     */
    Long countOutstanding();

}
