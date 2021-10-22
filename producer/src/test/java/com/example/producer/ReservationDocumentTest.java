package com.example.producer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class ReservationDocumentTest {

	@Autowired
	private ReservationRepository resvRepo;

	@Test
	public void presist() throws Exception {

		Reservation res = new Reservation(null, "tommy");
		Mono<Reservation> save = this.resvRepo.save(res);
		StepVerifier.create(save).expectNextMatches(r -> r.getId() != null && r.getName().equalsIgnoreCase("tommy"))
				.verifyComplete();

	}
}
