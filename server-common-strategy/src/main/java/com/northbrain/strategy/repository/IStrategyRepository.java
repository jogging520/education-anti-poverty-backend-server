package com.northbrain.strategy.repository;

import com.northbrain.strategy.model.Strategy;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IStrategyRepository extends ReactiveCrudRepository<Strategy, String> {

     Flux<Strategy> findByTypeAndAppTypeAndCategoryAndStatus(String type,
                                                             String appTYpe,
                                                             String category,
                                                             String status);
}
