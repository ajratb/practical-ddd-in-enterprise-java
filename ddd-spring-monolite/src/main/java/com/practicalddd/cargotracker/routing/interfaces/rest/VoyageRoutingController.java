package com.practicalddd.cargotracker.routing.interfaces.rest;


import com.practicalddd.cargotracker.routing.application.internal.queryservices.CargoRoutingQueryService;
import com.practicalddd.cargotracker.routing.domain.model.aggregates.Voyage;
import com.practicalddd.cargotracker.routing.domain.model.entities.CarrierMovement;
import com.practicalddd.cargotracker.shareddomain.model.TransitEdge;
import com.practicalddd.cargotracker.shareddomain.model.TransitPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//@Path("/voyageRouting")
@RestController
public class VoyageRoutingController {

    @Autowired
    private CargoRoutingQueryService cargoRoutingQueryService; // Application Service Dependency

    /**
     * A highly simplistic implementation of the Routing algorithm.
     * It works only with the specifications given in the test case (CNHKG - USNYC and 2019-09-28).
     * The Domain Model can be changed to implement more sophisticated algorithms
     * @param originUnLocode
     * @param destinationUnLocode
     * @param deadline
     * @return
     */
    @GetMapping("/voyageRouting/optimalRoute")
//    @Path("/optimalRoute")
//    @Produces({"application/json"})
    public TransitPath findOptimalRoute(
             @RequestParam(name = "origin") String originUnLocode,
             @RequestParam(name = "destination") String destinationUnLocode,
             @RequestParam(name = "deadline") String deadline) {

        List<Voyage> voyages = cargoRoutingQueryService.findAll();
        System.out.println("***Voyages are****"+voyages.size());
        TransitPath transitPath = new TransitPath();
        List<TransitEdge> transitEdges = new ArrayList<>();
        for(Voyage voyage:voyages){

            TransitEdge transitEdge = new TransitEdge();
            transitEdge.setVoyageNumber(voyage.getVoyageNumber().getVoyageNumber());
            List<CarrierMovement> carrierMovements = voyage.getSchedule().getCarrierMovements();

            CarrierMovement movement =
                    ((List<CarrierMovement>)voyage.getSchedule().getCarrierMovements()).get(0);
            transitEdge.setFromDate(movement.getArrivalDate());
            transitEdge.setToDate(movement.getDepartureDate());
            transitEdge.setFromUnLocode(movement.getArrivalLocation().getUnLocCode());
            transitEdge.setToUnLocode(movement.getDepartureLocation().getUnLocCode());
            transitEdges.add(transitEdge);

        }

        transitPath.setTransitEdges(transitEdges);
        return transitPath;

    }
}
