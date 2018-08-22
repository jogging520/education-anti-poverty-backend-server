package com.northbrain.user.repository;

import com.northbrain.user.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserRepository extends ReactiveCrudRepository<User, String> {

    Mono<User> findByCategoryAndStatusAndIdOrName(String category,
                                                  String status,
                                                  String id,
                                                  String name);

    Mono<User> findByCategoryAndStatusAndName(String category,
                                              String status,
                                              String name);

    Mono<User> findByCategoryAndStatusAndMobilesContaining(String category,
                                                           String status,
                                                           String mobile);

    Flux<User> findByCategoryAndStatus(String category,
                                       String status);

}
