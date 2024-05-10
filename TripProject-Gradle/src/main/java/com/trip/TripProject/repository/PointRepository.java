package com.trip.TripProject.repository;

import com.trip.TripProject.model.Point;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends ElasticsearchRepository<Point, Long> {
    // Optional custom methods specific to Point searches
}
