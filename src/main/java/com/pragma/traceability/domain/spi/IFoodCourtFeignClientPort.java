package com.pragma.traceability.domain.spi;

import java.util.List;

public interface IFoodCourtFeignClientPort {
List<Long> getOrdersIdByClientId(Long clientId);
}
