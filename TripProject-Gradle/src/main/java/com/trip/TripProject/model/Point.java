package com.trip.TripProject.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Data
@Document(indexName = "point")
public class Point {
    @Id
    private String id;

    @Field(name="place", type= FieldType.Text)
    private String place;

    @Field(name="coordinates")
    private GeoPoint coordinates;

    @Field(name="name")
    private String name;
}
