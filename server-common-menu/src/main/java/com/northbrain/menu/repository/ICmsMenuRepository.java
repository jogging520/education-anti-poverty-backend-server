package com.northbrain.menu.repository;

import com.northbrain.menu.model.CmsMenu;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ICmsMenuRepository extends ReactiveCrudRepository<CmsMenu, String> {
}
