package com.pragma.traceability.adapters.driven.feigns.clients;

import com.pragma.traceability.adapters.driven.feigns.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "food-court", url = "${food-court.service.url}", configuration = FeignClientConfig.class)
public interface FoodCourtFeignClient {
    @GetMapping("/order/getOrdersIdByClientId")
    List<Long> getOrdersIdByClientId(@RequestParam Long clientId);

    @GetMapping("/order/getAllOrdersId")
    List<Long>  getAllOrdersId();

    @GetMapping("/order/getOrdersIdAndEmployeeId")
    Map<Long, Long> getOrdersIdAndEmployeeId();
}
