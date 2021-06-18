package com.practicalddd.cargotracker.handling.interfaces.rest;


import com.practicalddd.cargotracker.handling.application.internal.commandservices.HandlingActivityRegistrationCommandService;
import com.practicalddd.cargotracker.handling.interfaces.rest.dto.HandlingActivityRegistrationResource;
import com.practicalddd.cargotracker.handling.interfaces.rest.transform.HandlingActivityRegistrationCommandDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the REST API
 */
//@Path("/cargohandling")
@RestController
public  class CargoHandlingController {

    @Autowired
    private HandlingActivityRegistrationCommandService handlingActivityRegistrationCommandService; // Application Service Dependency


    /**
     * POST method to register Handling Activities
     * @param handlingActivityRegistrationResource
     */
    @PostMapping("/cargohandling")
//    @Produces(MediaType.APPLICATION_JSON)
    public String registerHandlingActivity(@RequestBody HandlingActivityRegistrationResource handlingActivityRegistrationResource){
        System.out.println("***"+handlingActivityRegistrationResource.getBookingId());
        System.out.println("***"+handlingActivityRegistrationResource.getHandlingType());

        handlingActivityRegistrationCommandService.registerHandlingActivityService(
                HandlingActivityRegistrationCommandDTOAssembler.toCommandFromDTO(handlingActivityRegistrationResource));

        return "Handling Activity Registered";
    }
}
