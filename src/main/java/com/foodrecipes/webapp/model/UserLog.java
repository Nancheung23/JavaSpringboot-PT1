package com.foodrecipes.webapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "logs")
public class UserLog implements Comparable<LocalDateTime> {

    @Id // Marks this field as the primary key of the user table.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the way the ID is generated, using the database
                                                        // identity column.
    private Long id; // Unique identifier for log.

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "log detail", nullable = true)
    private String detail;

    @Column(name = "user id", nullable =  true)
    private Long userId;

    /**
     * Constructor
     * @param time
     * @param detail
     * @param userId
     */
    public UserLog(LocalDateTime time, String detail, Long userId) {
        this.time = time;
        this.detail = detail;
        this.userId = userId;
    }

    /**
     * No-arg
     */
    public UserLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
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
        UserLog other = (UserLog) obj;
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
        return "UserLog [id=" + id + ", time=" + time + ", detail=" + detail + ", userId=" + userId + "]";
    }

    /**
     * Compare log time with `time` attribute
     * @param o
     * @return
     */
    @Override
    public int compareTo(LocalDateTime o) {
        if (this.getTime().isBefore(o)) {
            return -1;
        } else if (this.getTime().isAfter(o)) {
            return 1;
        } else {
            return 0;
        }
    }
}
