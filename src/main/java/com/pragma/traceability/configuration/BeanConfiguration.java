package com.pragma.traceability.configuration;



import com.pragma.traceability.adapters.driven.feigns.adapter.UserFeignClientAdapter;
import com.pragma.traceability.adapters.driven.feigns.clients.UserFeignClient;
import com.pragma.traceability.adapters.driven.mongoDB.adapters.StateAdapter;
import com.pragma.traceability.adapters.driven.mongoDB.repository.StateRepository;
import com.pragma.traceability.domain.api.IStateServicePort;
import com.pragma.traceability.domain.spi.StatePersistancePort;
import com.pragma.traceability.domain.spi.UserFeignClientPort;
import com.pragma.traceability.domain.use_cases.StateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final StateRepository stateRepository;
    private  final UserFeignClient userFeignClient;

    @Bean
    public StatePersistancePort statePersistancePort(){
        return new StateAdapter(stateRepository);
    }

    @Bean
    public IStateServicePort stateServicePort(){
        return new StateUseCase(statePersistancePort());
    }
@Bean
    public UserFeignClientPort userFeignClientPort(){
        return new UserFeignClientAdapter(userFeignClient);
    }


}

