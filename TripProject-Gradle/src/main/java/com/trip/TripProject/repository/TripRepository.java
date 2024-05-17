package com.trip.TripProject.repository;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.trip.TripProject.model.Trip;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TripRepository extends ElasticsearchRepository<Trip, Long> {
    // Optional custom methods specific to Trip searches
}
