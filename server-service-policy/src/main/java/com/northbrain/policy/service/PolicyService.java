package com.northbrain.policy.service;

import com.northbrain.policy.model.Constants;
import com.northbrain.policy.model.Policy;
import com.northbrain.policy.repository.IPolicyHistoryRepository;
import com.northbrain.policy.repository.IPolicyRepository;
import com.northbrain.util.timer.Clock;
import lombok.extern.java.Log;
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


    public Mono<Policy> queryPolicyById(String serialNo,
                                        String appType,
                                        String category,
                                        String id){
        return this.policyRepository.findById(id)
                .filter(Policy -> Policy.getCategory().equalsIgnoreCase(category))
                .filter(Policy -> Policy.getStatus().equalsIgnoreCase(Constants.POLICY_STATUS_ACTIVE))
                .map(policy -> {
                   log.info(Constants.POLICY_OPERATION_SERIAL_NO+ serialNo);

                   return policy;
                });

    }

    public Flux<Policy> queryPoliciesByName(String serialNo,
                                            String appType,
                                            String category,
                                            String name){
        return this.policyRepository.findByCategoryAndStatusAndNameLike(
                category, Constants.POLICY_STATUS_ACTIVE, name)
                //.filter(policy -> ArrayUtils.contains(policy.getAppTypes(), appType))
                .map(policy -> {
                    log.info(Constants.POLICY_OPERATION_SERIAL_NO+ serialNo) ;
                    return policy;
                });

    }

    public Flux<Policy> createPolicies(String serialNo,
                                       String appType,
                                       String category,
                                       Flux<Policy> policies){

        return policies
                .flatMap(policy -> {
                    log.info(Constants.POLICY_OPERATION_SERIAL_NO+ serialNo);

                    return this.policyRepository
                            .findByName(policy.getName())
                            .switchIfEmpty(this.policyRepository
                                    .save(policy
                                            .setCategory(category)
                                            .setStatus(Constants.POLICY_STATUS_ACTIVE)
                                            .setCreateTime(Clock.currentTime())
                                            .setTimestamp(Clock.currentTime())
                                            .setSerialNo(serialNo)))
                            .map(newPolicy -> newPolicy.setStatus(Constants.POLICY_ERRORCODE_HAS_EXISTS));
                });

    }




}
