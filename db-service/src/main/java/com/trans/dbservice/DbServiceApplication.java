package com.trans.dbservice;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.trans.dbservice.entities.Driver;
import com.trans.dbservice.entities.Manager;
import com.trans.dbservice.repositories.DriverRepository;
import com.trans.dbservice.repositories.ManagerRepository;

@EnableEurekaClient
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.trans.dbservice.repositories")
@SpringBootApplication
public class DbServiceApplication {

	@Bean
	CommandLineRunner runner(DriverRepository dr,ManagerRepository mr){
		return args -> {
			addData(dr,mr);
			
		};
	}
	
	private void addData(DriverRepository dr,ManagerRepository mr) {
		dr.deleteAll();
		Arrays.asList("Amit Znati", "sdfsd sdff", "werr wer")
		.forEach(x -> dr.save(new Driver(x)));
		dr.findAll().forEach(System.out::println);
		
		mr.deleteAll();
		Arrays.asList("Amit mgr", "tom", "jons")
		.forEach(x -> mr.save(new Manager(x)));
		mr.findAll().forEach(System.out::println);
		
		
	}

	public static void main(String[] args) {
		SpringApplication.run(DbServiceApplication.class, args);
	}
}
