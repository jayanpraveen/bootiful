package com.example.consumer;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class ReservationClient {

	private final WebClient webClient;

	public Flux<Reservation> getAllReservations() {
		return this.webClient.get().uri("http://localhost:8080/reserations").retrieve().bodyToFlux(Reservation.class);
	}

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Reservation {
	private String id, reservationName;
}
