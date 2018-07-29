package com.northbrain.school.repository;

import com.northbrain.school.model.SchoolHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ISchoolHistoryRepository extends ReactiveCrudRepository<SchoolHistory, String> {

}
