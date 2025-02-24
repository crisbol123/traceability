package com.pragma.traceability.configuration;



import com.pragma.traceability.adapters.driven.feigns.adapter.FoodCourtFeignClientAdapter;
import com.pragma.traceability.adapters.driven.feigns.adapter.UserFeignClientAdapter;
import com.pragma.traceability.adapters.driven.feigns.clients.FoodCourtFeignClient;
import com.pragma.traceability.adapters.driven.feigns.clients.UserFeignClient;
import com.pragma.traceability.adapters.driven.mongodb.adapters.StateAdapter;
import com.pragma.traceability.adapters.driven.mongodb.repository.StateRepository;
import com.pragma.traceability.configuration.securityconfig.SecurityContextPortImpl;
import com.pragma.traceability.domain.api.IStateServicePort;
import com.pragma.traceability.domain.spi.IFoodCourtFeignClientPort;
import com.pragma.traceability.domain.spi.ISecurityContextPort;
import com.pragma.traceability.domain.spi.StatePersistancePort;
import com.pragma.traceability.domain.spi.UserFeignClientPort;
import com.pragma.traceability.domain.use_cases.StateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final StateRepository stateRepository;
    private  final FoodCourtFeignClient foodCourtFeignClient;

    @Bean
    public StatePersistancePort statePersistancePort(){
        return new StateAdapter(stateRepository);
    }

    @Bean
    public IStateServicePort stateServicePort(){
        return new StateUseCase(statePersistancePort(),securityContextPort(),foodCourtFeignClientPort());
    }
    @Bean
  public ISecurityContextPort securityContextPort()
    {
        return new SecurityContextPortImpl();
    }

@Bean
    public IFoodCourtFeignClientPort foodCourtFeignClientPort(){
        return new FoodCourtFeignClientAdapter(foodCourtFeignClient);
    }
    @Bean
    public UserFeignClientPort userFeignClientPort(UserFeignClient userFeignClient){
        return new UserFeignClientAdapter(userFeignClient);
    }


}

