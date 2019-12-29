package com.gmail.roadtojob2019.servicemodule.services.dtos;

//import com.gmail.roadtojob2019.servicemodule.services.validators.UniqueEmail;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//@UniqueEmail
public class UserDTO {
    private Long id;
    private String lastName;
    private String name;
    private String middleName;
    private String email;
    private String password;
    private String role;
    private Boolean isActive;
    private Set<ReviewDTO> reviewDTOs=new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(Long id, String lastName, String name, String middleName, String email, String password, String role, Boolean isActive, Set<ReviewDTO> reviewDTOS) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.middleName = middleName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
        this.reviewDTOs = reviewDTOS;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<ReviewDTO> getReviewDTOs() {
        return reviewDTOs;
    }

    public void setReviewDTOs(Set<ReviewDTO> reviewDTOs) {
        this.reviewDTOs = reviewDTOs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id.equals(userDTO.id) &&
                lastName.equals(userDTO.lastName) &&
                name.equals(userDTO.name) &&
                middleName.equals(userDTO.middleName) &&
                email.equals(userDTO.email) &&
                password.equals(userDTO.password) &&
                role.equals(userDTO.role) &&
                isActive.equals(userDTO.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, name, middleName, email, password, role, isActive);
    }
}
