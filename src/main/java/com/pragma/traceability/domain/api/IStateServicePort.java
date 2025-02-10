package com.pragma.traceability.domain.api;

import com.pragma.traceability.domain.model.StateOrder;

public interface IStateServicePort {
    void saveState(StateOrder state);
}
