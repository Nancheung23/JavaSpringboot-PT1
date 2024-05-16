package com.foodrecipes.webapp.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

// Importing JPA annotations and other necessary Java utilities.
import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "time", nullable = false)
    private LocalDateTime ldt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_id", nullable = false)
    @JsonBackReference
    private Recipe recipe;

    /**
     * No-argument constructor required by JPA for creating instances.
     */
    public Comment() {

    }

    /**
     * Constructor
     * 
     * @param id
     * @param content
     * @param ldt
     * @param user
     * @param recipe
     */
    public Comment(Long id, String content, LocalDateTime ldt, User user, Recipe recipe) {
        this.id = id;
        this.content = content;
        this.ldt = ldt;
        this.user = user;
        this.recipe = recipe;
    }

    // Standard getter and setter methods for accessing and updating the field
    // values.
    // These methods are crucial for managing the state of Comment objects and
    // facilitating database interactions through ORM.

    // HashCode, Equals, and ToString methods overridden to provide appropriate
    // behavior for user entity instances.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getLdt() {
        return ldt;
    }

    public void setLdt(LocalDateTime ldt) {
        this.ldt = ldt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((ldt == null) ? 0 : ldt.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((recipe == null) ? 0 : recipe.hashCode());
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
        Comment other = (Comment) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (ldt == null) {
            if (other.ldt != null)
                return false;
        } else if (!ldt.equals(other.ldt))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        if (recipe == null) {
            if (other.recipe != null)
                return false;
        } else if (!recipe.equals(other.recipe))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Comment [id=" + id + ", content=" + content + ", ldt=" + ldt + ", user=" + user + ", recipe=" + recipe
                + "]";
    }
}
