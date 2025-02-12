package com.pragma.traceability.adapters.driven.mongoDB.adapters;

import com.pragma.traceability.adapters.driven.mongoDB.repository.StateRepository;
import com.pragma.traceability.domain.model.StateOrder;
import com.pragma.traceability.domain.model.Traceability;
import com.pragma.traceability.domain.spi.StatePersistancePort;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StateAdapter implements StatePersistancePort {
    private final StateRepository stateRepository;

    public StateAdapter(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public void saveState(StateOrder state) {
        state.setDate(LocalDateTime.now());
        stateRepository.save(state);
    }

    @Override
    public List<Traceability> getTraceabilityByOrderIdList(List<Long> orderIdList) {
        List<StateOrder> stateOrders = stateRepository.findByOrderIdIn(orderIdList);

        return stateOrders.stream()
                .map(stateOrder -> new Traceability(
                        stateOrder.getOrderId(),
                        stateOrder.getState(),
                        stateOrder.getDate()
                ))
                .collect(Collectors.toList());
    }
    }

