package com.northbrain.privilige.repository;

import com.northbrain.privilige.model.Role;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IRoleRepository extends ReactiveCrudRepository<Role, String> {
    public Flux<Role> findByIdInAndAppTypesContainingAndCategory(String[] ids, String appType, String category);
}
