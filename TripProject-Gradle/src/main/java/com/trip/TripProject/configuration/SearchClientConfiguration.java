package com.trip.TripProject.configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.JsonpMapper;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.mapping.model.SnakeCaseFieldNamingStrategy;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.trip.TripProject.repository")
@ComponentScan(basePackages = {"com.trip.TripProject"})
public class SearchClientConfiguration extends ElasticsearchConfiguration {
    ElasticsearchOperations operations;

    @Autowired
    ElasticsearchClient elasticsearchClient;

    @Autowired
    RestClient restClient;

    @Autowired
    JsonpMapper jsonpMapper;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo("localhost:9200").build();
    }


}

