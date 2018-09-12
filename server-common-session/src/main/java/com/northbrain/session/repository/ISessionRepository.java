package com.northbrain.session.repository;

import com.northbrain.session.model.Session;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ISessionRepository extends ReactiveCrudRepository<Session, String> {
    Mono<Session> findTop1ByAppTypeAndCategoryAndStatusAndUserNameOOrderByCreateTimeDesc(
            String appType,
            String category,
            String status,
            String userName);
}
