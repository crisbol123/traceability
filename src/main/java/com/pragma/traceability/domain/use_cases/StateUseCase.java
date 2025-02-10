package com.pragma.traceability.domain.use_cases;

import com.pragma.traceability.domain.api.IStateServicePort;
import com.pragma.traceability.domain.model.StateOrder;
import com.pragma.traceability.domain.spi.StatePersistancePort;

public class StateUseCase implements IStateServicePort {
    private final StatePersistancePort statePersistancePort;

    public StateUseCase(StatePersistancePort statePersistancePort) {
        this.statePersistancePort = statePersistancePort;
    }


    @Override
    public void saveState(StateOrder stateOrder) {
        statePersistancePort.saveState(stateOrder);
    }
}
