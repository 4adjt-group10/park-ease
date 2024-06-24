package com.parkease;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.DockerClientFactory;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ParkEaseApplicationTests {

//	@Test
//	void contextLoads() {
//	}
//
//	@Test
//	void testDockerConnection() {
//		DockerClientFactory.instance().client();
//	}

}
