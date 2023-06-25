package com.abccompany.flightmanager.boarding;

import com.abccompany.flightmanager.flight.Flight;
import com.abccompany.flightmanager.flight.FlightCategory;
import com.abccompany.flightmanager.passenger.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class FlightPolicyTest {
    @Nested
    @DisplayName("Given Economic Flight")
    class EconomicFlightPolicyTest {
        Flight economicFlight;

        @BeforeEach
        void setup() {
            economicFlight = new Flight();
            economicFlight.setId("F1");
            economicFlight.setCategory(FlightCategory.ECONOMIC);
            economicFlight.setPassengers(new HashSet<>());
        }

        @Nested
        @DisplayName("Given Regular Passenger")
        class RegularPassengerTest {
            Passenger regularPassenger;

            @BeforeEach
            void setup() {
                regularPassenger = new Passenger();
                regularPassenger.setId("P1");
                regularPassenger.setName("João");
            }

            @Test
            @DisplayName("When addPassenger Then regular passenger should be added")
            void economicFlightAddPassengers() {
                //Given
                Passenger vipPassenger = new Passenger();
                vipPassenger.setId("P2");
                vipPassenger.setName("Maria");
                vipPassenger.setVip(true);

                //When
                EconomicFlightService underTest = new EconomicFlightService();
                boolean regularPassengerAdded = underTest.addPassenger(economicFlight, regularPassenger);
                boolean vipPassengerAdded = underTest.addPassenger(economicFlight, vipPassenger);

                //Then
                assertTrue(regularPassengerAdded);
                assertTrue(vipPassengerAdded);
            }

            @Test
            void economicFlightRemovePassengers() {
            }
        }
    }

    @Nested
    class BusinessFlightPolicyTest {
        @Test
        @DisplayName("Given Business Flight When addPassenger Then only vip passenger should be added")
        void businessFlightAddPassengers() {
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

            //When
            BusinessFlightService underTest = new BusinessFlightService();
            boolean regularPassengerAdded = underTest.addPassenger(businessFlight, regularPassenger);
            boolean vipPassengerAdded = underTest.addPassenger(businessFlight, vipPassenger);

            //Then
            assertFalse(regularPassengerAdded);
            assertTrue(vipPassengerAdded);
        }

        @Test
        void businessFlightRemovePassengers() {
        }
    }
}