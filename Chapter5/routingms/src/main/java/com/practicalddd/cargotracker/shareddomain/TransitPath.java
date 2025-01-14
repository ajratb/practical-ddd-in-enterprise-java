package com.practicalddd.cargotracker.shareddomain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TransitPath implements Serializable {

    private List<TransitEdge> transitEdges;

    public TransitPath() {
        this.transitEdges = new ArrayList<>();
    }

    @SuppressWarnings("unused")
    public TransitPath(List<TransitEdge> transitEdges) {
        this.transitEdges = transitEdges;
    }

    @SuppressWarnings("unused")
    public List<TransitEdge> getTransitEdges() {
        return transitEdges;
    }

    public void setTransitEdges(List<TransitEdge> transitEdges) {
        this.transitEdges = transitEdges;
    }

    @Override
    public String toString() {
        return "TransitPath{" + "transitEdges=" + transitEdges + '}';
    }
}
