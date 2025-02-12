package com.pragma.traceability.domain.api;

import com.pragma.traceability.domain.model.StateOrder;
import com.pragma.traceability.domain.model.Traceability;

import java.util.List;

public interface IStateServicePort {
    void saveState(StateOrder state);
    List<Traceability> getTraceability();
}
