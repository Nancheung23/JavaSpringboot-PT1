package com.foodrecipes.webapp.dto;

public class RecipeDTO {
    private String title;
    private String content;
    private double rating;
    private int views;
    private Long userId;

    // Non-parameter constructor
    public RecipeDTO() {
    }

    /**
     * 
     * @param title
     * @param content
     * @param rating
     * @param views
     * @param userId
     */
    public RecipeDTO(String title, String content, double rating, int views, Long userId) {
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.views = views;
        this.userId = userId;
    }

    // Standard getter and setter methods for accessing and updating the field
    // values.
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.length() <= 3) {
            throw new IllegalArgumentException("Invalid title");
        }
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content.equals("") || content.length() < 1) {
            throw new IllegalArgumentException("Invalid content");
        }
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (rating < 0) {
            throw new IllegalArgumentException("Invalid rating");
        }
        rating = (getRating() + rating) / 2;
        this.rating = rating;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        if (views < 0) {
            throw new IllegalArgumentException("Invalid views");
        }
        this.views = views;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        long temp;
        temp = Double.doubleToLongBits(rating);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + views;
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
        RecipeDTO other = (RecipeDTO) obj;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (Double.doubleToLongBits(rating) != Double.doubleToLongBits(other.rating))
            return false;
        if (views != other.views)
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
        return "RecipeDTO [title=" + title + ", content=" + content + ", rating=" + rating + ", views=" + views
                + ", userId=" + userId + "]";
    }
}
