package com.trip.TripProject.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.trip.TripProject.model.Trip;
import com.trip.TripProject.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trip/")
public class TripController {
    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public void save(@RequestBody Trip trip) {
        tripService.save(trip);
    }

    @GetMapping("/{id}")
    public Trip findById(@PathVariable("id") Long id) {
        return tripService.findById(id);
    }
}
