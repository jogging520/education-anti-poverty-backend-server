package com.northbrain.policy.repository;

import com.northbrain.policy.model.Policy;
import com.northbrain.policy.model.PolicyHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IPolicyHistoryRepository extends ReactiveCrudRepository<PolicyHistory, String> {

}
