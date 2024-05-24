package com.foodrecipes.webapp.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

// Importing JPA annotations and other necessary Java utilities.
// No javax in current version
import jakarta.persistence.*;

/**
 * Represents a user entity in the application.
 * The @Entity annotation marks this class as a JPA entity, meaning it will be
 * mapped to a table in the database.
 */
@Entity
// Specifies the table in the database to which this entity will be mapped.
@Table(name = "users")
public class User implements Comparable<Integer> {

    // Attributes of the User class with JPA annotations to define table mapping,
    // constraints, and ID generation strategy.

    @Id // Marks this field as the primary key of the user table.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the way the ID is generated, using the database
                                                        // identity column.
    private Long id; // Unique identifier for the user.

    @Column(name = "username", nullable = false, length = 50) // Maps this field to the specified column with
                                                              // constraints.
    private String name; // Username, must not be null and has a max length of 50 characters.

    @Column(name = "password", nullable = false) // Marks this field as a column and it must not be null.
    private String password; // User's password.

    @Column(name = "nickname", length = 50) // Specifies this field has a max length of 50 characters.
    private String nickName; // User's nickname.

    @Column(name = "avatar_url") // Optional field without constraints.
    private String avatarUrl; // URL of the user's avatar image.

    @Column(name = "email", unique = true) // Marks the email as unique within the database.
    private String email; // User's email address, must be unique.

    @Column(name = "birthdate") // No specific constraints, defaults apply.
    private String birthDate; // User's birthdate.

    @Column(name = "salt") // store specific salt value for authorization
    private String salt;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Recipe> recipes;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Comment> comments;

    /**
     * No-argument constructor required by JPA for creating instances.
     */
    public User() {
    }

    /**
     * Full constructor for creating a new User with all field values.
     */
    public User(final Long id, final String name, final String password, final String nickName,
            final String avatarUrl, final String email, final String birthDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.email = email;
        this.birthDate = birthDate;
    }

    // Standard getter and setter methods for accessing and updating the field
    // values.
    // These methods are crucial for managing the state of User objects and
    // facilitating database interactions through ORM.

    // HashCode, Equals, and ToString methods overridden to provide appropriate
    // behavior for user entity instances.

    /**
     * Compares this user's age to another age.
     * As user does not have an age field, it is calculated using LocalDate,
     * Period and DateTimeFormatter classes.
     * Useful for sorting collections of users based on age.
     * 
     * @param o another user's age to compare to.
     * @return standard compareTo results: negative if this age is less, zero if
     *         equal, positive if greater.
     */
    @Override
    public int compareTo(Integer o) {
        return Integer.compare(Period.between(LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd.MM.yyyy")), LocalDate.now()).getYears(), o);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    /**
     * Set one recipe in a user
     * 
     * @param recipe
     */
    public void setRecipe(Recipe recipe) {
        if (recipes == null) {
            this.recipes = new HashSet<>();
        }
        recipes.add(recipe);
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void setComment(Comment comment) {
        if (comment == null) {
            this.comments = new HashSet<>();
        }
        comments.add(comment);
    }
    // Overriding the hashCode, equals, and toString methods for proper value
    // comparison and output formatting

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
        result = prime * result + ((avatarUrl == null) ? 0 : avatarUrl.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (nickName == null) {
            if (other.nickName != null)
                return false;
        } else if (!nickName.equals(other.nickName))
            return false;
        if (avatarUrl == null) {
            if (other.avatarUrl != null)
                return false;
        } else if (!avatarUrl.equals(other.avatarUrl))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (birthDate == null) {
            if (other.birthDate != null)
                return false;
        } else if (!birthDate.equals(other.birthDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password + ", nickName=" + nickName
                + ", avatarUrl=" + avatarUrl + ", email=" + email + ", birthDate=" + birthDate + "]";
    }

}
