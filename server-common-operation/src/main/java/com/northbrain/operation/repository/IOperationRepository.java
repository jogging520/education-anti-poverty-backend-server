package com.northbrain.operation.repository;

import com.northbrain.operation.model.Operation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IOperationRepository extends ReactiveCrudRepository<Operation, String> {

    Flux<Operation> findByIdAndAppTypeAndCategoryAndStatusAndUserAndBusinessTypeAndCreateTimeBetween(String id,
                                                                                                     String appType,
                                                                                                     String category,
                                                                                                     String status,
                                                                                                     String user,
                                                                                                     String businessType,
                                                                                                     Date fromCreateTime,
                                                                                                     Date toCreateDate);

    Flux<Operation> findByIdAndAppTypeAndCategoryAndStatusAndBusinessTypeAndCreateTimeBetween(String id,
                                                                                              String appType,
                                                                                              String category,
                                                                                              String status,
                                                                                              String businessType,
                                                                                              Date fromCreateTime,
                                                                                              Date toCreateDate);

    Flux<Operation> findByAppTypeAndCategoryAndStatusAndUserAndBusinessTypeAndCreateTimeBetween(String appType,
                                                                                                String category,
                                                                                                String status,
                                                                                                String user,
                                                                                                String businessType,
                                                                                                Date fromCreateTime,
                                                                                                Date toCreateDate);

    Flux<Operation> findByAppTypeAndCategoryAndStatusAndBusinessTypeAndCreateTimeBetween(String appType,
                                                                                         String category,
                                                                                         String status,
                                                                                         String businessType,
                                                                                         Date fromCreateTime,
                                                                                         Date toCreateDate);

    Flux<Operation> findByCategoryAndStatusAndUserAndBusinessTypeAndCreateTimeBetween(String category,
                                                                                      String status,
                                                                                      String user,
                                                                                      String businessType,
                                                                                      Date fromCreateTime,
                                                                                      Date toCreateDate);

    Flux<Operation> findByCategoryAndStatusAndBusinessTypeAndCreateTimeBetween(String category,
                                                                               String status,
                                                                               String businessType,
                                                                               Date fromCreateTime,
                                                                               Date toCreateDate);

    Flux<Operation> findByAppTypeAndCategoryAndStatusAndUserAndCreateTimeBetween(String appType,
                                                                                 String category,
                                                                                 String status,
                                                                                 String user,
                                                                                 Date fromCreateTime,
                                                                                 Date toCreateDate);

    Flux<Operation> findByAppTypeAndCategoryAndStatusAndCreateTimeBetween(String appType,
                                                                          String category,
                                                                          String status,
                                                                          Date fromCreateTime,
                                                                          Date toCreateDate);

    Flux<Operation> findByCategoryAndStatusAndUserAndCreateTimeBetween(String category,
                                                                       String status,
                                                                       String user,
                                                                       Date fromCreateTime,
                                                                       Date toCreateDate);

    Flux<Operation> findByCategoryAndStatusAndCreateTimeBetween(String category,
                                                                String status,
                                                                Date fromCreateTime,
                                                                Date toCreateDate);
}
