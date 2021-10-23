package com.example.producer;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Flux;

@Import(ReservationHttpConfig.class)
@SpringBootTest(properties = "server.port=0",
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseClass {

    @MockBean
    ReservationRepository resvRepo;

    @LocalServerPort
    int port;

    @BeforeEach
    public void befores(){

        RestAssured.baseURI = "http://localhost:" + port;

        Mockito.when(resvRepo.findAll()).thenReturn(Flux.just(new Reservation("1", "tommy")));

    }

}
