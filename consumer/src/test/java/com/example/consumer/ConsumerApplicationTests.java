package com.example.consumer;

import com.github.tomakehurst.wiremock.client.WireMock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureStubRunner(ids = "com.example:producer:+:8080", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
// @AutoConfigureWireMock(port = 8080)
public class ConsumerApplicationTests {

	@Autowired
	ReservationClient client;

	@Test
	public void contextLoads() {

		// var json = " [ { \"id\": \"1\", \"reservationName\": \"tommy\" } ] ";

		// WireMock.stubFor(WireMock.get("/reserations")
		// .willReturn(WireMock.aResponse().withHeader(HttpHeaders.CONTENT_TYPE,
		// MediaType.APPLICATION_JSON_VALUE)
		// .withStatus(HttpStatus.OK.value()).withBody(json)));

		Flux<Reservation> reserveration = this.client.getAllReservations();
		StepVerifier.create(reserveration)
				.expectNextMatches(r -> r.getId() != null && r.getName().equalsIgnoreCase("tommy"))
				.verifyComplete();

	}

}
