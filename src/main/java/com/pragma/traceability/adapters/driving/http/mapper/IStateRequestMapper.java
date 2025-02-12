package com.pragma.traceability.adapters.driving.http.mapper;

import com.pragma.traceability.adapters.driving.http.dto.request.StateDTO;
import com.pragma.traceability.adapters.driving.http.dto.response.TraceabilityResponse;
import com.pragma.traceability.domain.model.StateOrder;
import com.pragma.traceability.domain.model.Traceability;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IStateRequestMapper {
    StateOrder toDomain(StateDTO request);

    TraceabilityResponse toResponse(Traceability traceability);

    List<TraceabilityResponse> toResponse(List<Traceability> traceabilities);
}
