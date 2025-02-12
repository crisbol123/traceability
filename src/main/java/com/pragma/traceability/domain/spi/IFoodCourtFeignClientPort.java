package com.pragma.traceability.domain.spi;

import java.util.List;
import java.util.Map;

public interface IFoodCourtFeignClientPort {
List<Long> getOrdersIdByClientId(Long clientId);
List<Long> getAllOrdersId();
    Map<Long, Long> getOrdersIdAndEmployeeId();
}
