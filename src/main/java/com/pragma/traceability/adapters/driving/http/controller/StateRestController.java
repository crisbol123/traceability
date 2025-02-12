package com.pragma.traceability.adapters.driving.http.controller;


import com.pragma.traceability.adapters.driving.http.dto.request.StateDTO;
import com.pragma.traceability.adapters.driving.http.mapper.IStateRequestMapper;
import com.pragma.traceability.domain.api.IStateServicePort;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public
    }
}
