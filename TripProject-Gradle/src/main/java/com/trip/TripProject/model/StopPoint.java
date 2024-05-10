package com.trip.TripProject.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class StopPoint {
    @Field(type = FieldType.Nested)
    private Point point;

    private boolean pickup;

    private boolean drop;
}
