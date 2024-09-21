package com.tomas.cvds.parcial.database;

/**
 * A generic interface for notifying with data of type T.
 *
 * @param <T> the type of data to be notified
 */
public interface Notifiable<T> {

    /**
     * Notify with the given data.
     *
     * @param data the data to notify with
     */
    void notify(T data);
}
