package com.foodrecipes.webapp.dto;

public class CommentDTO {
    private String content;
    private String date;
    private String picture;
    private Long userId;
    private Long recipeId;

    /**
     * No-arg Constructor
     */
    public CommentDTO() {
    }

    /**
     * Constructor
     * 
     * @param content
     * @param date
     * @param picture
     * @param userId
     * @param recipeId
     */
    public CommentDTO(String content, String date, String picture, Long userId, Long recipeId) {
        this.content = content;
        this.date = date;
        this.picture = picture;
        this.userId = userId;
        this.recipeId = recipeId;
    }

    // Standard getter and setter methods for accessing and updating the field
    // values.
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content.equals("") || content.length() == 0) {
            throw new IllegalArgumentException("Invalid content");
        }
        this.content = content.strip();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((recipeId == null) ? 0 : recipeId.hashCode());
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
        CommentDTO other = (CommentDTO) obj;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (recipeId == null) {
            if (other.recipeId != null)
                return false;
        } else if (!recipeId.equals(other.recipeId))
            return false;
        return true;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "CommentDTO [content=" + content + ", date=" + date + ", userId=" + userId + ", recipeId=" + recipeId
                + "]";
    }

}
