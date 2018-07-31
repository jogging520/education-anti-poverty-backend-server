package com.northbrain.strategy.repository;

import com.northbrain.strategy.model.StrategyHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IStrategyHistoryRepository extends ReactiveCrudRepository<StrategyHistory, String> {
}
