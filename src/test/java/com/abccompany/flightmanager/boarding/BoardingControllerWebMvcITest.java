package com.abccompany.flightmanager.boarding;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(BoardingController.class)
@AutoConfigureDataJpa
class BoardingControllerWebMvcITest {
    @Autowired
    private MockMvc mvc;

    private final String endpointUrl = "/boarding/{flightId}/{passengerId}";

    @Test
    void addPassenger() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post(endpointUrl, "E8", "P9")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotAcceptable());
        mvc.perform(
                MockMvcRequestBuilders.post(endpointUrl, "E8", "P8")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    void removePassenger() {
    }
}