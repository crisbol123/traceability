package com.pragma.traceability.adapters.driving.http.mapper;

import com.pragma.traceability.adapters.driving.http.dto.request.StateDTO;
import com.pragma.traceability.domain.model.StateOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IStateRequestMapper {
    StateOrder toDomain(StateDTO request);
}
