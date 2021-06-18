package com.practicalddd.cargotracker.shareddomain.events;


import org.springframework.context.ApplicationEvent;

/**
 * Event Class for the Cargo Booked Event. Wraps up the Cargo Booking identifier
 * for the event
 */

public class CargoBookedEvent extends ApplicationEvent {
    private String id;

    public CargoBookedEvent(String id) {
        super(id);
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
}
