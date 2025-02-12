package com.pragma.traceability.domain.spi;

import com.pragma.traceability.domain.model.StateOrder;
import com.pragma.traceability.domain.model.Traceability;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public interface StatePersistancePort {
    void saveState(StateOrder state);
    List<Traceability> getTraceabilityByOrderIdList(List<Long> orderIdList);
    Map<Long, Duration> getEficiencyAllOrder(List<Long> orderIdList);
    Map<Long, Double> getEficiencyAllEmployee(Map<Long, Long> employeeIdAndOrderId);
}
