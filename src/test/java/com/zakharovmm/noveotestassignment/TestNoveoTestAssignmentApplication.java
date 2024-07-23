package com.zakharovmm.noveotestassignment;

import org.springframework.boot.SpringApplication;

public class TestNoveoTestAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.from(Application::main).with(TestcontainersConfiguration.class).run(args);
    }

}
