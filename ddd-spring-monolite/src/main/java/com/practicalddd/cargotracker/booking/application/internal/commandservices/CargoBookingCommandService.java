package com.practicalddd.cargotracker.booking.application.internal.commandservices;

import com.practicalddd.cargotracker.booking.application.internal.outboundservices.acl.ExternalCargoRoutingService;
import com.practicalddd.cargotracker.booking.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.booking.domain.model.aggregates.Cargo;
import com.practicalddd.cargotracker.booking.domain.model.commands.BookCargoCommand;
import com.practicalddd.cargotracker.booking.domain.model.commands.RouteCargoCommand;
import com.practicalddd.cargotracker.booking.domain.model.valueobjects.CargoItinerary;
import com.practicalddd.cargotracker.booking.infrastructure.repositories.jpa.CargoRepository;
import com.practicalddd.cargotracker.shareddomain.events.CargoBookedEvent;
import com.practicalddd.cargotracker.shareddomain.events.CargoRoutedEvent;
import com.practicalddd.cargotracker.shareddomain.events.CargoRoutedEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Application Service class for the Cargo Booking Command
 */
@Service
public class CargoBookingCommandService {

    @Autowired
    private CargoRepository cargoRepository; // Outbound Service to connect to the Booking Bounded Context MySQL Database Instance

//    @Autowired
//    private Event<CargoBookedEvent> cargoBookedEventControl;
//
//    @Autowired
//    private Event<CargoRoutedEvent> cargoRoutedEventControl; // Event that needs to be raised when the Cargo is Booked

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ExternalCargoRoutingService externalCargoRoutingService;

    /**
     * Service Command method to book a new Cargo
     * @return BookingId of the Cargo
     */
    @Transactional // Inititate the Transaction
    public BookingId bookCargo(BookCargoCommand bookCargoCommand){
        String bookingId = cargoRepository.nextBookingId();
        bookCargoCommand.setBookingId(bookingId);
        Cargo cargo = new Cargo(bookCargoCommand);
        cargoRepository.store(cargo); //Store the Cargo

        CargoBookedEvent cargoBookedEvent = new CargoBookedEvent(bookingId);
//        cargoBookedEvent.setId(bookingId); //Set the content of the event
        publisher.publishEvent(cargoBookedEvent);
//        cargoBookedEventControl.fire(cargoBookedEvent);
        return new BookingId(bookingId);
    }

    /**
     * Service Command method to assign a route to a Cargo
     * @param routeCargoCommand
     */
    @Transactional
    public void assignRouteToCargo(RouteCargoCommand routeCargoCommand){
        Cargo cargo = cargoRepository.find(new BookingId(routeCargoCommand.getCargoBookingId()));

        CargoItinerary cargoItinerary = externalCargoRoutingService
                .fetchRouteForSpecification(cargo.getRouteSpecification());

        cargo.assignToRoute(cargoItinerary);
        cargoRepository.store(cargo);


        CargoRoutedEventData eventData = new CargoRoutedEventData();
        eventData.setBookingId(routeCargoCommand.getCargoBookingId());
        CargoRoutedEvent cargoRoutedEvent = new CargoRoutedEvent(eventData);
//        cargoRoutedEvent.setContent(eventData);
        publisher.publishEvent(cargoRoutedEvent);
    }


}
