package com.foodrecipes.webapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a user entity in the application.
 * The @Entity annotation marks this class as a JPA entity.
 */
@Entity
@Table(name = "users") // Custom table name
public class User implements Comparable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the user

    @Column(name = "username", nullable = false, length = 50)
    private String name; // Username, custom column name and constraints

    @Column(name = "password", nullable = false)
    private String password; // User's password

    @Column(name = "nickname", length = 50)
    private String nickName; // User's nickname

    @Column(name = "avatar_url")
    private String avatarUrl; // URL of the user's avatar image

    @Column(name = "email", unique = true)
    private String email; // User's email address, must be unique

    @Column(name = "age")
    private int age; // User's age

    /**
     * Required no-argument constructor for JPA.
     */
    public User() {
    }

    /**
     * Constructs a new User with all fields initialized.
     */
    public User(final Long id, final String name, final String password, final String nickName,
                final String avatarUrl, final String email, final int age) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.email = email;
        this.age = age;
    }

    // Getter and setter methods for all attributes

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(final String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(final String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    // Overriding the hashCode, equals, and toString methods for proper value comparison and output formatting

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
        result = prime * result + age;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User other = (User) obj;
        return id.equals(other.id) && name.equals(other.name) &&
            password.equals(other.password) && nickName.equals(other.nickName) &&
            avatarUrl.equals(other.avatarUrl) && email.equals(other.email) && age == other.age;
    }

    @Override
    public String toString() {
        return String.format("User [id=%s, name=%s, password=%s, nickName=%s, avatarUrl=%s, email=%s, age=%d]",
                            id, name, password, nickName, avatarUrl, email, age);
    }

    @Override
    public int compareTo(Integer o) {
        return Integer.compare(age, o);
    }
}
