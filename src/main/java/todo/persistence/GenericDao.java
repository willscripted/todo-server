package todo.persistence;

import java.io.Serializable;
import java.util.Collection;

/**
 * Generic data access object.
 * @param <T> the type of entity this GenericDao manages
 */
public interface GenericDao<T> {
    /**
     * Add a new object to persistence.
     *
     * @param t T to add. Does not yet have a unique identifier.
     */
    Serializable add(T t);

    /**
     * Update an existing persistent object with the state of the object
     * passed.
     *
     * @param t T to update. Must have a unique identifier.
     */
    void update(T t);

    /**
     * Remove the object passed from persistence.
     *
     * @param t T to remove. Must exist in persistence.
     */
    void remove(T t);

    /**
     * Find all objects of class T.
     *
     * @return Collection<T> of all persistent objects.
     */
    Collection<T> findAll();

    /**
     * Find a specific object by its primary identifier.
     *
     * @param key Long unique key of target persistent object
     * @return T
     */
    T findByPrimaryKey(Object key);

}
