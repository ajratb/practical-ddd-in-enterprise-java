package com.practicalddd.cargotracker.routingms.interfaces.rest;

import com.practicalddd.cargotracker.shareddomain.TransitPath;
import com.practicalddd.cargotracker.routingms.application.internal.CargoRoutingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller    // This means that this class is a Controller
@RequestMapping("/cargorouting")
public class CargoRoutingController {


    private final CargoRoutingService cargoRoutingService; // Application Service Dependency

    public CargoRoutingController(CargoRoutingService cargoRoutingService){
        this.cargoRoutingService = cargoRoutingService;
    }

    @SuppressWarnings("MVCPathVariableInspection")
    @GetMapping(path = "/optimalRoute")
    @ResponseBody
    public TransitPath findOptimalRoute(
             @PathVariable("origin") String originUnLocode,
             @PathVariable("destination") String destinationUnLocode,
             @PathVariable("deadline") String deadline) {

        return cargoRoutingService.findOptimalRoute(originUnLocode,destinationUnLocode,deadline);
    }
}
