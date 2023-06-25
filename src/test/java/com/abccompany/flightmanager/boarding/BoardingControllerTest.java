package com.abccompany.flightmanager.boarding;

import com.abccompany.flightmanager.flight.Flight;
import com.abccompany.flightmanager.flight.FlightCategory;
import com.abccompany.flightmanager.flight.FlightRepository;
import com.abccompany.flightmanager.passenger.Passenger;
import com.abccompany.flightmanager.passenger.PassengerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(BoardingController.class)
@ExtendWith(MockitoExtension.class)
class BoardingControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private FlightRepository flightRepository;
    @MockBean
    private PassengerRepository passengerRepository;

    private Flight f1;
    private Passenger p1, p3;

    @BeforeEach
    void setUp() {
        p1 = new Passenger();
        p1.setId("P1");
        p1.setName("João");
        p1.setVip(true);
        p3 = new Passenger();
        p3.setId("P3");
        p3.setName("Julia");
        p3.setVip(false);
        f1 = new Flight();
        f1.setId("F1");
        f1.setCategory(FlightCategory.ECONOMIC);
        f1.setPassengers(new HashSet<>());
        f1.getPassengers().add(p3);

        Mockito.when(flightRepository.findById(ArgumentMatchers.anyString()))
                .then(invocationOnMock -> Optional.empty());
        Mockito.when(passengerRepository.findById(ArgumentMatchers.anyString()))
                .then(invocationOnMock -> Optional.empty());
        Mockito.when(flightRepository.findById(f1.getId()))
                .then(invocationOnMock -> Optional.of(f1));
        Mockito.when(passengerRepository.findById(p1.getId()))
                .then(invocationOnMock -> Optional.of(p1));
        Mockito.when(passengerRepository.findById(p3.getId()))
                .then(invocationOnMock -> Optional.of(p3));
    }

    @Test
    void addPassenger() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post("/boarding/{flightId}/{passengerId}", f1.getId(), p1.getId())
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isAccepted());
        mvc.perform(
                MockMvcRequestBuilders.post("/boarding/{flightId}/{passengerId}", "F2", p1.getId())
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isConflict());
        mvc.perform(
                MockMvcRequestBuilders.post("/boarding/{flightId}/{passengerId}", f1.getId(), "P2")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isConflict());
        mvc.perform(
                MockMvcRequestBuilders.post("/boarding/{flightId}/{passengerId}", f1.getId(), p1.getId())
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotAcceptable());
    }

    @Test
    void removePassenger() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.delete("/boarding/{flightId}/{passengerId}", f1.getId(), p3.getId())
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isAccepted());
        mvc.perform(
                MockMvcRequestBuilders.delete("/boarding/{flightId}/{passengerId}", "F2", p1.getId())
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isConflict());
        mvc.perform(
                MockMvcRequestBuilders.delete("/boarding/{flightId}/{passengerId}", f1.getId(), "P2")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isConflict());
        mvc.perform(
                MockMvcRequestBuilders.delete("/boarding/{flightId}/{passengerId}", f1.getId(), p1.getId())
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotAcceptable());
    }
}