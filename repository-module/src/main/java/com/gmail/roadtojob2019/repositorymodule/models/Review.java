package com.gmail.roadtojob2019.repositorymodule.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "content")
    private String content;
    @Column(name = "date")
    private Date date;
    @Column(name = "status")
    private boolean isActive;
    @Column(name = "user_id")
    private Long user_id;

    public Review() {
    }

    public Review(Long id, String content, Date date, boolean isActive, Long user_id) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.isActive = isActive;
        this.user_id = user_id;
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return isActive == review.isActive &&
                id.equals(review.id) &&
                content.equals(review.content) &&
                date.equals(review.date) &&
                user_id.equals(review.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, date, isActive, user_id);
    }
}
