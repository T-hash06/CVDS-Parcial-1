package com.tomas.cvds.parcial.database;

public interface Subscriptable<T> {

    void notifySubscribers(T data);

    void addSubscriber(Notifiable<T> subscriber);

    void removeSubscriber(Notifiable<T> subscriber);
}
