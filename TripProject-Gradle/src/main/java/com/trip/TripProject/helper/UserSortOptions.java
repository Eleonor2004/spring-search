package com.trip.TripProject.helper;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.core.suggest.response.SortBy;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserSortOptions {
    private List<SortCriterion> sortCriteria;

    public UserSortOptions(List<SortCriterion> sortCriteria) {
        this.sortCriteria = sortCriteria;
    }

    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class SortCriterion {
        private SortBy sortBy;
        private SortDirection sortDirection;

        public enum SortBy {
            PRICE, DISTANCE, REPUTATION;
        }

        public enum SortDirection {
            ASC, DESC;
        }
    }
}