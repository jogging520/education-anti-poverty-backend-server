package com.northbrain.operation.controller;

import com.northbrain.operation.model.Constants;
import com.northbrain.operation.model.Operation;
import com.northbrain.operation.model.Record;
import com.northbrain.operation.service.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService){
        this.operationService = operationService;
    }

    /**
     * 方法：根据各类组合信息查询操作记录
     * @param serialNo 查询流水号
     * @param id 流水号（记录ID）
     * @param channelType 渠道类型
     * @param category 类别（企业）
     * @param user 用户信息
     * @param businessType 业务类型
     * @param fromCreateTime 查询开始时间
     * @param toCreateTime 查询结束时间
     * @return 操作记录
     */
    @GetMapping(Constants.OPERATION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Operation>> queryOperations(@RequestParam String serialNo,
                                                           @RequestParam(required = false) String id,
                                                           @RequestParam(required = false) String channelType,
                                                           @RequestParam String category,
                                                           @RequestParam String user,
                                                           @RequestParam(required = false) String businessType,
                                                           @RequestParam(required = false) Long fromCreateTime,
                                                           @RequestParam(required = false) Long toCreateTime) {
        if(id != null && channelType != null && businessType != null &&
                fromCreateTime != null && toCreateTime != null)
            return ResponseEntity.ok()
                    .body(this.operationService
                            .queryOperationsByIdAndAppTypeAndBusinessTypeAndUserAndCreateTime(serialNo, id,
                                    channelType, category, user, businessType, fromCreateTime, toCreateTime));

        if(id == null && channelType != null && businessType != null && fromCreateTime != null && toCreateTime != null)
            return ResponseEntity.ok()
                    .body(this.operationService
                            .queryOperationsByAppTypeAndBusinessTypeAndUserAndCreateTime(serialNo, channelType,
                                    category, user, businessType, fromCreateTime, toCreateTime));

        if(id == null && channelType == null && businessType != null && fromCreateTime != null && toCreateTime != null)
            return ResponseEntity.ok()
                    .body(this.operationService
                            .queryOperationsByBusinessTypeAndUserAndCreateTime(serialNo, category,
                                    user, businessType, fromCreateTime, toCreateTime));

        if(id == null && channelType != null && businessType == null && fromCreateTime != null && toCreateTime != null)
            return ResponseEntity.ok()
                    .body(this.operationService
                            .queryOperationsByAppTypeAndUserAndCreateTime(serialNo, channelType, category,
                                    user, fromCreateTime, toCreateTime));

        if(id == null && channelType == null && businessType == null && fromCreateTime != null && toCreateTime != null)
            return ResponseEntity.ok()
                    .body(this.operationService
                            .queryOperationsByUserAndCreateTime(serialNo, category,
                                    user, fromCreateTime, toCreateTime));

        return ResponseEntity.badRequest().body(null);
    }

    /**
     * 方法：创建一条操作记录
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param user 用户编号
     * @param session 会话编号
     * @param operation 操作记录
     * @return 创建成功的操作记录
     */
    @PostMapping(Constants.OPERATION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Operation>> createOperation(@RequestParam String appType,
                                                           @RequestParam String category,
                                                           @RequestParam String user,
                                                           @RequestParam String session,
                                                           @RequestBody Operation operation) {
        return ResponseEntity.ok()
                .body(this.operationService
                        .createOperation(appType, category, user, session, operation));
    }

    /**
     * 方法：根据流水号查找操作记录
     * @param serialNo 流水号
     * @return 操作记录
     */
    @GetMapping(Constants.OPERATION_SPECIFIED_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Operation>> queryOperationById(@RequestParam String serialNo) {
        return ResponseEntity.ok()
                .body(this.operationService
                        .queryOperationById(serialNo));
    }

    /**
     * 方法：根据流水号查找操作详细记录
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @return 操作详细记录
     */
    @GetMapping(Constants.OPERATION_RECORD_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Record>> queryRecordsBySerialNo(@RequestParam String serialNo,
                                                               @RequestParam String category) {
        return ResponseEntity.ok()
                .body(this.operationService
                        .queryRecordsBySerialNo(serialNo, category));
    }

    /**
     * 方法：创建新记录（操作明细）
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param record 明细记录
     * @return 创建成功的明细记录
     */
    @PostMapping(Constants.OPERATION_RECORD_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Record>> createRecord(@RequestParam String appType,
                                                     @RequestParam String category,
                                                     @RequestBody Record record) {
        return ResponseEntity.ok()
                .body(this.operationService
                        .createRecord(appType, category, record));
    }

}
