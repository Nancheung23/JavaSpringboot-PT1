package com.foodrecipes.webapp.dto;

public class RecipeDTO {
    private String title;
    private String content;
    private double rating;
    private int views;
    private String picture;
    private Long userId;

    // Non-parameter constructor
    public RecipeDTO() {
    }

    /**
     * Constructor for recipe DTO
     * 
     * @param title
     * @param content
     * @param rating
     * @param views
     * @param picture
     * @param userId
     */
    public RecipeDTO(String title, String content, double rating, int views, String picture, Long userId) {
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.views = views;
        this.picture = picture;
        this.userId = userId;
    }

    /**
     * Standart getter for recipe title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter: recipe title
     * The title length must be between 4 and 40 characters.
     * 
     * @param title
     * @throws IllegalArgumentException if the title length does not match the length limits
     */
    public void setTitle(String title) {
        if (title.length() < 4 || title.length() > 40) {
            throw new IllegalArgumentException("Invalid title, 4 to 40 symbols allowed.");
        }
        this.title = title;
    }

    /**
     * Standart getter for recipe content
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter: recipe content
     * 
     * @param content
     * @throws IllegalArgumentException if the content is empty
     */
    public void setContent(String content) {
        if (content.equals("") || content.length() < 1) {
            throw new IllegalArgumentException("Empty content.");
        }
        this.content = content;
    }

    /**
     * Standart getter for id of the recipe author
     * @return user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Setter: user id
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Standart getter for recipe rating
     * @return recipe rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Setter: recipe rating
     * @param rating
     * @throws IllegalArgumentException if the rating is negative 
     */
    public void setRating(double rating) {
        if (rating < 0) {
            throw new IllegalArgumentException("Invalid rating");
        }
        rating = (getRating() + rating) / 2;
        this.rating = rating;
    }

    /**
     * Standart getter for recipe number of views
     * @return number of views
     */
    public int getViews() {
        return views;
    }

    /**
     * Setter: views number
     * 
     * @param views
     * @throws IllegalArgumentException if the views number is negative
     */
    public void setViews(int views) {
        if (views < 0) {
            throw new IllegalArgumentException("Invalid views");
        }
        this.views = views;
    }

    /**
     * Standart getter for picture url/path
     * @return picture url/path
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Setter: picture url/path
     * 
     * @param picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Overriden hashCode method for enhanced debugging
     * 
     * @return object hash code
     */
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

    /**
     * Overriden equals method for enhanced debugging
     * 
     * @return comparison result
     */
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

    /**
     * Overriden toString method for enhanced debugging
     * 
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return "RecipeDTO [title=" + title + ", content=" + content + ", rating=" + rating + ", views=" + views
                + ", userId=" + userId + "]";
    }
}
