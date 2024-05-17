package com.trip.TripProject.helper;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserQuery {
    private String origin;
    private String destination;
    private TripType tripType;
    private GeoPoint geoLocation;
}
