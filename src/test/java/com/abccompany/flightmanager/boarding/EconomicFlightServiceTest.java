package com.abccompany.flightmanager.boarding;

import com.abccompany.flightmanager.flight.Flight;
import com.abccompany.flightmanager.flight.FlightCategory;
import com.abccompany.flightmanager.passenger.Passenger;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class EconomicFlightServiceTest {

    @Test
    void addPassenger() {
        EconomicFlightService underTest = new EconomicFlightService();

        //Given
        Flight economicFlight = new Flight();
        economicFlight.setId("F1");
        economicFlight.setCategory(FlightCategory.ECONOMIC);
        economicFlight.setPassengers(new HashSet<>());

        Passenger regularPassenger = new Passenger();
        regularPassenger.setId("P1");
        regularPassenger.setName("João");

        Passenger vipPassenger = new Passenger();
        vipPassenger.setId("P2");
        vipPassenger.setName("Maria");
        vipPassenger.setVip(true);

        int totalPassengers = economicFlight.getPassengers().size();

        //When
        boolean regularPassengerAdded = underTest.addPassenger(economicFlight, regularPassenger);
        boolean vipPassengerAdded = underTest.addPassenger(economicFlight, vipPassenger);

        //Then
        assertTrue(regularPassengerAdded);
        assertTrue(vipPassengerAdded);
        assertEquals(totalPassengers + 2, economicFlight.getPassengers().size());
    }

    @Test
    void removePassenger() {
        EconomicFlightService underTest = new EconomicFlightService();

        //Given
        Flight economicFlight = new Flight();
        economicFlight.setId("F1");
        economicFlight.setCategory(FlightCategory.ECONOMIC);
        economicFlight.setPassengers(new HashSet<>());

        Passenger regularPassenger = new Passenger();
        regularPassenger.setId("P1");
        regularPassenger.setName("João");

        Passenger vipPassenger = new Passenger();
        vipPassenger.setId("P2");
        vipPassenger.setName("Maria");
        vipPassenger.setVip(true);

        int totalPassengers = economicFlight.getPassengers().size();

        //When
        underTest.addPassenger(economicFlight, regularPassenger);
        underTest.addPassenger(economicFlight, vipPassenger);
        assertEquals(totalPassengers + 2, economicFlight.getPassengers().size());
        boolean regularPassengerRemoved = underTest.removePassenger(economicFlight, regularPassenger);
        boolean vipPassengerRemoved = underTest.removePassenger(economicFlight, vipPassenger);

        //Then
        assertTrue(regularPassengerRemoved);
        assertFalse(vipPassengerRemoved);
        assertEquals(totalPassengers + 1, economicFlight.getPassengers().size());
    }
}