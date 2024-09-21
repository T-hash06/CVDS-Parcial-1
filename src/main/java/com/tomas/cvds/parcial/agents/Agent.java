package com.tomas.cvds.parcial.agents;

import com.tomas.cvds.parcial.database.Notifiable;

/**
 * Abstract class representing an Agent that implements the Notifiable interface.
 *
 * @param <T> the type of the notification
 */
public abstract class Agent<T> implements Notifiable<T> {

    /**
     * Logs a message to the console.
     *
     * @param log the message to log
     */
    protected void log(String log) {
        System.out.println(log);
    }
}
