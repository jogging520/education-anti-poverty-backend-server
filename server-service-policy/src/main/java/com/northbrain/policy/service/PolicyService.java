package com.northbrain.policy.service;

import com.northbrain.policy.model.Constants;
import com.northbrain.policy.model.Policy;
import com.northbrain.policy.model.PolicyHistory;
import com.northbrain.policy.repository.IPolicyHistoryRepository;
import com.northbrain.policy.repository.IPolicyRepository;
import com.northbrain.util.timer.Clock;
import lombok.extern.java.Log;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Log
public class PolicyService {
    private final IPolicyRepository policyRepository;
    private final IPolicyHistoryRepository policyHistoryRepository;

    public PolicyService(IPolicyRepository policyRepository,
                         IPolicyHistoryRepository policyHistoryRepository) {
        this.policyRepository = policyRepository;
        this.policyHistoryRepository = policyHistoryRepository;
    }


    /**
     * 方法：根据ID号获取政策信息
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 分类（企业类型）
     * @param id id号
     * @return 政策信息
     */
    public Mono<Policy> queryPolicyById(String serialNo,
                                        String appType,
                                        String category,
                                        String id){
        return this.policyRepository.findById(id)
                .filter(policy -> policy.getCategory().equalsIgnoreCase(category))
                .filter(policy -> ArrayUtils.contains(policy.getAppTypes(), appType))
                .filter(policy -> policy.getStatus().equalsIgnoreCase(Constants.POLICY_STATUS_ACTIVE))
                .map(policy -> {
                   log.info(Constants.POLICY_OPERATION_SERIAL_NO + serialNo);

                   return policy;
                });
    }

    /**
     * 方法：根据名称查询政策信息
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 分类（企业类型）
     * @param name 名称
     * @return 政策信息
     */
    public Flux<Policy> queryPoliciesByName(String serialNo,
                                            String appType,
                                            String category,
                                            String name){
        return this.policyRepository.findByCategoryAndStatusAndNameLike(
                category, Constants.POLICY_STATUS_ACTIVE, name)
                .filter(policy -> ArrayUtils.contains(policy.getAppTypes(), appType))
                .map(policy -> {
                    log.info(Constants.POLICY_OPERATION_SERIAL_NO + serialNo) ;

                    return policy;
                });
    }

    /**
     * 方法：创建政策信息
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 分类（企业类型）
     * @param policies 政策信息
     * @return 创建成功的政策信息
     */
    public Flux<Policy> createPolicies(String serialNo,
                                       String appType,
                                       String category,
                                       Flux<Policy> policies){
        return policies
                .flatMap(policy -> {
                    log.info(Constants.POLICY_OPERATION_SERIAL_NO + serialNo);

                    return this.policyRepository
                            .findByCategoryAndStatusAndName(category, Constants.POLICY_STATUS_ACTIVE,
                                    policy.getName())
                            .filter(newPolicy -> ArrayUtils.contains(newPolicy.getAppTypes(), appType))
                            .map(oldPolicy -> oldPolicy.setStatus(Constants.POLICY_ERRORCODE_HAS_EXISTS))
                            .switchIfEmpty(this.policyRepository
                                    .save(policy
                                            .setCategory(category)
                                            .setStatus(Constants.POLICY_STATUS_ACTIVE)
                                            .setCreateTime(Clock.currentTime())
                                            .setTimestamp(Clock.currentTime())
                                            .setSerialNo(serialNo))
                                    .map(newPolicy -> {
                                        this.policyHistoryRepository
                                                .save(PolicyHistory.builder()
                                                        .operationType(Constants.POLICY_HISTORY_CREATE)
                                                        .type(newPolicy.getType())
                                                        .appTypes(newPolicy.getAppTypes())
                                                        .category(newPolicy.getCategory())
                                                        .name(newPolicy.getName())
                                                        .grade(newPolicy.getGrade())
                                                        .keywords(newPolicy.getKeywords())
                                                        .readings(newPolicy.getReadings())
                                                        .createTime(newPolicy.getCreateTime())
                                                        .timestamp(Clock.currentTime())
                                                        .status(newPolicy.getStatus())
                                                        .serialNo(serialNo)
                                                        .description(newPolicy.getDescription())
                                                        .build())
                                                .subscribe();

                                        return newPolicy.setStatus(Constants.POLICY_ERRORCODE_SUCCESS);
                                    })
                            );
                });
    }

