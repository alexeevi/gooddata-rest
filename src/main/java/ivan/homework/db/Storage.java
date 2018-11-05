package ivan.homework.db;

import java.util.Collection;

public interface Storage<T> {

    /**
     * Add given object into the storage
     * @param object
     * @return added object, or its already existent copy
     */
    T add(T object);

    /**
     * Retrieve all objects from the storage
     * @return
     */
    Collection<T> getAll();

    /**
     * Find an object by given key
     * @param key
     * @return found object or null is not found
     */
    T getByKey(Object key);
}
