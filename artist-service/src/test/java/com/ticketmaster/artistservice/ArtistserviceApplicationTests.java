package com.ticketmaster.artistservice;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class ArtistserviceApplicationTests {
	
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
		assertNotNull(applicationContext);
	}
	
	@Test
	void mainShouldRunWithoutExceptionWhenAppStarts() {
		final String serverPortProperty = "--server.port=8084";
		final ConfigurableApplicationContext applConfigurableApplicationContext = SpringApplication.run(ArtistserviceApplication.class, serverPortProperty);
		assertNotNull(applConfigurableApplicationContext);
		final int exit = SpringApplication.exit(applConfigurableApplicationContext);
		assertEquals(exit, 0);
	}

}
