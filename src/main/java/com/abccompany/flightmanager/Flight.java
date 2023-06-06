package com.abccompany.flightmanager;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Flight {
    private String id;
    private FlightCategory category;
    private Set<Passenger> passengers;
}
