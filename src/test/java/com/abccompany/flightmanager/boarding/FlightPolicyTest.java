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
        EconomicFlightService underTest = new EconomicFlightService();

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
                boolean regularPassengerAdded = underTest.addPassenger(economicFlight, regularPassenger);
                assertTrue(regularPassengerAdded);
            }

            @Test
            @DisplayName("When removePassenger Then regular passenger should be removed")
            void economicFlightRemovePassengers() {
                int totalPassengers = economicFlight.getPassengers().size();
                underTest.addPassenger(economicFlight, regularPassenger);
                assertEquals(++totalPassengers, economicFlight.getPassengers().size());
                boolean removed = underTest.removePassenger(economicFlight, regularPassenger);
                assertTrue(removed);
            }
        }

        @Nested
        @DisplayName("Given Vip Passenger")
        class VipPassengerTest {
            Passenger vipPassenger;

            @BeforeEach
            void setup() {
                vipPassenger = new Passenger();
                vipPassenger.setId("P2");
                vipPassenger.setName("Maria");
                vipPassenger.setVip(true);
            }

            @Test
            @DisplayName("When addPassenger Then vip passenger should be added")
            void economicFlightAddPassengers() {
                boolean vipPassengerAdded = underTest.addPassenger(economicFlight, vipPassenger);
                assertTrue(vipPassengerAdded);
            }

            @Test
            @DisplayName("When removePassenger Then vip passenger should not be removed")
            void economicFlightRemovePassengers() {
                int totalPassengers = economicFlight.getPassengers().size();
                underTest.addPassenger(economicFlight, vipPassenger);
                assertEquals(++totalPassengers, economicFlight.getPassengers().size());
                boolean removed = underTest.removePassenger(economicFlight, vipPassenger);
                assertFalse(removed);
            }
        }
    }

    @Nested
    @DisplayName("Given Business Flight")
    class BusinessFlightPolicyTest {
        Flight businessFlight;
        BusinessFlightService underTest = new BusinessFlightService();

        @BeforeEach
        void setup() {
            businessFlight = new Flight();
            businessFlight.setId("F2");
            businessFlight.setCategory(FlightCategory.BUSINESS);
            businessFlight.setPassengers(new HashSet<>());
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
            @DisplayName("When addPassenger Then regular passenger should not be added")
            void businessFlightAddPassengers() {
                boolean added = underTest.addPassenger(businessFlight, regularPassenger);
                assertFalse(added);
            }

            @Test
            void businessFlightRemovePassengers() {
                boolean removed = underTest.removePassenger(businessFlight, regularPassenger);
                assertFalse(removed);
            }
        }

        @Nested
        @DisplayName("Given Vip Passenger")
        class VipPassengerTest {
            Passenger vipPassenger;

            @BeforeEach
            void setup() {
                vipPassenger = new Passenger();
                vipPassenger.setId("P2");
                vipPassenger.setName("Maria");
                vipPassenger.setVip(true);
            }

            @Test
            @DisplayName("When addPassenger Then vip passenger should be added")
            void businessFlightAddPassengers() {
                boolean added = underTest.addPassenger(businessFlight, vipPassenger);
                assertTrue(added);
            }

            @Test
            void businessFlightRemovePassengers() {
                int totalPassengers = businessFlight.getPassengers().size();
                underTest.addPassenger(businessFlight, vipPassenger);
                assertEquals(++totalPassengers, businessFlight.getPassengers().size());
                boolean removed = underTest.removePassenger(businessFlight, vipPassenger);
                assertFalse(removed);
            }
        }
    }
}