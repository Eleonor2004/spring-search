package com.trip.TripProject.repository;

import co.elastic.clients.elasticsearch._types.GeoDistanceType;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQueryField;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.util.ObjectBuilder;
import com.trip.TripProject.helper.UserFilterOptions;
import com.trip.TripProject.helper.UserQuery;
import com.trip.TripProject.helper.UserSortOptions;
import com.trip.TripProject.model.Trip;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TripCustomRepository {
    private ElasticsearchOperations operations;

    IndexCoordinates index = IndexCoordinates.of("trips");
    SearchHits<Trip> searchTrip(UserQuery input, UserFilterOptions filters, UserSortOptions sortOptions) {
        NativeQuery searchQuery = NativeQuery.builder()
                .withQuery(q -> {
                    return q.bool(b0 -> b0
                            .must(Arrays.asList(
                                    QueryBuilders.match(m -> m.field("is_active").query(true)),
                                    QueryBuilders.match(m -> m.field("trip_type").query(input.getTripType().getValue())),
                                    QueryBuilders.bool(b1 -> {
                                        List<Query> queryList = new ArrayList<>();
                                        queryList.add(
                                                QueryBuilders.bool(b2 -> b2
                                                        .must(Arrays.asList(
                                                                QueryBuilders.nested(ns -> ns.path("origin").query(q1 -> q1.match(m -> m.field("origin.name").query(input.getOrigin())))),
                                                                QueryBuilders.nested(ns -> ns.path("destination").query(q1 -> q1.match(m -> m.field("destination.name").query(input.getDestination()))))
                                                        )))
                                        );

                                        if (filters.getAllowDestinationAsStopPoint()) {
                                            queryList.add(
                                                    QueryBuilders.bool(b2 -> b2
                                                            .must(Arrays.asList(
                                                                    QueryBuilders.nested(ns -> ns.path("origin").query(q1 -> q1.match(m -> m.field("origin.name").query(input.getOrigin())))),
                                                                    QueryBuilders.nested(ns -> ns.path("stop_points").query(q1 -> q1.bool(b3 -> b3.must(Arrays.asList(
                                                                            QueryBuilders.match(m -> m.field("stop_points.drop").query(true)),
                                                                            QueryBuilders.match(m -> m.field("stop_points.point.name").query(input.getDestination()))
                                                                    )))))
                                                            )))
                                            );
                                        }

                                        if (filters.getAllowOriginAsStopPoint()) {
                                            queryList.add(
                                                    QueryBuilders.bool(b2 -> b2
                                                            .must(Arrays.asList(
                                                                    QueryBuilders.nested(ns -> ns.path("stop_points").query(q1 -> q1.bool(b3 -> b3.must(Arrays.asList(
                                                                            QueryBuilders.match(m -> m.field("stop_points.pickup").query(true)),
                                                                            QueryBuilders.match(m -> m.field("stop_points.point.name").query(input.getOrigin()))
                                                                    ))))),
                                                                    QueryBuilders.nested(ns -> ns.path("destination").query(q1 -> q1.match(m -> m.field("destination.name").query(input.getDestination()))))
                                                            )))
                                            );
                                        }

                                        if (filters.getAllowOriginAsStopPoint() && filters.getAllowDestinationAsStopPoint()) {
                                            queryList.add(
                                                    QueryBuilders.bool(b2 -> b2
                                                            .must(Arrays.asList(
                                                                    QueryBuilders.nested(ns -> ns.path("stop_points").query(q1 -> q1.bool(b3 -> b3.must(Arrays.asList(
                                                                            QueryBuilders.match(m -> m.field("stop_points.pickup").query(true)),
                                                                            QueryBuilders.match(m -> m.field("stop_points.point.name").query(input.getOrigin()))
                                                                    ))))),
                                                                    QueryBuilders.nested(ns -> ns.path("stop_points").query(q1 -> q1.bool(b3 -> b3.must(Arrays.asList(
                                                                            QueryBuilders.match(m -> m.field("stop_points.drop").query(true)),
                                                                            QueryBuilders.match(m -> m.field("stop_points.point.name").query(input.getDestination()))
                                                                    )))))
                                                            )))
                                            );
                                        }

                                        return b1.should(queryList);
                                    })
                            )));
                })
                .withFilter(f -> {
                    if (filters.getAgencyName() != null)
                        f.match(m0 -> m0.field("agency_name").query(filters.getAgencyName()));
                    if (filters.getMaxPrice() != null || filters.getMinPrice() != null) {
                        RangeQuery.Builder range = QueryBuilders.range().field("price");
                        if (filters.getMaxPrice() != null)
                            range.lte(JsonData.of(filters.getMaxPrice()));
                        if (filters.getMinPrice() != null)
                            range.gte(JsonData.of(filters.getMinPrice()));
                        f.range(range.build());
                    }
                    if (filters.getLuggageType() != null && !filters.getLuggageType().isEmpty())
                        f.terms(t0 -> t0.field("luggage_type").terms((TermsQueryField) filters.getLuggageType()));
                    if (filters.getVehicleType() != null)
                        f.term(t0 -> t0.field("vehicle.vehicle_type").field(filters.getVehicleType()));
                    if (filters.getNumberOfSeats() != null)
                        f.range(r0 -> r0.field("number_of_seats").gte(JsonData.of(filters.getNumberOfSeats())));
                    if (input.getGeoLocation() != null && filters.getMaxDistanceFromStation() != null)
                        f.geoDistance(gd -> gd.field("station.location").distanceType(GeoDistanceType.Plane).location(l0 -> l0.latlon(l1 -> l1.lat(input.getGeoLocation().getLat()).lon(input.getGeoLocation().getLon()))));

                    return f;
                })
                .build();


        SearchHits<Trip> tripResults = operations.search(searchQuery, Trip.class, index);
        return tripResults;
    }


}
