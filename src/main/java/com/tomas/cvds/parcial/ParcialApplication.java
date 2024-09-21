package com.tomas.cvds.parcial;

import com.tomas.cvds.parcial.agents.AgentManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParcialApplication {

    public static void main(String[] args) {
        AgentManager.StartAgents();
        SpringApplication.run(ParcialApplication.class, args);
    }

}
