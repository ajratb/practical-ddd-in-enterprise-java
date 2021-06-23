package com.practicalddd.cargotracker.shareddomain.events;


import org.springframework.context.ApplicationEvent;

public class CargoHandledEvent  extends ApplicationEvent {

    private CargoHandledEventData cargoHandledEventData;
    public CargoHandledEvent(CargoHandledEventData cargoHandledEventData){
        super(cargoHandledEventData);
        this.cargoHandledEventData = cargoHandledEventData;
    }
    public void setContent(CargoHandledEventData cargoHandledEventData) { this.cargoHandledEventData = cargoHandledEventData; }
    public CargoHandledEventData getContent() {
        return cargoHandledEventData;
    }
}
