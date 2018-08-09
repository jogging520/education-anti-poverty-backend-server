package com.northbrain.privilege.repository;

import com.northbrain.privilege.model.RoleHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IRoleHistoryRepository extends ReactiveCrudRepository<RoleHistory, String > {
}
