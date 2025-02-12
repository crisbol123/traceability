package com.pragma.traceability.adapters.driven.feigns.adapter;

import com.pragma.traceability.adapters.driven.feigns.clients.FoodCourtFeignClient;
import com.pragma.traceability.domain.spi.IFoodCourtFeignClientPort;

import java.util.List;

public class FoodCourtFeignClientAdapter implements IFoodCourtFeignClientPort {
    private final FoodCourtFeignClient foodCourtFeignClient;

    public FoodCourtFeignClientAdapter(FoodCourtFeignClient foodCourtFeignClient) {
        this.foodCourtFeignClient = foodCourtFeignClient;
    }

    @Override
    public List<Long> getOrdersIdByClientId(Long clientId) {
        return foodCourtFeignClient.getOrdersIdByClientId(clientId);
    }
}
