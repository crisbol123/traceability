package com.pragma.traceability.domain.spi;

import com.pragma.traceability.domain.model.StateOrder;

public interface StatePersistancePort {
    void saveState(StateOrder state);
}
