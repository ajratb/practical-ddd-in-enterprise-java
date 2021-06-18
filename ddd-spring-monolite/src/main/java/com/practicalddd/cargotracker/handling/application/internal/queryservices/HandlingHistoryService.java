package com.practicalddd.cargotracker.handling.application.internal.queryservices;

import com.practicalddd.cargotracker.handling.domain.model.valueobjects.HandlingActivityHistory;
import com.practicalddd.cargotracker.handling.infrastructure.repositories.jpa.HandlingActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Application Service which caters to all queries related to the Handling Activity Aggregate
 */
@Service
public class HandlingHistoryService {

    @Autowired
    private HandlingActivityRepository handlingActivityRepository;

    @Transactional
    public HandlingActivityHistory getHandlingActivityHistory(String bookingId){
        return handlingActivityRepository.lookupHandlingHistoryOfCargo(bookingId);
    }
}
