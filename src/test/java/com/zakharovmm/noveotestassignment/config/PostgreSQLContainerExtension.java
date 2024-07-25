package com.zakharovmm.noveotestassignment.config;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;


public class PostgreSQLContainerExtension implements BeforeAllCallback {

    private static final String POSTGRES_IMAGE = "postgres:15.5";
    private static final String POSTGRES_DB = "noveo";

    private static final String POSTGRES_USER = "username";
    private static final String POSTGRES_PASSWORD = "password";
    private static PostgreSQLContainer<?> postgresqlContainer;

    @Override
    public void beforeAll(ExtensionContext context) {
        if (postgresqlContainer == null) {
            postgresqlContainer = new PostgreSQLContainer<>(DockerImageName.parse(POSTGRES_IMAGE))
                    .withDatabaseName(POSTGRES_DB)
                    .withUsername(POSTGRES_USER)
                    .withPassword(POSTGRES_PASSWORD)
                    .withExposedPorts(5432)
                    .withReuse(false);

            postgresqlContainer.start();

            System.setProperty("spring.datasource.url", postgresqlContainer.getJdbcUrl());
            System.setProperty("spring.datasource.username", postgresqlContainer.getUsername());
            System.setProperty("spring.datasource.password", postgresqlContainer.getPassword());
        }
    }
}

