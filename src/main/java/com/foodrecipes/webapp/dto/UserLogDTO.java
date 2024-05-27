package com.foodrecipes.webapp.dto;

public class UserLogDTO {
    private String time;
    private String detail;
    private Long userId;

    /**
     * Constructor
     * @param time
     * @param detail
     * @param userId
     */
    public UserLogDTO(String time, String detail, Long userId) {
        this.time = time;
        this.detail = detail;
        this.userId = userId;
    }

    /**
     * No-arg
     */
    public UserLogDTO() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException(String.format("Invalid user id: %L", userId));
        }
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((detail == null) ? 0 : detail.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserLogDTO other = (UserLogDTO) obj;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        if (detail == null) {
            if (other.detail != null)
                return false;
        } else if (!detail.equals(other.detail))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserLogDTO [time=" + time + ", detail=" + detail + ", userId=" + userId + "]";
    }

    
}
