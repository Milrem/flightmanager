package com.abccompany.flightmanager.flight;

import com.abccompany.flightmanager.passenger.Passenger;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Flight {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private FlightCategory category;
    @OneToMany()
    private Set<Passenger> passengers;
}
