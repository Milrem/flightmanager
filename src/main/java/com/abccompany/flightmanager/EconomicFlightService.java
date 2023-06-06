package com.abccompany.flightmanager;

public class EconomicFlightService implements FlightPolicy {
    public boolean addPassenger(Flight flight, Passenger passenger) {
        return flight.getPassengers().add(passenger);
    }
    public boolean removePassenger(Flight flight, Passenger passenger) {
        if (passenger.isVip()) {
            return false;
        }
        return flight.getPassengers().remove(passenger);
    }
}
