package com.pragma.traceability.domain.api;

import com.pragma.traceability.domain.model.StateOrder;
import com.pragma.traceability.domain.model.Traceability;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public interface IStateServicePort {
    void saveState(StateOrder state);
    List<Traceability> getTraceability();
    Map<Long, Duration> getEficiencyAllOrder();
    Map<Long, Double> getEficiencyAllEmployee();
}
