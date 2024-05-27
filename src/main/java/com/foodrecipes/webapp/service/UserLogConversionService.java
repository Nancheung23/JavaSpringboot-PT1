package com.foodrecipes.webapp.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrecipes.webapp.dto.UserLogDTO;
import com.foodrecipes.webapp.model.UserLog;
import com.foodrecipes.webapp.repository.UserLogRepository;
import com.foodrecipes.webapp.repository.UserRepository;

@Service
public class UserLogConversionService {

    private UserRepository userRepository;

    private UserLogRepository userLogRepository;

    DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructor
     * @param userRepository
     * @param userLogRepository
     */
    @Autowired
    public UserLogConversionService(UserRepository userRepository, UserLogRepository userLogRepository) {
        this.userRepository = userRepository;
        this.userLogRepository = userLogRepository;
    }

    /**
     * Convert userLogDto to userLog entity
     * @param userLogDto
     * @return
     */
    public UserLog convertToEntity(UserLogDTO userLogDto) {
        UserLog userLog = new UserLog();
        String time = userLogDto.getTime();
        String detail = userLogDto.getDetail();
        Long userId = userLogDto.getUserId();
        userLog.setTime(LocalDateTime.parse(time, dft));
        userLog.setDetail(detail);
        userLog.setUserId(userId);
        return userLog;
    }

    /**
     * Convert userLog entity to userLogDTO 
     * @param userLog
     * @return
     */
    public UserLogDTO convertToDto(UserLog userLog) {
        return new UserLogDTO(userLog.getTime().toString(), userLog.getDetail(), userLog.getUserId());
    }

    /**
     * entry method for record logs
     * @param detail
     * @param userId
     * @return
     */
    public UserLog recordLog(String detail, Long userId) {
        String ldt = LocalDateTime.now().format(dft);
        return convertToEntity(new UserLogDTO(ldt, detail, userId));
    }
} 
