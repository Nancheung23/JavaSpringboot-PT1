package com.foodrecipes.webapp.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodrecipes.webapp.dto.UserLogDTO;
import com.foodrecipes.webapp.model.UserLog;
import com.foodrecipes.webapp.repository.UserLogRepository;
import com.foodrecipes.webapp.service.UserLogConversionService;

@RestController
@RequestMapping("/api/userLog")
public class UserLogController {

    private final UserLogRepository userLogRepository;
    private final UserLogConversionService userLogConversionService;

    @Autowired
    public UserLogController(UserLogRepository userLogRepository, UserLogConversionService userLogConversionService) {
        this.userLogRepository = userLogRepository;
        this.userLogConversionService = userLogConversionService;
    }

    /**
     * Get all logs
     * @return
     */
    @GetMapping("/")
    public Iterable<UserLog> getAllUserLog() {
        return userLogRepository.findAll();
    }

    /**
     * Get logs for one user
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Iterable<UserLogDTO> getUserLogById(@PathVariable Long id) {
        Stream<UserLog> logs = StreamSupport.stream(userLogRepository.findAll().spliterator(), false);
        return logs.filter(l -> l.getUserId().equals(id))
        .sorted((l1, l2) -> l1.getTime().compareTo(l2.getTime()))
        .map(userLogConversionService::convertToDto)
        .collect(Collectors.toList());
    }
}
