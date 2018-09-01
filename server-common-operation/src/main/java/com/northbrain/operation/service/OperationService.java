package com.northbrain.operation.service;

import com.northbrain.operation.model.Constants;
import com.northbrain.operation.model.Operation;
import com.northbrain.operation.model.Record;
import com.northbrain.operation.repository.IOperationRepository;
import com.northbrain.operation.repository.IRecordRepository;
import com.northbrain.util.timer.Clock;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Log
public class OperationService {

    private final IOperationRepository operationRepository;
    private final IRecordRepository recordRepository;

    public OperationService(IOperationRepository operationRepository,
                            IRecordRepository recordRepository){
        this.operationRepository = operationRepository;
        this.recordRepository = recordRepository;
    }

    /**
     * 方法：根据ID、应用类型、企业类型、业务类型、用户和时间段查找操作记录
     * @param serialNo 流水号
     * @param id 流水号（记录ID）
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param user 用户
     * @param businessType 业务类型
     * @param fromCreateTime 开始时间
     * @param toCreateTime 结束时间
     * @return 操作记录
     */
    public Flux<Operation> queryOperationsByIdAndAppTypeAndBusinessTypeAndUserAndCreateTime(String serialNo,
                                                                                            String id,
                                                                                            String appType,
                                                                                            String category,
                                                                                            String user,
                                                                                            String businessType,
                                                                                            Long fromCreateTime,
                                                                                            Long toCreateTime) {
        if(user.equalsIgnoreCase(Constants.OPERATION_QUERY_ALL_USER)) {
            return this.operationRepository
                    .findByIdAndAppTypeAndCategoryAndStatusAndBusinessTypeAndCreateTimeBetweenOrderByCreateTimeDesc(
                            id, appType, category, Constants.OPERATION_STATUS_ACTIVE,
                            businessType, Clock.getDate(fromCreateTime),
                            Clock.getDate(toCreateTime))
                    .map(operation -> {
                        log.info(Constants.OPERATION_OPERATION_SERIAL_NO + serialNo);

                        return operation;
                    });
        }

        return this.operationRepository
                .findByIdAndAppTypeAndCategoryAndStatusAndUserAndBusinessTypeAndCreateTimeBetweenOrderByCreateTimeDesc(
                        id, appType, category, Constants.OPERATION_STATUS_ACTIVE,
                        user, businessType, Clock.getDate(fromCreateTime),
                        Clock.getDate(toCreateTime))
                .map(operation -> {
                    log.info(Constants.OPERATION_OPERATION_SERIAL_NO + serialNo);

                    return operation;
                });
    }

    /**
     * 方法：根据企业类型、应用类型、业务类型、用户和时间段查找操作记录
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param user 用户
     * @param businessType 业务类型
     * @param fromCreateTime 开始时间
     * @param toCreateTime 结束时间
     * @return 操作记录
     */
    public Flux<Operation> queryOperationsByAppTypeAndBusinessTypeAndUserAndCreateTime(String serialNo,
                                                                                       String appType,
                                                                                       String category,
                                                                                       String user,
                                                                                       String businessType,
                                                                                       Long fromCreateTime,
                                                                                       Long toCreateTime) {
        if(user.equalsIgnoreCase(Constants.OPERATION_QUERY_ALL_USER)) {
            return this.operationRepository
                    .findByAppTypeAndCategoryAndStatusAndBusinessTypeAndCreateTimeBetweenOrderByCreateTimeDesc(
                            appType, category, Constants.OPERATION_STATUS_ACTIVE,
                            businessType, Clock.getDate(fromCreateTime),
                            Clock.getDate(toCreateTime))
                    .map(operation -> {
                        log.info(Constants.OPERATION_OPERATION_SERIAL_NO + serialNo);

                        return operation;
                    });
        }

        return this.operationRepository
                .findByAppTypeAndCategoryAndStatusAndUserAndBusinessTypeAndCreateTimeBetweenOrderByCreateTimeDesc(
                        appType, category, Constants.OPERATION_STATUS_ACTIVE,
                        user, businessType, Clock.getDate(fromCreateTime),
                        Clock.getDate(toCreateTime))
                .map(operation -> {
                    log.info(Constants.OPERATION_OPERATION_SERIAL_NO + serialNo);

                    return operation;
                });
    }

    /**
     * 方法：根据企业类型、业务类型、用户和时间段查找操作记录
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param user 用户
     * @param businessType 业务类型
     * @param fromCreateTime 开始时间
     * @param toCreateTime 结束时间
     * @return 操作记录
     */
    public Flux<Operation> queryOperationsByBusinessTypeAndUserAndCreateTime(String serialNo,
                                                                             String category,
                                                                             String user,
                                                                             String businessType,
                                                                             Long fromCreateTime,
                                                                             Long toCreateTime) {
        if(user.equalsIgnoreCase(Constants.OPERATION_QUERY_ALL_USER)) {
            return this.operationRepository
                    .findByCategoryAndStatusAndBusinessTypeAndCreateTimeBetweenOrderByCreateTimeDesc(
                            category, Constants.OPERATION_STATUS_ACTIVE,
                            businessType, Clock.getDate(fromCreateTime),
                            Clock.getDate(toCreateTime))
                    .map(operation -> {
                        log.info(Constants.OPERATION_OPERATION_SERIAL_NO + serialNo);

                        return operation;
                    });
        }

        return this.operationRepository
                .findByCategoryAndStatusAndUserAndBusinessTypeAndCreateTimeBetweenOrderByCreateTimeDesc(
                        category, Constants.OPERATION_STATUS_ACTIVE,
                        user, businessType, Clock.getDate(fromCreateTime),
                        Clock.getDate(toCreateTime))
                .map(operation -> {
                    log.info(Constants.OPERATION_OPERATION_SERIAL_NO + serialNo);

                    return operation;
                });
    }

