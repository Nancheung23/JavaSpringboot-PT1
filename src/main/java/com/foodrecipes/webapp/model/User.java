package com.foodrecipes.webapp.model;

// Importing JPA annotations and other necessary Java utilities.
import javax.persistence.*;

/**
 * Represents a user entity in the application.
 * The @Entity annotation marks this class as a JPA entity, meaning it will be mapped to a table in the database.
 */
@Entity
// Specifies the table in the database to which this entity will be mapped.
@Table(name = "users")
public class User implements Comparable<Integer> {

    // Attributes of the User class with JPA annotations to define table mapping, constraints, and ID generation strategy.

    @Id  // Marks this field as the primary key of the user table.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Configures the way the ID is generated, using the database identity column.
    private Long id; // Unique identifier for the user.

    @Column(name = "username", nullable = false, length = 50)  // Maps this field to the specified column with constraints.
    private String name; // Username, must not be null and has a max length of 50 characters.

    @Column(name = "password", nullable = false)  // Marks this field as a column and it must not be null.
    private String password; // User's password.

    @Column(name = "nickname", length = 50)  // Specifies this field has a max length of 50 characters.
    private String nickName; // User's nickname.

    @Column(name = "avatar_url")  // Optional field without constraints.
    private String avatarUrl; // URL of the user's avatar image.

    @Column(name = "email", unique = true)  // Marks the email as unique within the database.
    private String email; // User's email address, must be unique.

    @Column(name = "age")  // No specific constraints, defaults apply.
    private int age; // User's age.

    /**
     * No-argument constructor required by JPA for creating instances.
     */
    public User() {
    }

    /**
     * Full constructor for creating a new User with all field values.
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

    // Standard getter and setter methods for accessing and updating the field values.
    // These methods are crucial for managing the state of User objects and facilitating database interactions through ORM.

    // HashCode, Equals, and ToString methods overridden to provide appropriate behavior for user entity instances.

    /**
     * Compares this user's age to another age.
     * Useful for sorting collections of users based on age.
     * @param o another user's age to compare to.
     * @return standard compareTo results: negative if this age is less, zero if equal, positive if greater.
     */
    @Override
    public int compareTo(Integer o) {
        return Integer.compare(age, o);
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
}
