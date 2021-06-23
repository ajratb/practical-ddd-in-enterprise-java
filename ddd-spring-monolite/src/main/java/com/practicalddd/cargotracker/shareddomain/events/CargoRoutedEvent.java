package com.practicalddd.cargotracker.shareddomain.events;


import org.springframework.context.ApplicationEvent;

/**
 * Event Class for the Cargo Routed Event. Wraps up the Cargo
 */

public class CargoRoutedEvent extends ApplicationEvent {
    private CargoRoutedEventData cargoRoutedEventData;
    public CargoRoutedEvent(CargoRoutedEventData cargoRoutedEventData){
        super(cargoRoutedEventData);
        this.cargoRoutedEventData = cargoRoutedEventData;
    }
//    public void setContent(CargoRoutedEventData cargoRoutedEventData) { this.cargoRoutedEventData = cargoRoutedEventData; }
    public CargoRoutedEventData getContent() {
        return cargoRoutedEventData;
    }
}