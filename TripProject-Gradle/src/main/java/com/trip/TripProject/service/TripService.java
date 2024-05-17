package com.trip.TripProject.service;

import co.elastic.clients.elasticsearch._types.Script;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.trip.TripProject.model.Trip;
import com.trip.TripProject.repository.TripRepository;
import lombok.Data;
import org.elasticsearch.client.RequestOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.ScriptType;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.Optional;

@Service
@Data
public class TripService {
    private final TripRepository tripRepository;

    public void save(final Trip trip){
        tripRepository.save(trip);
    }

    public Trip findById(final Long id) {
        return tripRepository.findById(id).orElse(null);
    }
}



