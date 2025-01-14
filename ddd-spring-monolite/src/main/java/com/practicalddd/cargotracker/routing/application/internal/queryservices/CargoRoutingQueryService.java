package com.practicalddd.cargotracker.routing.application.internal.queryservices;


import com.practicalddd.cargotracker.routing.domain.model.aggregates.Voyage;
import com.practicalddd.cargotracker.routing.infrastructure.repositories.jpa.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Application Service class for the Cargo Routing Query service
 */
@Service
public class CargoRoutingQueryService {

    @Autowired
    private VoyageRepository voyageRepository; // Inject Dependencies

    /**
     * Returns all Voyages
     * @return
     */
    @Transactional
    public List<Voyage> findAll(){
        return voyageRepository.findAll();
    }


}
