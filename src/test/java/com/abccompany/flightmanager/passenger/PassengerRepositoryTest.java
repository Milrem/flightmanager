package com.abccompany.flightmanager.passenger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PassengerRepositoryTest {
    @Autowired
    private PassengerRepository passengerRepository;

    @Test
    void findByName() {
        String name = "Ely Nogueira";
        Optional<Passenger> found = passengerRepository.findByName(name);
        assertTrue(found.isPresent());
        assertEquals(name, found.get().getName());

        name = "Jonh Smith";
        found = passengerRepository.findByName(name);
        assertTrue(found.isPresent());
        assertEquals(name, found.get().getName());
    }
}