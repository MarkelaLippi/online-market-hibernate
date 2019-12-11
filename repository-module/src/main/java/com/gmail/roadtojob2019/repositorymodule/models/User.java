package com.gmail.roadtojob2019.repositorymodule.models;

import com.gmail.roadtojob2019.repositorymodule.converters.RoleConverter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String name;
    @Column(name = "middle_name")
    private String middleName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    @Convert(converter = RoleConverter.class)
    private Role role;
    @Column(name = "is_active")
    private boolean isActive;

    public User() {
    }

    public User(Long id, String lastname, String name, String middlename, String email, String password, Role role, boolean isactive) {
        this.id = id;
        this.lastName = lastname;
        this.name = name;
        this.middleName = middlename;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = isactive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isActive == user.isActive &&
                id.equals(user.id) &&
                lastName.equals(user.lastName) &&
                name.equals(user.name) &&
                middleName.equals(user.middleName) &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, name, middleName, email, password);
    }
}
