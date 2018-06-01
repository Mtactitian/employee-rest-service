package com.rest.employee;

import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication
public class EmployeeApplication extends SpringBootServletInitializer implements CommandLineRunner {

    private final DataSource dataSource;

    public EmployeeApplication(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EmployeeApplication.class);
    }

    @Override
    public void run(String... args) {
        logger = LogFactory.getLog(EmployeeApplication.class);

        logger.info("USING " + dataSource.getUrl() + " datasource url");

        logger.info("TRIGGERING JENKINS");

    }
}
