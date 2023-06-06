package com.abccompany.flightmanager;

public interface FlightPolicy {
    boolean addPassenger(Flight flight, Passenger passenger);
    boolean removePassenger(Flight flight, Passenger passenger);
}
