package com.trip.TripProject.helper;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.trip.TripProject.model.LuggageType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Contains nullable fields the user can use to filter in/out trips
 */
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserFilterOptions {
    private Double minPrice;
    private Double maxPrice;
    private Double maxDistance;
    private List<LuggageType> luggageType;
    private String vehicleType;
    private Integer numberOfSeats;
    private String agencyName;
    private Boolean allowOriginAsStopPoint;
    private Boolean allowDestinationAsStopPoint;
    private Double maxDistanceFromStation;

    public UserFilterOptions(Double minPrice, Double maxPrice, Double maxDistance, List<LuggageType> luggageType, String vehicleType, Integer numberOfSeats, String agencyName, Boolean allowOriginAsStopPoint, Boolean allowDestinationAsStopPoint, Double maxDistanceFromStation) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.maxDistance = maxDistance;
        this.luggageType = luggageType;
        this.vehicleType = vehicleType;
        this.numberOfSeats = numberOfSeats;
        this.agencyName = agencyName;
        this.allowOriginAsStopPoint = allowOriginAsStopPoint;
        this.allowDestinationAsStopPoint = allowDestinationAsStopPoint;
        this.maxDistanceFromStation = maxDistanceFromStation;
    }
}
