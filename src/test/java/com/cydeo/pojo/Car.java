package com.cydeo.pojo;

import lombok.*;
import org.hamcrest.Condition;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class Car {

    private String model;
    private String make;
    private int year;
    private boolean autopilot;


}
