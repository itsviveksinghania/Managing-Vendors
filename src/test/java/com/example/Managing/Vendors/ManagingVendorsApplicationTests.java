package com.example.Managing.Vendors;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ManagingVendorsApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;


	@Test
	void contextLoads() {
		assertThat(testRestTemplate.getForObject("http://localhost:" + port + "/",
				String.class));
	}

	@Test
	public void main() {
		ManagingVendorsApplication.main(new String[] {});
	}
}
