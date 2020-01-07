package com.gmail.roadtojob2019.servicemodule.services.dtos;

import java.util.Date;
import java.util.Objects;

public class CommentDTO {
    private Long id;
    private Date date;
    private String content;
    private UserDTO userDTO;
    private ArticleDTO articleDTO;

    public CommentDTO() {
    }

    public CommentDTO(Long id, Date date, String content, UserDTO userDTO, ArticleDTO articleDTO) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.userDTO = userDTO;
        this.articleDTO = articleDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public ArticleDTO getArticleDTO() {
        return articleDTO;
    }

    public void setArticleDTO(ArticleDTO articleDTO) {
        this.articleDTO = articleDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDTO that = (CommentDTO) o;
        return id.equals(that.id) &&
                date.equals(that.date) &&
                content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, content);
    }
}
