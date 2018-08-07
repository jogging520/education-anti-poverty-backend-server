package com.northbrain.storage.repository;

import com.northbrain.storage.model.StorageHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IStorageHistoryRepository extends ReactiveCrudRepository<StorageHistory, String> {
}
