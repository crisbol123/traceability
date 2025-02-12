package com.pragma.traceability.adapters.driven.feigns.clients;

import com.pragma.traceability.adapters.driven.feigns.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(name = "food-court", url = "${food-court.service.url}", configuration = FeignClientConfig.class)
public interface FoodCourtFeignClient {
    @GetMapping("/order/getOrdersIdByClientId")
    List<Long> getOrdersIdByClientId(@RequestParam Long clientId);
}
