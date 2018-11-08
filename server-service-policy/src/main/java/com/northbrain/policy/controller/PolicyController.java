package com.northbrain.policy.controller;

import com.northbrain.policy.model.Constants;
import com.northbrain.policy.model.Policy;
import com.northbrain.policy.service.PolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PolicyController {
    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }


    /**
     * 方法：创建政策信息
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 分类（企业类型）
     * @param policies 政策信息
     * @return 创建成功的政策信息
     */
    @PostMapping(Constants.POLICY_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Policy>> createPolicies(@RequestParam String serialNo,
                                                       @RequestParam String category,
                                                       @RequestParam String appType,
                                                       @RequestBody Flux<Policy> policies){
        return ResponseEntity.ok()
                .body(this.policyService.createPolicies(serialNo, appType, category, policies));

    }

    /**
     * 方法：更新政策信息
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 分类（企业类型）
     * @param policies 政策信息
     * @return 创建成功的政策信息
     */
    @PutMapping(Constants.POLICY_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Policy>> updatePolicies(@RequestParam String serialNo,
                                                       @RequestParam String category,
                                                       @RequestParam String appType,
                                                       @RequestBody Flux<Policy> policies){
        return ResponseEntity.ok()
                .body(this.policyService.updatePolicies(serialNo, appType, category, policies));

    }

    /**
     * 方法：删除政策信息
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 分类（企业类型）
     * @param policies 政策信息
     * @return 创建成功的政策信息
     */
    @DeleteMapping(Constants.POLICY_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Policy>> deletePolicies(@RequestParam String serialNo,
                                                       @RequestParam String category,
                                                       @RequestParam String appType,
                                                       @RequestBody Flux<Policy> policies){
        return ResponseEntity.ok()
                .body(this.policyService.deletePolicies(serialNo, appType, category, policies));

    }

    /**
     * 方法：根据ID号获取政策信息
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 分类（企业类型）
     * @param id id号
     * @return 政策信息
     */
    @GetMapping(Constants.POLICY_HTTP_SPECIFIED_REQUEST_MAPPING)
    public ResponseEntity<Mono<Policy>> queryPolicyById(@RequestParam String serialNo,
                                                        @RequestParam String appType,
                                                        @RequestParam String category,
                                                        @RequestParam String id) {
        return ResponseEntity.ok()
                .body(this.policyService.queryPolicyById(serialNo, appType, category, id));
    }

    /**
     * 方法：根据名称查询政策信息
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 分类（企业类型）
     * @param name 名称
     * @return 政策信息
     */
    @GetMapping(Constants.POLICY_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Policy>> queryPolicesByName(@RequestParam String serialNo,
                                                           @RequestParam String category,
                                                           @RequestParam String appType,
                                                           @RequestParam String name){
            return ResponseEntity.ok()
                    .body(this.policyService
                            .queryPoliciesByName(serialNo, appType, category, name));
    }
}
