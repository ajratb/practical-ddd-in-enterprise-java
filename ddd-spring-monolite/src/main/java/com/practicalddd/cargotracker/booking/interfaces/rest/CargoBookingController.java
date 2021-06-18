package com.practicalddd.cargotracker.booking.interfaces.rest;

import com.practicalddd.cargotracker.booking.application.internal.commandservices.CargoBookingCommandService;
import com.practicalddd.cargotracker.booking.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.booking.interfaces.rest.dto.BookCargoResource;
import com.practicalddd.cargotracker.booking.interfaces.rest.transform.BookCargoCommandDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CargoBookingController {


    @Autowired
    private CargoBookingCommandService cargoBookingCommandService; // Application Service Dependency

    /**
     * POST method to book a cargo
     *
     * @param bookCargoResource
     */

    @PostMapping("/cargobooking")
//    @Produces(MediaType.APPLICATION_JSON)
    public BookingId bookCargo(@RequestBody BookCargoResource bookCargoResource) {
        System.out.println("****Book Cargo" + cargoBookingCommandService);
        BookingId bookingId = cargoBookingCommandService.bookCargo(
                BookCargoCommandDTOAssembler.toCommandFromDTO(bookCargoResource));
        return bookingId;
    }


}
