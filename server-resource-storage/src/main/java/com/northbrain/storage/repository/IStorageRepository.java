package com.northbrain.storage.repository;

import com.northbrain.storage.model.Storage;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IStorageRepository extends ReactiveCrudRepository<Storage, String> {
    Mono<Void> ByTypeAndNameAndCategory(String type, String name, String category);
}
