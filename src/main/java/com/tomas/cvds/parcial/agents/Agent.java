package com.tomas.cvds.parcial.agents;

import com.tomas.cvds.parcial.database.Notifiable;

public abstract class Agent<T> implements Notifiable<T> {

    protected void log(String log) {
        System.out.println(log);
    }
}