    /**
     * 方法：更新政策信息
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 分类（企业类型）
     * @param policies 政策信息
     * @return 创建成功的政策信息
     */
    public Flux<Policy> updatePolicies(String serialNo,
                                       String appType,
                                       String category,
                                       Flux<Policy> policies){
        return policies
                .flatMap(policy -> {
                    log.info(Constants.POLICY_OPERATION_SERIAL_NO + serialNo);

                    return this.policyRepository
                            .findByCategoryAndStatusAndName(category, Constants.POLICY_STATUS_ACTIVE,
                                    policy.getName())
                            .filter(newPolicy -> ArrayUtils.contains(newPolicy.getAppTypes(), appType))
                            .flatMap(oldPolicy ->  this.policyRepository
                                        .save(policy
                                                .setCategory(category)
                                                .setStatus(Constants.POLICY_STATUS_ACTIVE)
                                                .setCreateTime(Clock.currentTime())
                                                .setTimestamp(Clock.currentTime())
                                                .setSerialNo(serialNo))
                                        .map(newPolicy -> {
                                            this.policyHistoryRepository
                                                    .save(PolicyHistory.builder()
                                                            .operationType(Constants.POLICY_HISTORY_UPDATE)
                                                            .type(newPolicy.getType())
                                                            .appTypes(newPolicy.getAppTypes())
                                                            .category(newPolicy.getCategory())
                                                            .name(newPolicy.getName())
                                                            .grade(newPolicy.getGrade())
                                                            .keywords(newPolicy.getKeywords())
                                                            .readings(newPolicy.getReadings())
                                                            .createTime(newPolicy.getCreateTime())
                                                            .timestamp(Clock.currentTime())
                                                            .status(newPolicy.getStatus())
                                                            .serialNo(serialNo)
                                                            .description(newPolicy.getDescription())
                                                            .build())
                                                    .subscribe();

                                            return newPolicy.setStatus(Constants.POLICY_ERRORCODE_SUCCESS); }))
                            .switchIfEmpty(Mono.just(policy.setStatus(Constants.POLICY_ERRORCODE_NOT_EXISTS)));
                });
    }

    /**
     * 方法：删除政策信息
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 分类（企业类型）
     * @param policies 政策信息
     * @return 创建成功的政策信息
     */
    public Flux<Policy> deletePolicies(String serialNo,
                                       String appType,
                                       String category,
                                       Flux<Policy> policies){
        return policies
                .flatMap(policy -> {
                    log.info(Constants.POLICY_OPERATION_SERIAL_NO + serialNo);

                    return this.policyRepository
                            .findByCategoryAndStatusAndName(category, Constants.POLICY_STATUS_ACTIVE,
                                    policy.getName())
                            .filter(newPolicy -> ArrayUtils.contains(newPolicy.getAppTypes(), appType))
                            .map(oldPolicy ->  {
                                this.policyHistoryRepository
                                    .save(PolicyHistory.builder()
                                            .operationType(Constants.POLICY_HISTORY_DELETE)
                                            .type(oldPolicy.getType())
                                            .appTypes(oldPolicy.getAppTypes())
                                            .category(oldPolicy.getCategory())
                                            .name(oldPolicy.getName())
                                            .grade(oldPolicy.getGrade())
                                            .keywords(oldPolicy.getKeywords())
                                            .readings(oldPolicy.getReadings())
                                            .createTime(oldPolicy.getCreateTime())
                                            .timestamp(Clock.currentTime())
                                            .status(oldPolicy.getStatus())
                                            .serialNo(serialNo)
                                            .description(oldPolicy.getDescription())
                                            .build())
                                    .map(policyHistory -> this.policyRepository
                                            .delete(policy)
                                            .subscribe()
                                    );

                                return oldPolicy.setStatus(Constants.POLICY_ERRORCODE_SUCCESS); })
                            .switchIfEmpty(Mono.just(policy.setStatus(Constants.POLICY_ERRORCODE_NOT_EXISTS)));
                });
    }

}
