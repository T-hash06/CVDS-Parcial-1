package com.tomas.cvds.parcial.database;

public interface Notifiable<T> {

    void notify(T data);
}
