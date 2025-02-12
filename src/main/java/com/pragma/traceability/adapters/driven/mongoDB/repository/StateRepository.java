package com.pragma.traceability.adapters.driven.mongoDB.repository;

import com.pragma.traceability.domain.model.StateOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StateRepository extends MongoRepository<StateOrder, String> {

    List<StateOrder> findByOrderIdIn(List<Long> orderIds);


}
