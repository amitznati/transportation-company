package com.trans.managerservice;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.trans.managerservice.dto.Training;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerServiceApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testDates() {
		String time1 = "2017-10-06T17:48:23.558";
		// convert String to LocalDateTime
		LocalDateTime localDateTime = LocalDateTime.parse(time1);
		
		assertNotNull(localDateTime);
		
		
	}

}
