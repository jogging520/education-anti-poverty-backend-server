package com.northbrain.user.repository;

import com.northbrain.user.model.UserHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IUserHistoryRepository extends ReactiveCrudRepository<UserHistory, String> {
}
