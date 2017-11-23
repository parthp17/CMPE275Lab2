package sjsu.cmpe275.Lab2.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
 * Project: CMPE275Lab2
 * @author: Kemy Halani, Parth Pandya, Rahil Modi
 * Purpose: Assignment submission at San Jose State University
 * Do not use for any purpose without prior consent from Author or institution
 * 
 */

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {
		"sjsu.cmpe275.Lab2.controller",
		"sjsu.cmpe275.Lab2.model",
		"sjsu.cmpe275.Lab2.service",
		"sjsu.cmpe275.Lab2.configurations"
		})
@EnableJpaRepositories("sjsu.cmpe275.Lab2.repositories")
@EntityScan("sjsu.cmpe275.Lab2.model")
public class Main {
	
	
	/*
	 * Main class is the entry point for the application.
	 */
	public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}