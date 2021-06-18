package com.practicalddd.cargotracker.handling.application.internal.commandservices;


import com.practicalddd.cargotracker.handling.domain.model.aggregates.HandlingActivity;
import com.practicalddd.cargotracker.handling.domain.model.commands.HandlingActivityRegistrationCommand;
import com.practicalddd.cargotracker.handling.domain.model.valueobjects.CargoBookingId;
import com.practicalddd.cargotracker.handling.domain.model.valueobjects.Location;
import com.practicalddd.cargotracker.handling.domain.model.valueobjects.Type;
import com.practicalddd.cargotracker.handling.domain.model.valueobjects.VoyageNumber;
import com.practicalddd.cargotracker.handling.infrastructure.repositories.jpa.HandlingActivityRepository;
import com.practicalddd.cargotracker.shareddomain.events.CargoHandledEvent;
import com.practicalddd.cargotracker.shareddomain.events.CargoHandledEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HandlingActivityRegistrationCommandService {

        @Autowired
        private HandlingActivityRepository handlingActivityRepository;

        @Autowired
        private ApplicationEventPublisher cargoHandledEventControl; // Event that needs to be raised when the Cargo is Handled

        /**
         * Service Command method to register a new Handling Activity
         * @return BookingId of the CargoBookingId
         */
        @Transactional
        public void registerHandlingActivityService(HandlingActivityRegistrationCommand handlingActivityRegistrationCommand){
                System.out.println("Handling Voyage Number is"+handlingActivityRegistrationCommand.getVoyageNumber());
                if(!handlingActivityRegistrationCommand.getVoyageNumber().equals("")) {
                        HandlingActivity handlingActivity = new HandlingActivity(
                                new CargoBookingId(handlingActivityRegistrationCommand.getBookingId()),
                                handlingActivityRegistrationCommand.getCompletionTime(),
                                Type.valueOf(handlingActivityRegistrationCommand.getHandlingType()),
                                new Location(handlingActivityRegistrationCommand.getUnLocode()),
                                new VoyageNumber(handlingActivityRegistrationCommand.getVoyageNumber()));
                        handlingActivityRepository.store(handlingActivity);


                }else{
                        HandlingActivity handlingActivity = new HandlingActivity(
                                new CargoBookingId(handlingActivityRegistrationCommand.getBookingId()),
                                handlingActivityRegistrationCommand.getCompletionTime(),
                                Type.valueOf(handlingActivityRegistrationCommand.getHandlingType()),
                                new Location(handlingActivityRegistrationCommand.getUnLocode()));
                        handlingActivityRepository.store(handlingActivity);
                }



                CargoHandledEventData eventData = new CargoHandledEventData();
                eventData.setBookingId(handlingActivityRegistrationCommand.getBookingId());
                eventData.setHandlingCompletionTime(handlingActivityRegistrationCommand.getCompletionTime());
                eventData.setHandlingLocation(handlingActivityRegistrationCommand.getUnLocode());
                eventData.setHandlingType(handlingActivityRegistrationCommand.getHandlingType());
                eventData.setVoyageNumber(handlingActivityRegistrationCommand.getVoyageNumber());

                System.out.println("***Event Data ***"+eventData);
                CargoHandledEvent cargoHandledEvent = new CargoHandledEvent(eventData);

                System.out.println("*****cargohandlede"+handlingActivityRegistrationCommand.getBookingId()+ " " + handlingActivityRegistrationCommand.getHandlingType()
                + " " + handlingActivityRegistrationCommand.getCompletionTime() + " " +handlingActivityRegistrationCommand.getUnLocode() );

                cargoHandledEventControl.publishEvent(cargoHandledEvent);

        }
}
