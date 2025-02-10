package com.pragma.traceability.configuration.securityconfig;



import com.pragma.traceability.adapters.driven.feigns.dto.AuthorizationResponse;
import com.pragma.traceability.domain.spi.ISecurityContextPort;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor
public class SecurityContextPortImpl implements ISecurityContextPort {

    @Override
    public Long getUserId() {
        AuthorizationResponse authorizationResponse = (AuthorizationResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return authorizationResponse.getId();
    }
}
