package com.pragma.traceability.adapters.driven.feigns.adapter;


import com.pragma.traceability.adapters.driven.feigns.clients.UserFeignClient;
import com.pragma.traceability.adapters.driven.feigns.dto.AuthorizationRequest;
import com.pragma.traceability.adapters.driven.feigns.dto.AuthorizationResponse;
import com.pragma.traceability.adapters.driven.feigns.dto.OwnerRequest;
import com.pragma.traceability.adapters.driven.feigns.dto.OwnerResponse;
import com.pragma.traceability.domain.spi.UserFeignClientPort;

public class UserFeignClientAdapter implements UserFeignClientPort {
    private final UserFeignClient userFeignClient;

    public UserFeignClientAdapter(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }


    @Override
    public AuthorizationResponse validateToken(String token) {
        return userFeignClient.validateToken(new AuthorizationRequest(token));
    }


}
