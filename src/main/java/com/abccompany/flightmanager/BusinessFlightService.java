package com.abccompany.flightmanager;

public class BusinessFlightService implements FlightPolicy {
    public boolean addPassenger(Flight flight, Passenger passenger) {
        if (!passenger.isVip()) {
            return false;
        }
        return flight.getPassengers().add(passenger);
    }
    public boolean removePassenger(Flight flight, Passenger passenger) {
        return false;
    }
}
