package com.trip.TripProject.model;

import lombok.Data;
import org.springframework.boot.context.properties.bind.Nested;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.Date;
import java.util.List;

@Data
@Document(indexName = "trip")
@Setting(settingPath = "static/es-settings.json")
public class Trip {
    @Id
    @Field(name="trip_id", type = FieldType.Keyword)
    private Long tripId;

    @Field(type = FieldType.Date)
    private Date departureTime;

    @Field(type = FieldType.Long)
    private Long price;

    @Field(name="is_active")
    private boolean isActive;

    private Vehicle vehicle;

    private Station station;

    @Field(type = FieldType.Nested)
    private Point origin;

    @Field(type = FieldType.Nested)
    private Point destination;

    private List<StopPoint> stopPoints;

    @Field(type = FieldType.Keyword, name="trip_type")
    private String tripType;

}
