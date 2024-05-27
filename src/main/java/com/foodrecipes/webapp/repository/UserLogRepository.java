package com.foodrecipes.webapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.foodrecipes.webapp.model.UserLog;

public interface UserLogRepository extends CrudRepository<UserLog, Long>{
    /**
     * find logs for one user
     * @param userId
     * @return
     */
    List<Optional<UserLog>> findAllByUserId(Long userId);
}
