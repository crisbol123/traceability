package com.pragma.traceability.adapters.driven.mongodb.adapters;

import com.pragma.traceability.adapters.driven.mongodb.repository.StateRepository;
import com.pragma.traceability.domain.model.StateOrder;
import com.pragma.traceability.domain.model.Traceability;
import com.pragma.traceability.domain.spi.StatePersistancePort;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StateAdapter implements StatePersistancePort {
    private final StateRepository stateRepository;

    public StateAdapter(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public void saveState(StateOrder state) {
        state.setDate(LocalDateTime.now());
        stateRepository.save(state);
    }

    @Override
    public List<Traceability> getTraceabilityByOrderIdList(List<Long> orderIdList) {
        List<StateOrder> stateOrders = stateRepository.findByOrderIdIn(orderIdList);

        return stateOrders.stream()
                .map(stateOrder -> new Traceability(
                        stateOrder.getOrderId(),
                        stateOrder.getState(),
                        stateOrder.getDate()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Long, Duration> getEficiencyAllOrder(List<Long> orderIdList) {
        List<StateOrder> stateOrders = stateRepository.findByOrderIdIn(orderIdList);

        Map<Long, List<StateOrder>> ordersGrouped = stateOrders.stream()
                .collect(Collectors.groupingBy(StateOrder::getOrderId));

        Map<Long, Duration> completionTimes = new HashMap<>();

        for (Map.Entry<Long, List<StateOrder>> entry : ordersGrouped.entrySet()) {
            List<StateOrder> states = entry.getValue();

            LocalDateTime createdTime = states.stream()
                    .filter(s -> "PENDING".equals(s.getState()))
                    .map(StateOrder::getDate)
                    .findFirst().orElse(null);

            LocalDateTime deliveredTime = states.stream()
                    .filter(s -> "DELIVERED".equals(s.getState()))
                    .map(StateOrder::getDate)
                    .findFirst().orElse(null);

            if (createdTime != null && deliveredTime != null) {
                completionTimes.put(entry.getKey(), Duration.between(createdTime, deliveredTime));
            }
        }

        return completionTimes;
    }

    @Override
    public Map<Long, Double> getEficiencyAllEmployee(Map<Long, Long> employeeIdAndOrderId) {
        List<StateOrder> allStateOrders = stateRepository.findAll();

        Map<Long, List<StateOrder>> ordersGrouped = allStateOrders.stream()
                .collect(Collectors.groupingBy(StateOrder::getOrderId));

        Map<Long, List<Duration>> employeeTimes = new HashMap<>();

        for (Map.Entry<Long, List<StateOrder>> entry : ordersGrouped.entrySet()) {
            List<StateOrder> states = entry.getValue();
            Long orderId = entry.getKey();
            Long employee = employeeIdAndOrderId.get(orderId);

            if (employee == null) continue;

            LocalDateTime createdTime = states.stream()
                    .filter(s -> "PENDING".equals(s.getState()))
                    .map(StateOrder::getDate)
                    .findFirst().orElse(null);

            LocalDateTime deliveredTime = states.stream()
                    .filter(s -> "DELIVERED".equals(s.getState()))
                    .map(StateOrder::getDate)
                    .findFirst().orElse(null);

            if (createdTime != null && deliveredTime != null) {
                Duration duration = Duration.between(createdTime, deliveredTime);
                employeeTimes.computeIfAbsent(employee, k -> new ArrayList<>()).add(duration);
            }
        }

        Map<Long, Double> employeeAverageTimes = new HashMap<>();
        for (Map.Entry<Long, List<Duration>> entry : employeeTimes.entrySet()) {
            List<Duration> durations = entry.getValue();
            double averageMinutes = durations.stream()
                    .mapToLong(Duration::toMinutes)
                    .average()
                    .orElse(0);
            employeeAverageTimes.put(entry.getKey(), averageMinutes);
        }

        return employeeAverageTimes.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }


}

