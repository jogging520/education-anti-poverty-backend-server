package com.northbrain.policy.controller;

import com.northbrain.policy.model.Constants;
import com.northbrain.policy.model.Policy;
import com.northbrain.policy.service.PolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class PolicyController {
    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }


    @PostMapping(Constants.POLICY_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Policy>> createPolices(@RequestParam String serialNo,
                                                      @RequestParam String category,
                                                      @RequestParam String appType,
                                                      @RequestBody Flux<Policy> policies){
        return ResponseEntity.ok()
                .body(this.policyService.createPolicies(serialNo, appType, category, policies));

    }
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
