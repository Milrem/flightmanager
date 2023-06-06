package com.abccompany.flightmanager;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Passenger {
    private String id;
    private String name;
    private boolean vip;
}
