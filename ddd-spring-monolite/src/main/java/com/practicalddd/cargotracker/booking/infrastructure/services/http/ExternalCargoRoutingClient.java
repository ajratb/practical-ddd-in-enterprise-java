package com.practicalddd.cargotracker.booking.infrastructure.services.http;

import com.practicalddd.cargotracker.shareddomain.model.TransitPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Type safe Rest client for the Routing Service API
 */

@Service
public class ExternalCargoRoutingClient {

    @Autowired
    private RestTemplate restTemplate;

    public TransitPath findOptimalRoute(String origin, String destination, String arrivalDeadline) {
        final String REST_URI
                = "http://localhost:8080/voyageRouting/optimalRoute";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(REST_URI)
                .queryParam("origin", origin)
                .queryParam("destination", destination)
                .queryParam("deadline", arrivalDeadline);

        ResponseEntity<TransitPath> response =
                restTemplate.getForEntity(builder.build().encode().toUri(), TransitPath.class);

        return response.getBody();
    }
}
