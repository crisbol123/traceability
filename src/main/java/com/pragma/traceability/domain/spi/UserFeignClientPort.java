package com.pragma.traceability.domain.spi;


import com.pragma.traceability.adapters.driven.feigns.dto.AuthorizationResponse;

public interface UserFeignClientPort {

    AuthorizationResponse validateToken(String token);
}
