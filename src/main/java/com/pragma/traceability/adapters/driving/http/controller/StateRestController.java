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

import static com.pragma.traceability.configuration.Constants.*;

@RestController
@RequestMapping(STATE_BASE_PATH)
@AllArgsConstructor
@Tag(name = STATE_TAG, description = STATE_DESCRIPTION)
public class StateRestController {
    private final IStateServicePort stateServicePort;
    private final IStateRequestMapper stateRequestMapper;

    @PostMapping(STATE_SAVE_PATH)
    @Operation(summary = STATE_SAVE_SUMMARY, description = STATE_SAVE_DESCRIPTION)
    public ResponseEntity<Void> saveOrder(@RequestBody StateDTO request) {
        stateServicePort.saveState(stateRequestMapper.toDomain(request));
        return ResponseEntity.ok().build();
    }

    @GetMapping(STATE_TRACEABILITY_PATH)
    @Operation(summary = STATE_TRACEABILITY_SUMMARY, description = STATE_TRACEABILITY_DESCRIPTION)
    public List<TraceabilityResponse> getTraceability() {
        List<Traceability> traceability = stateServicePort.getTraceability();
        return stateRequestMapper.toResponse(traceability);
    }

    @GetMapping(STATE_EFFICIENCY_ALL_ORDERS_PATH)
    @Operation(summary = STATE_EFFICIENCY_ALL_ORDERS_SUMMARY, description = STATE_EFFICIENCY_ALL_ORDERS_DESCRIPTION)
    public Map<Long, Duration> getEficiencyAllOrder() {
        return stateServicePort.getEficiencyAllOrder();
    }

    @GetMapping(STATE_EFFICIENCY_ALL_EMPLOYEES_PATH)
    @Operation(summary = STATE_EFFICIENCY_ALL_EMPLOYEES_SUMMARY, description = STATE_EFFICIENCY_ALL_EMPLOYEES_DESCRIPTION)
    public Map<Long, Double> getEficiencyAllEmployee() {
        return stateServicePort.getEficiencyAllEmployee();
    }
}
