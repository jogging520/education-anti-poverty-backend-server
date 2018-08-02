package com.northbrain.privilige.repository;

import com.northbrain.privilige.model.Permission;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IPermissionRepository extends ReactiveCrudRepository<Permission, String > {
    public Flux<Permission> findByIdInAndCategory(String[] ids, String category);
}
