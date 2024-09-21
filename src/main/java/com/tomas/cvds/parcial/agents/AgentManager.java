package com.tomas.cvds.parcial.agents;

import java.util.ArrayList;

/**
 * Abstract class to manage agents.
 */
public abstract class AgentManager {

    /**
     * List to store agents.
     */
    private static final ArrayList<Agent> agents = new ArrayList<>();

    /**
     * Initializes and starts the agents.
     */
    public static void StartAgents() {
        agents.add(new LogAgent());
        agents.add(new StockAgent());
    }
}
