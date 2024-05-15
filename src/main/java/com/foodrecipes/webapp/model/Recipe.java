package com.foodrecipes.webapp.model;

import java.util.HashSet;
import java.util.Set;

// Importing JPA annotations and other necessary Java utilities.
import jakarta.persistence.*;

/**
 * Represents a recipe entity in the application.
 * The @Entity annotation marks this class as a JPA entity, meaning it will be
 * mapped to a table in the database.
 */
@Entity
@Table(name = "recipe")
public class Recipe implements Comparable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "rating", nullable = true)
    private double rating;

    @Column(name = "views", nullable = false)
    private int views;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();

    /**
     * No-argument constructor required by JPA for creating instances.
     */
    public Recipe() {
    }

    /**
     * Full constructor for creating a new Recipe with all field values.
     */
    public Recipe(Long id, String title, String content, double rating, int views, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.views = views;
        this.user = user;
    }

    // Standard getter and setter methods for accessing and updating the field
    // values.
    // These methods are crucial for managing the state of Recipe objects and
    // facilitating database interactions through ORM.

    // HashCode, Equals, and ToString methods overridden to provide appropriate
    // behavior for user entity instances.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        Set<Recipe> recipes = this.user.getRecipes();
        if (!recipes.contains(this)) {
            recipes.add(this);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        Recipe other = (Recipe) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
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
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Recipe [id=" + id + ", title=" + title + ", content=" + content + ", user=" + user + "]";
    }

    /**
     * Compares title length and first letter.
     * 
     * @param o another user's age to compare to.
     * @return first compare with length, if equals, compare first letter.
     */
    @Override
    public int compareTo(String o) {
        if (title.length() > o.length()) {
            return 1;
        } else if (title.length() < o.length()) {
            return -1;
        } else {
            if (title.toCharArray()[0] > o.toCharArray()[0]) {
                return 1;
            } else if (title.toCharArray()[0] < o.toCharArray()[0]) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
