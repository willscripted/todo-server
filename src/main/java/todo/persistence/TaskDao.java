/*
 * File: TaskDao.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */

package todo.persistence;

import todo.domain.Task;

import java.util.Collection;

/**
 * Data access object for Task s.
 *
 * @author Will O'Brien
 */
public interface TaskDao extends GenericDao<Task> {

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
