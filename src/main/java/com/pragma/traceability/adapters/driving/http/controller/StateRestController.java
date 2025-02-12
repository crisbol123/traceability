package com.pragma.traceability.adapters.driving.http.controller;


import com.pragma.traceability.adapters.driving.http.dto.request.StateDTO;
import com.pragma.traceability.adapters.driving.http.dto.response.TraceabilityResponse;
import com.pragma.traceability.adapters.driving.http.mapper.IStateRequestMapper;
import com.pragma.traceability.domain.api.IStateServicePort;
import com.pragma.traceability.domain.model.Traceability;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/state")
@AllArgsConstructor
public class StateRestController {
    private final IStateServicePort stateServicePort;
    private final IStateRequestMapper stateRequestMapper;
    @PostMapping("/save")
    public ResponseEntity<Void> saveOrder(@RequestBody StateDTO request) {

        stateServicePort.saveState(stateRequestMapper.toDomain(request));
        return ResponseEntity.ok().build();

    }
    @GetMapping("/consult-traceability")
    public List<TraceabilityResponse> getTraceability() {
        List<Traceability> traceability = stateServicePort.getTraceability();
        return stateRequestMapper.toResponse(traceability);
    }
}
