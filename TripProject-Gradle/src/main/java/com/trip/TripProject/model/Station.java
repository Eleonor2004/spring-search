package com.trip.TripProject.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class Station {
    @Id
    @Field(name="station_id")
    private long stationId;

    @Field(name="station_name")
    private String stationName;

    @Field(type = FieldType.Nested)
    private Point location;
}
