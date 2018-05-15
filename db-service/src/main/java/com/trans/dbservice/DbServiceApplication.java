package com.trans.dbservice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

import com.trans.dbservice.entities.Accident;
import com.trans.dbservice.entities.Driver;
import com.trans.dbservice.entities.Location;
import com.trans.dbservice.entities.Manager;
import com.trans.dbservice.entities.ParkingTicket;
import com.trans.dbservice.entities.TrafficTicket;
import com.trans.dbservice.entities.Training;
import com.trans.dbservice.entities.Vehicle;
import com.trans.dbservice.entities.enums.TrafficTicketCause;
import com.trans.dbservice.repositories.DriverRepository;
import com.trans.dbservice.repositories.EventRepository;
import com.trans.dbservice.repositories.ManagerRepository;
import com.trans.dbservice.repositories.TrainingRepository;
import com.trans.dbservice.repositories.VehicleRepository;


@EnableEurekaClient
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.trans.dbservice.repositories")
@SpringBootApplication
public class DbServiceApplication {

	@Autowired
	VehicleRepository vr;
	
	@Autowired
	EventRepository er;
	
	@Autowired
	ManagerRepository mr;
	
	@Autowired
	DriverRepository dr;
	
	@Autowired
	TrainingRepository tr;
	
	@Bean
	CommandLineRunner runner(){
		return args -> {
			addData();
			
		};
	}
	
	private void addData() {
		
		dr.deleteAll();
		Arrays.asList("Amit Znati", "sdfsd sdff", "werr wer")
		.forEach(x -> dr.save(new Driver(x)));
		dr.findAll().forEach(System.out::println);
		
		mr.deleteAll();
		Arrays.asList("Amit mgr", "tom", "jons")
		.forEach(x -> mr.save(new Manager(x)));
		mr.findAll().forEach(System.out::println);
		
		String[] licenses = {"34234234","234234234","45334324"};
		for(int i=0;i<licenses.length;i++) {
			Vehicle v = new Vehicle(null, licenses[i], dr.findOne(Long.valueOf(i)), null);
			vr.save(v);
		}
		
		for(int i=0;i<3;i++) 
		{
			Random random = new Random();
			ParkingTicket pt = new ParkingTicket(random.nextDouble() + 1000);
			Accident ac = new Accident("Other driver"+random.nextInt(20) +1, "436"+random.nextInt(20), "3453465465"+random.nextInt(20), "3453465465"+random.nextInt(20));
			TrafficTicket tt = new TrafficTicket(TrafficTicketCause.RED_LIGHT);
			Arrays.asList(ac,pt,tt)
			.forEach(e -> {
				e.setCreatedAt(getRandomDate());
				e.setDescription("Description" + random.nextInt(100));
				e.setDriver(dr.findOne((long)1 + random.nextInt(dr.findAll().size())));
				e.setLocation(new Location(null, "ciy", "street"+random.nextInt(100),null));
				er.save(e);
			});
			
			Training t = new Training(null, 
					"desc", 
					"title"+random.nextInt(100), 
					getRandomDate(), 
					mr.findOne(random.nextLong() + 1), random.nextInt(100)+3, 
					dr.findAll());
			
			tr.save(t);
			Driver d = dr.findOne(Long.valueOf(i+1));
			d.setTrainings(tr.findAll());
			dr.save(d);
		}
		
		dr.findAll().stream().filter(d -> d.getId() % 2 == 1)
		.map(driver -> {
			driver.setEvents(er.findAll().stream().filter(event -> event.getId() % 2 == 0).collect(Collectors.toList()));
			return driver;
		});
		
		dr.findAll().stream().filter(d -> d.getId() % 2 == 0)
		.map(driver -> {
			driver.setEvents(er.findAll().stream().filter(event -> event.getId() % 2 == 1).collect(Collectors.toList()));
			return driver;
		});
		
		
		
		
	}
	
	private Date getRandomDate() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date date = null;
		Random random = new Random();
		String dateInString = "2018-"+random.nextInt(12)+1+"-"+random.nextInt(28)+1+"T"+random.nextInt(24)+":"+random.nextInt(60)+":00";
		
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static void main(String[] args) {
		SpringApplication.run(DbServiceApplication.class, args);
	}
	
	@Component
	public class ExposeEntityIdRestMvcConfiguration extends RepositoryRestConfigurerAdapter {

	  @Override
	  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
	    config.exposeIdsFor(Training.class);
	    config.exposeIdsFor(Accident.class);
	    config.exposeIdsFor(Driver.class);
	    config.exposeIdsFor(Location.class);
	    config.exposeIdsFor(Manager.class);
	    config.exposeIdsFor(ParkingTicket.class);
	    config.exposeIdsFor(TrafficTicket.class);
	    config.exposeIdsFor(Vehicle.class);
	  }
	}
}


