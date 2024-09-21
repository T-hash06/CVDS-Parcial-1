package com.tomas.cvds.parcial.database;

/**
 * Interface representing a subscriptable entity.
 *
 * @param <T> the type of data to be notified to subscribers
 */
public interface Subscriptable<T> {

    /**
     * Notifies all subscribers with the provided data.
     *
     * @param data the data to notify subscribers with
     */
    void notifySubscribers(T data);

    /**
     * Adds a subscriber to the list of subscribers.
     *
     * @param subscriber the subscriber to add
     */
    void addSubscriber(Notifiable<T> subscriber);

    /**
     * Removes a subscriber from the list of subscribers.
     *
     * @param subscriber the subscriber to remove
     */
    void removeSubscriber(Notifiable<T> subscriber);
}
