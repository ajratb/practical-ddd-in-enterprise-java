package com.practicalddd.cargotracker.tracking.interfaces.events;

import com.practicalddd.cargotracker.shareddomain.events.CargoBookedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CargoBookedEventHandler {

    //public void testEventObserving(@Observes CargoBookedEvent event) {
    @EventListener
    public void testEventObserving(CargoBookedEvent event) {
        // Processing of an event
        System.out.println("***Just a Test***"+event.getId());
    }
}
