package com.trip.TripProject.repository;


import com.trip.TripProject.model.Agency;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends ElasticsearchRepository<Agency, Long> {


}


