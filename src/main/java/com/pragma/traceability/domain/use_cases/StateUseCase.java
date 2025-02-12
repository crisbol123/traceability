package com.pragma.traceability.domain.use_cases;

import com.pragma.traceability.domain.api.IStateServicePort;
import com.pragma.traceability.domain.model.StateOrder;
import com.pragma.traceability.domain.model.Traceability;
import com.pragma.traceability.domain.spi.IFoodCourtFeignClientPort;
import com.pragma.traceability.domain.spi.ISecurityContextPort;
import com.pragma.traceability.domain.spi.StatePersistancePort;

import java.time.Duration;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<Long, Duration> getEficiencyAllOrder() {
        List<Long> listAllOrdersId = foodCourtFeignClientPort.getAllOrdersId();
        return statePersistancePort.getEficiencyAllOrder(listAllOrdersId);
    }

    @Override
    public Map<Long, Double> getEficiencyAllEmployee() {
      Map<Long, Long> orderIdEmployeeId =foodCourtFeignClientPort.getOrdersIdAndEmployeeId();
        return statePersistancePort.getEficiencyAllEmployee(orderIdEmployeeId);
    }
}
