package com.abccompany.flightmanager.passenger;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Passenger {
    @Id
    private String id;
    private String name;
    private boolean vip;
}
