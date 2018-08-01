package com.northbrain.menu.controller;

import com.northbrain.menu.model.CmsMenu;
import com.northbrain.menu.model.Constants;
import com.northbrain.menu.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class CmsController {
    private final MenuService menuService;


    public CmsController(MenuService menuService){
        this.menuService = menuService;
    }

    /**
     * 方法：获取CMS系统的菜单列表
     * @param serialNo 操作流水号
     * @return 菜单
     */
    @GetMapping(Constants.MENU_CMS_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<CmsMenu>> queryCmsMenus(String serialNo) {
        return ResponseEntity
                .ok()
                .body(this.menuService
                        .queryCmsMenus(serialNo));
    }

}
