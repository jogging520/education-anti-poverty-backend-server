package com.northbrain.operation.repository;

import com.northbrain.operation.model.Operation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IOperationRepository extends ReactiveCrudRepository<Operation, String> {

    Flux<Operation> findByIdAndAppTypeAndCategoryAndStatusAndUserAndBusinessTypeAndCreateTimeBetweenOrderByCreateTimeDesc(
            String id,
            String appType,
            String category,
            String status,
            String user,
            String businessType,
            Date fromCreateTime,
            Date toCreateDate);

    Flux<Operation> findByIdAndAppTypeAndCategoryAndStatusAndBusinessTypeAndCreateTimeBetweenOrderByCreateTimeDesc(
            String id,
            String appType,
            String category,
            String status,
            String businessType,
            Date fromCreateTime,
            Date toCreateDate);

    Flux<Operation> findByAppTypeAndCategoryAndStatusAndUserAndBusinessTypeAndCreateTimeBetweenOrderByCreateTimeDesc(
            String appType,
            String category,
            String status,
            String user,
            String businessType,
            Date fromCreateTime,
            Date toCreateDate);

    Flux<Operation> findByAppTypeAndCategoryAndStatusAndBusinessTypeAndCreateTimeBetweenOrderByCreateTimeDesc(
            String appType,
            String category,
            String status,
            String businessType,
            Date fromCreateTime,
            Date toCreateDate);

    Flux<Operation> findByCategoryAndStatusAndUserAndBusinessTypeAndCreateTimeBetweenOrderByCreateTimeDesc(
            String category,
            String status,
            String user,
            String businessType,
            Date fromCreateTime,
            Date toCreateDate);

    Flux<Operation> findByCategoryAndStatusAndBusinessTypeAndCreateTimeBetweenOrderByCreateTimeDesc(
            String category,
            String status,
            String businessType,
            Date fromCreateTime,
            Date toCreateDate);

    Flux<Operation> findByAppTypeAndCategoryAndStatusAndUserAndCreateTimeBetweenOrderByCreateTimeDesc(
            String appType,
            String category,
            String status,
            String user,
            Date fromCreateTime,
            Date toCreateDate);

    Flux<Operation> findByAppTypeAndCategoryAndStatusAndCreateTimeBetweenOrderByCreateTimeDesc(
            String appType,
            String category,
            String status,
            Date fromCreateTime,
            Date toCreateDate);

    Flux<Operation> findByCategoryAndStatusAndUserAndCreateTimeBetweenOrderByCreateTimeDesc(
            String category,
            String status,
            String user,
            Date fromCreateTime,
            Date toCreateDate);

    Flux<Operation> findByCategoryAndStatusAndCreateTimeBetweenOrderByCreateTimeDesc(
            String category,
            String status,
            Date fromCreateTime,
            Date toCreateDate);
}
