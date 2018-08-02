package com.northbrain.privilige.repository;

import com.northbrain.privilige.model.RoleHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IRoleHistoryRepository extends ReactiveCrudRepository<RoleHistory, String > {
}
