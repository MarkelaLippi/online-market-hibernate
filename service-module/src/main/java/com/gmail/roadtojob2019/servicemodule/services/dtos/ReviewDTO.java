package com.gmail.roadtojob2019.servicemodule.services.dtos;

import java.util.Date;
import java.util.Objects;

public class ReviewDTO {
    private Long id;
    private String content;
    private Date date;
    private boolean isActive;
    private UserDTO userDTO;

    public ReviewDTO() {
    }

    public ReviewDTO(Long id, String content, Date date, boolean isActive, UserDTO userDTO) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.isActive = isActive;
        this.userDTO = userDTO;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewDTO reviewDTO = (ReviewDTO) o;
        return isActive == reviewDTO.isActive &&
                Objects.equals(id, reviewDTO.id) &&
                content.equals(reviewDTO.content) &&
                date.equals(reviewDTO.date) &&
                userDTO.equals(reviewDTO.userDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, date, isActive, userDTO);
    }
}
