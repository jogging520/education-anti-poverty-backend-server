package com.northbrain.organization.controller;

import com.northbrain.organization.model.Constants;
import com.northbrain.organization.model.Organization;
import com.northbrain.organization.model.Region;
import com.northbrain.organization.service.OrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    /**
     * 方法：查找区域信息
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @return 区域信息
     */
    @GetMapping(Constants.ORGANIZATION_REGION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Region>> queryRegions(@RequestParam String serialNo,
                                                     @RequestParam String category) {
        return ResponseEntity
                .ok()
                .body(this.organizationService
                        .queryRegions(serialNo, category));
    }

    /**
     * 方法：查询组织机构信息
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @return 组织机构信息
     */
    @GetMapping(Constants.ORGANIZATION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Organization>> queryOrganizations(@RequestParam String serialNo,
                                                                 @RequestParam String category) {
        return ResponseEntity
                .ok()
                .body(this.organizationService
                        .queryOrganizations(serialNo, category));
    }

    /**
     * 方法：创建区域信息
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param region 区域对象
     * @return 创建成功的区域信息
     */
    @PostMapping(Constants.ORGANIZATION_REGION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Region>> createRegion(@RequestParam String serialNo,
                                                     @RequestParam String category,
                                                     @RequestBody Region region) {
        return ResponseEntity
                .ok()
                .body(this.organizationService
                        .createRegion(serialNo, category, region));
    }

    /**
     * 创建组织机构
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param organization 组织机构
     * @return 创建成功的组织机构
     */
    @PostMapping(Constants.ORGANIZATION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Organization>> createOrganization(@RequestParam String serialNo,
                                                                 @RequestParam String category,
                                                                 @RequestBody Organization organization) {
        return ResponseEntity
                .ok()
                .body(this.organizationService
                        .createOrganization(serialNo, category, organization));
    }
}
