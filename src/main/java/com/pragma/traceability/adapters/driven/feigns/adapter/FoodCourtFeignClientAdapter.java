package com.pragma.traceability.adapters.driven.feigns.adapter;

import com.pragma.traceability.adapters.driven.feigns.clients.FoodCourtFeignClient;
import com.pragma.traceability.domain.spi.IFoodCourtFeignClientPort;

import java.util.List;
import java.util.Map;

public class FoodCourtFeignClientAdapter implements IFoodCourtFeignClientPort {
    private final FoodCourtFeignClient foodCourtFeignClient;

    public FoodCourtFeignClientAdapter(FoodCourtFeignClient foodCourtFeignClient) {
        this.foodCourtFeignClient = foodCourtFeignClient;
    }

    @Override
    public List<Long> getOrdersIdByClientId(Long clientId) {
        return foodCourtFeignClient.getOrdersIdByClientId(clientId);
    }

    @Override
    public List<Long> getAllOrdersId() {
        return foodCourtFeignClient.getAllOrdersId();
    }

    @Override
    public Map<Long, Long> getOrdersIdAndEmployeeId() {
        return foodCourtFeignClient.getOrdersIdAndEmployeeId();
    }
}
