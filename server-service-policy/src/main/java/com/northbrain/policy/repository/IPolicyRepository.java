package com.northbrain.policy.repository;

import com.northbrain.policy.model.Policy;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPolicyRepository extends ReactiveCrudRepository<Policy, String> {

    Flux<Policy> findByCategoryAndStatusAndNameLike(String category,
                                                    String status,
                                                    String name);
    Mono<Policy> findByCategoryAndStatusAndName(String category,
                                                String status,
                                                String name);
}
