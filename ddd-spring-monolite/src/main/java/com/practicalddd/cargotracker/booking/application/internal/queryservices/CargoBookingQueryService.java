package com.practicalddd.cargotracker.booking.application.internal.queryservices;


import com.practicalddd.cargotracker.booking.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.booking.domain.model.aggregates.Cargo;
import com.practicalddd.cargotracker.booking.infrastructure.repositories.jpa.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Application Service which caters to all queries related to the Booking Bounded Context
 */
@Service
public class CargoBookingQueryService {


    @Autowired
    private CargoRepository cargoRepository; // Inject Dependencies

    /**
     * Find all Cargos
     * @return List<Cargo>
     */
    //@Autowired
    public List<Cargo> findAll(){
        return cargoRepository.findAll();
    }

    /**
     * List All Booking Identifiers
     * @return List<BookingId>
     */
   public List<BookingId> getAllBookingIds(){
       return cargoRepository.findAllBookingIds();
   }

    /**
     * Find a specific Cargo based on its Booking Id
     * @param bookingId
     * @return Cargo
     */
    public Cargo find(String bookingId){
        return cargoRepository.find(new BookingId(bookingId));
    }
}
