package com.pragma.traceability.adapters.driving.http.controller;


import com.pragma.traceability.adapters.driving.http.dto.request.StateDTO;
import com.pragma.traceability.adapters.driving.http.dto.response.TraceabilityResponse;
import com.pragma.traceability.adapters.driving.http.mapper.IStateRequestMapper;
import com.pragma.traceability.domain.api.IStateServicePort;
import com.pragma.traceability.domain.model.Traceability;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import java.time.Duration;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/state")
@AllArgsConstructor
@Tag(name = "State Management", description = "API for managing order states and traceability")
public class StateRestController {
    private final IStateServicePort stateServicePort;
    private final IStateRequestMapper stateRequestMapper;

    @PostMapping("/save")
    @Operation(summary = "Save order state", description = "Saves the state of an order")
    public ResponseEntity<Void> saveOrder(@RequestBody StateDTO request) {
        stateServicePort.saveState(stateRequestMapper.toDomain(request));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/consult-traceability")
    @Operation(summary = "Get traceability", description = "Retrieves traceability records")
    public List<TraceabilityResponse> getTraceability() {
        List<Traceability> traceability = stateServicePort.getTraceability();
        return stateRequestMapper.toResponse(traceability);
    }

    @GetMapping("/consult-eficiency-all-order")
    @Operation(summary = "Get efficiency of all orders", description = "Retrieves efficiency metrics for all orders")
    public Map<Long, Duration> getEficiencyAllOrder() {
        return stateServicePort.getEficiencyAllOrder();
    }

    @GetMapping("/getEfficiencyAllEmployee")
    @Operation(summary = "Get efficiency of all employees", description = "Retrieves efficiency metrics for all employees")
    public Map<Long, Double> getEficiencyAllEmployee() {
        return stateServicePort.getEficiencyAllEmployee();
    }
}