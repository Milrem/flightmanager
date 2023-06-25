package com.abccompany.flightmanager.boarding;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BoardingControllerITest {
    @Value("${local.server.port}")
    private int httpPort;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String endpointUrl = "http://localhost:{port}/boarding/{flightId}/{passengerId}";

    @Test
    void addPassenger() {
        Assertions.assertThat(restTemplate.postForEntity(endpointUrl, null, Void.class, "E8", "P9"))
                .hasFieldOrPropertyWithValue("status", 406);

        Assertions.assertThat(restTemplate.postForEntity(endpointUrl, null, Void.class, "E8", "P8"))
                .hasFieldOrPropertyWithValue("status", 202);
    }

    @Test
    void removePassenger() {
    }
}