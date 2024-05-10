package com.trip.TripProject.configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.Value;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.validation.annotation.Validated;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.trip.TripProject.repository")
@ComponentScan(basePackages = {"com.trip.TripProject"})
public class ElasticsearchConfig extends ElasticsearchConfiguration {
/*
    //    // URL
//    String serverUrl = "192.168.176.129:9200";
//
//
//    // Create the low-level client
//    RestClient restClient = RestClient
//            .builder(HttpHost.create(serverUrl))
//            .build();
//
//    // Create the transport with a Jackson mapper
//    ElasticsearchTransport transport = new RestClientTransport(
//            restClient, new JacksonJsonpMapper());
//
//    // And create the API client
//    ElasticsearchClient esClient = new ElasticsearchClient(transport);
//
//    @Override
//    public ClientConfiguration clientConfiguration() {
//        return null;
//    }
//
*/
/*
    @Bean
    public RestClient getRestClient() {
        RestClient restClient = RestClient.builder(
                new HttpHost("192.168.176.129", 9200)).build();
        return restClient;

    }

    @Bean
    public ElasticsearchTransport getElasticsearchTransport() {
        return new RestClientTransport(getRestClient(), new JacksonJsonpMapper());
    }

    @Bean
    public ElasticsearchClient getElasticsearchClient() {
        ElasticsearchClient client = new ElasticsearchClient(getElasticsearchTransport());
        return client;
    }

*/


    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder().connectedTo("192.168.176.129:9200").build();
    }
}