    /**
     * 方法：根据企业类型、应用类型、用户和时间段查找操作记录
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param user 用户
     * @param fromCreateTime 开始时间
     * @param toCreateTime 结束时间
     * @return 操作记录
     */
    public Flux<Operation> queryOperationsByAppTypeAndUserAndCreateTime(String serialNo,
                                                                        String appType,
                                                                        String category,
                                                                        String user,
                                                                        Long fromCreateTime,
                                                                        Long toCreateTime) {
        if(user.equalsIgnoreCase(Constants.OPERATION_QUERY_ALL_USER)) {
            return this.operationRepository
                    .findByAppTypeAndCategoryAndStatusAndCreateTimeBetweenOrderByCreateTimeDesc(
                            appType, category, Constants.OPERATION_STATUS_ACTIVE,
                            Clock.getDate(fromCreateTime),
                            Clock.getDate(toCreateTime))
                    .map(operation -> {
                        log.info(Constants.OPERATION_OPERATION_SERIAL_NO + serialNo);

                        return operation;
                    });
        }

        return this.operationRepository
                .findByAppTypeAndCategoryAndStatusAndUserAndCreateTimeBetweenOrderByCreateTimeDesc(
                        appType, category, Constants.OPERATION_STATUS_ACTIVE,
                        user, Clock.getDate(fromCreateTime),
                        Clock.getDate(toCreateTime))
                .map(operation -> {
                    log.info(Constants.OPERATION_OPERATION_SERIAL_NO + serialNo);

                    return operation;
                });
    }

    /**
     * 方法：根据企业类型、业务类型、用户和时间段查找操作记录
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param user 用户
     * @param fromCreateTime 开始时间
     * @param toCreateTime 结束时间
     * @return 操作记录
     */
    public Flux<Operation> queryOperationsByUserAndCreateTime(String serialNo,
                                                              String category,
                                                              String user,
                                                              Long fromCreateTime,
                                                              Long toCreateTime) {
        if(user.equalsIgnoreCase(Constants.OPERATION_QUERY_ALL_USER)) {
            return this.operationRepository
                    .findByCategoryAndStatusAndCreateTimeBetweenOrderByCreateTimeDesc(
                            category, Constants.OPERATION_STATUS_ACTIVE,
                            Clock.getDate(fromCreateTime), Clock.getDate(toCreateTime))
                    .map(operation -> {
                        log.info(Constants.OPERATION_OPERATION_SERIAL_NO + serialNo);

                        return operation;
                    });
        }

        return this.operationRepository
                .findByCategoryAndStatusAndUserAndCreateTimeBetweenOrderByCreateTimeDesc(
                        category, Constants.OPERATION_STATUS_ACTIVE,
                        user, Clock.getDate(fromCreateTime), Clock.getDate(toCreateTime))
                .map(operation -> {
                    log.info(Constants.OPERATION_OPERATION_SERIAL_NO + serialNo);

                    return operation;
                });
    }

    /**
     * 方法：根据ID查询操作记录
     * @param serialNo 流水号
     * @return 操作记录
     */
    public Mono<Operation> queryOperationById(String serialNo) {
        return this.operationRepository
                .findById(serialNo)
                .map(operation -> operation);
    }

    /**
     * 方法：根据流水号查找详细流水记录
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @return 流水记录
     */
    public Flux<Record> queryRecordsBySerialNo(String serialNo,
                                               String category) {
        return this.recordRepository
                .findByCategoryAndStatusAndSerialNo(category, Constants.OPERATION_STATUS_ACTIVE, serialNo)
                .map(record -> {
                    log.info(Constants.OPERATION_OPERATION_SERIAL_NO + serialNo);

                    return record;
                });
    }

    /**
     * 方法：创建操作记录
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param user 用户编号
     * @param session 会话编号
     * @param operation 操作记录
     * @return 创建成功的操作记录
     */
    public Mono<Operation> createOperation(String appType,
                                           String category,
                                           String user,
                                           String session,
                                           Operation operation) {
        log.info(operation.toString());

        return this.operationRepository
                .save(operation
                        .setAppType(appType)
                        .setCategory(category)
                        .setUser(user)
                        .setSession(session)
                        .setStatus(Constants.OPERATION_STATUS_ACTIVE)
                        .setCreateTime(Clock.currentTime())
                        .setTimestamp(Clock.currentTime()))
                .map(newOperation -> newOperation
                        .setStatus(Constants.OPERATION_ERRORCODE_SUCCESS));
    }

    /**
     * 方法：创建新记录（操作明细）
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param record 明细记录
     * @return 创建成功的明细记录
     */
    public Mono<Record> createRecord(String appType,
                                     String category,
                                     Record record) {
        log.info(record.toString());

        return this.recordRepository
                .save(record
                        .setAppType(appType)
                        .setCategory(category)
                        .setStatus(Constants.OPERATION_STATUS_ACTIVE)
                        .setCreateTime(Clock.currentTime())
                        .setTimestamp(Clock.currentTime()))
                .map(newRecord -> newRecord
                        .setStatus(Constants.OPERATION_ERRORCODE_SUCCESS));
    }
}
