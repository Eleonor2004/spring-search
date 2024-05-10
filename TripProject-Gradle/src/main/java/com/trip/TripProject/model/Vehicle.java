package com.trip.TripProject.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Data
public class Vehicle {
    @Id
    private long vehicleId;
    private String vehicleType;
    private String serialNumber;
    private String brand;
    private int numberOfSeats;

}
