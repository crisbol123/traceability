package com.pragma.traceability.domain.spi;

import com.pragma.traceability.domain.model.StateOrder;
import com.pragma.traceability.domain.model.Traceability;

import java.util.List;

public interface StatePersistancePort {
    void saveState(StateOrder state);
    List<Traceability> getTraceabilityByOrderIdList(List<Long> orderIdList);
}
