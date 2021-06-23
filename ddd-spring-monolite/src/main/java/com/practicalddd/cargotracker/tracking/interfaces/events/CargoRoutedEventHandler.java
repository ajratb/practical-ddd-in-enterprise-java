package com.practicalddd.cargotracker.tracking.interfaces.events;

import com.practicalddd.cargotracker.shareddomain.events.CargoRoutedEvent;
import com.practicalddd.cargotracker.tracking.application.internal.commandservices.AssignTrackingIdCommandService;
import com.practicalddd.cargotracker.tracking.interfaces.events.transform.TrackingDetailsCommandEventAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class CargoRoutedEventHandler {

    @Autowired
    private AssignTrackingIdCommandService assignTrackingIdCommandService; // Application Service Dependency

    /**
     * Cargo Routed Event Handler Method
     *
     * @param event
     */
    @EventListener
    @Transactional
    public void observeCargoRoutedEvent(CargoRoutedEvent event) {
        System.out.println("****Observing Cargo Routed Event***");
        assignTrackingIdCommandService.assignTrackingNumberToCargo(TrackingDetailsCommandEventAssembler.toCommandFromEvent(event));
    }
}
