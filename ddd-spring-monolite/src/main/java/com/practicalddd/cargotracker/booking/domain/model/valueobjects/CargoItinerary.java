package com.practicalddd.cargotracker.booking.domain.model.valueobjects;


import com.practicalddd.cargotracker.booking.domain.model.entities.Leg;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Embeddable
public class CargoItinerary {

    public static final CargoItinerary EMPTY_ITINERARY = new CargoItinerary();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cargo_id")
    private List<Leg> legs = new ArrayList<>();//Collections.emptyList();

    public CargoItinerary() {
        // Nothing to initialize.
    }

    public CargoItinerary(List<Leg> legs) {
        this.legs = legs;
    }

    public List<Leg> getLegs() {
        return Collections.unmodifiableList(legs);
    }

    public void copyLegs(CargoItinerary source){
        legs.clear();
        legs.addAll(source.getLegs());
    }
}
