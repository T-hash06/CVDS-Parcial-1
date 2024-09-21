package com.tomas.cvds.parcial.agents;

import java.util.ArrayList;

public abstract class AgentManager {

    private static ArrayList<Agent> agents = new ArrayList<>();
    public static void StartAgents() {
        agents.add(new LogAgent());
        agents.add(new StockAgent());
    }
}
