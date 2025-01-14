package com.practicalddd.cargotracker.tracking.interfaces.events;

import com.practicalddd.cargotracker.shareddomain.events.CargoHandledEvent;
import com.practicalddd.cargotracker.shareddomain.events.CargoHandledEventData;
import com.practicalddd.cargotracker.tracking.application.internal.commandservices.AssignTrackingIdCommandService;
import com.practicalddd.cargotracker.tracking.interfaces.events.transform.TrackingActivityCommandEventAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class CargoHandledEventHandler {

        @Autowired
        private AssignTrackingIdCommandService assignTrackingIdCommandService; // Application Service Dependency

        /**
         * Cargo Handled Event handler
         * @param event
         */
        @EventListener
        @Transactional
        public void observeCargoHandledEvent(CargoHandledEvent event) {
            System.out.println("***Cargo Handled Event****"+event.getContent());
                CargoHandledEventData eventData = event.getContent();
                System.out.println(eventData.getBookingId());
                System.out.println(eventData.getHandlingLocation());
                System.out.println(eventData.getHandlingCompletionTime());
                System.out.println(eventData.getHandlingType());
                System.out.println(eventData.getVoyageNumber());
            assignTrackingIdCommandService.addTrackingEvent(
                    TrackingActivityCommandEventAssembler.toCommandFromEvent(event));
        }


}
