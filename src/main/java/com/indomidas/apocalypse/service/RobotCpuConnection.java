package com.indomidas.apocalypse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RobotCpuConnection implements CommandLineRunner {
    @Autowired
    private RobotService robotService;

    @Override
    public void run(String...args) throws Exception {
        robotService.apiFetch();

    }
}




