package com.northbrain.privilege.repository;

import com.northbrain.privilege.model.Permission;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IPermissionRepository extends ReactiveCrudRepository<Permission, String > {
    public Flux<Permission> findByIdInAndCategory(String[] ids, String category);
}
