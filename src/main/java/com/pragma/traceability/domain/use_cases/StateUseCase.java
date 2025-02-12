package com.pragma.traceability.domain.use_cases;

import com.pragma.traceability.domain.api.IStateServicePort;
import com.pragma.traceability.domain.model.StateOrder;
import com.pragma.traceability.domain.model.Traceability;
import com.pragma.traceability.domain.spi.IFoodCourtFeignClientPort;
import com.pragma.traceability.domain.spi.ISecurityContextPort;
import com.pragma.traceability.domain.spi.StatePersistancePort;

import java.util.List;

public class StateUseCase implements IStateServicePort {
    private final StatePersistancePort statePersistancePort;
private final ISecurityContextPort securityContextPort;
private final IFoodCourtFeignClientPort foodCourtFeignClientPort;

    public StateUseCase(StatePersistancePort statePersistancePort, ISecurityContextPort securityContextPort, IFoodCourtFeignClientPort foodCourtFeignClientPort) {
        this.statePersistancePort = statePersistancePort;
        this.securityContextPort = securityContextPort;
        this.foodCourtFeignClientPort = foodCourtFeignClientPort;
    }


    @Override
    public void saveState(StateOrder stateOrder) {
        statePersistancePort.saveState(stateOrder);
    }

    @Override
    public List<Traceability> getTraceability() {
        Long clientId = securityContextPort.getUserId();
        List<Long> ordersId = foodCourtFeignClientPort.getOrdersIdByClientId(clientId);
        return statePersistancePort.getTraceabilityByOrderIdList(ordersId);
    }
}
