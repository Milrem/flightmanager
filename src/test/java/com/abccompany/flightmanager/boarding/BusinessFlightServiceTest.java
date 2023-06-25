package com.abccompany.flightmanager.boarding;

import com.abccompany.flightmanager.flight.Flight;
import com.abccompany.flightmanager.flight.FlightCategory;
import com.abccompany.flightmanager.passenger.Passenger;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class BusinessFlightServiceTest {

    @Test
    void addPassenger() {
        BusinessFlightService underTest = new BusinessFlightService();

        //Given
        Flight businessFlight = new Flight();
        businessFlight.setId("F2");
        businessFlight.setCategory(FlightCategory.BUSINESS);
        businessFlight.setPassengers(new HashSet<>());

        Passenger regularPassenger = new Passenger();
        regularPassenger.setId("P1");
        regularPassenger.setName("João");

        Passenger vipPassenger = new Passenger();
        vipPassenger.setId("P2");
        vipPassenger.setName("Maria");
        vipPassenger.setVip(true);

        int totalPassengers = businessFlight.getPassengers().size();

        //When
        boolean regularPassengerAdded = underTest.addPassenger(businessFlight, regularPassenger);
        boolean vipPassengerAdded = underTest.addPassenger(businessFlight, vipPassenger);

        //Then
        assertFalse(regularPassengerAdded);
        assertTrue(vipPassengerAdded);
        assertEquals(totalPassengers + 1, businessFlight.getPassengers().size());
    }

    @Test
    void removePassenger() {
        BusinessFlightService underTest = new BusinessFlightService();

        //Given
        Flight businessFlight = new Flight();
        businessFlight.setId("F2");
        businessFlight.setCategory(FlightCategory.BUSINESS);
        businessFlight.setPassengers(new HashSet<>());

        Passenger vipPassenger = new Passenger();
        vipPassenger.setId("P2");
        vipPassenger.setName("Maria");
        vipPassenger.setVip(true);

        int totalPassengers = businessFlight.getPassengers().size();

        //When
        underTest.addPassenger(businessFlight, vipPassenger);
        assertEquals(totalPassengers + 1, businessFlight.getPassengers().size());
        boolean removed = underTest.removePassenger(businessFlight, vipPassenger);

        //Then
        assertFalse(removed);
        assertEquals(totalPassengers + 1, businessFlight.getPassengers().size());
    }
}