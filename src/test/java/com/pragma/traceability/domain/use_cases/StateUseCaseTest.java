package com.pragma.traceability.domain.use_cases;


import com.pragma.traceability.domain.model.StateOrder;
import com.pragma.traceability.domain.model.Traceability;
import com.pragma.traceability.domain.spi.IFoodCourtFeignClientPort;
import com.pragma.traceability.domain.spi.ISecurityContextPort;
import com.pragma.traceability.domain.spi.StatePersistancePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StateUseCaseTest {

    @Mock
    private StatePersistancePort statePersistancePort;

    @Mock
    private ISecurityContextPort securityContextPort;

    @Mock
    private IFoodCourtFeignClientPort foodCourtFeignClientPort;

    @InjectMocks
    private StateUseCase stateUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveState() {
        StateOrder stateOrder = new StateOrder(1L, "state", null);
        stateUseCase.saveState(stateOrder);
        verify(statePersistancePort, times(1)).saveState(stateOrder);
    }

    @Test
    void getTraceability() {
        Long clientId = 1L;
        List<Long> ordersId = List.of(1L, 2L);
        List<Traceability> traceabilities = List.of(new Traceability(4L, "state", null), new Traceability(5L, "state", null));

        when(securityContextPort.getUserId()).thenReturn(clientId);
        when(foodCourtFeignClientPort.getOrdersIdByClientId(clientId)).thenReturn(ordersId);
        when(statePersistancePort.getTraceabilityByOrderIdList(ordersId)).thenReturn(traceabilities);

        List<Traceability> result = stateUseCase.getTraceability();

        assertEquals(traceabilities, result);
        verify(securityContextPort, times(1)).getUserId();
        verify(foodCourtFeignClientPort, times(1)).getOrdersIdByClientId(clientId);
        verify(statePersistancePort, times(1)).getTraceabilityByOrderIdList(ordersId);
    }

    @Test
    void getEficiencyAllOrder() {
        List<Long> listAllOrdersId = List.of(1L, 2L);
        Map<Long, Duration> efficiencyMap = Map.of(1L, Duration.ofHours(1), 2L, Duration.ofHours(2));

        when(foodCourtFeignClientPort.getAllOrdersId()).thenReturn(listAllOrdersId);
        when(statePersistancePort.getEficiencyAllOrder(listAllOrdersId)).thenReturn(efficiencyMap);

        Map<Long, Duration> result = stateUseCase.getEficiencyAllOrder();

        assertEquals(efficiencyMap, result);
        verify(foodCourtFeignClientPort, times(1)).getAllOrdersId();
        verify(statePersistancePort, times(1)).getEficiencyAllOrder(listAllOrdersId);
    }

    @Test
    void getEficiencyAllEmployee() {
        Map<Long, Long> orderIdEmployeeId = Map.of(1L, 1L, 2L, 2L);
        Map<Long, Double> efficiencyMap = Map.of(1L, 1.0, 2L, 2.0);

        when(foodCourtFeignClientPort.getOrdersIdAndEmployeeId()).thenReturn(orderIdEmployeeId);
        when(statePersistancePort.getEficiencyAllEmployee(orderIdEmployeeId)).thenReturn(efficiencyMap);

        Map<Long, Double> result = stateUseCase.getEficiencyAllEmployee();

        assertEquals(efficiencyMap, result);
        verify(foodCourtFeignClientPort, times(1)).getOrdersIdAndEmployeeId();
        verify(statePersistancePort, times(1)).getEficiencyAllEmployee(orderIdEmployeeId);
    }
}