package com.gmail.roadtojob2019.servicemodule.services.dtos;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private String description;
    private Date date;
    private UserDTO userDTO;
    private Set<CommentDTO> commentDTOs;

    public ArticleDTO() {
    }

    public ArticleDTO(Long id, String title, String content, String description, Date date, UserDTO userDTO, Set<CommentDTO> commentDTOs) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.description = description;
        this.date = date;
        this.userDTO = userDTO;
        this.commentDTOs = commentDTOs;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Set<CommentDTO> getCommentDTOs() {
        return commentDTOs;
    }

    public void setCommentDTOs(Set<CommentDTO> commentDTOs) {
        this.commentDTOs = commentDTOs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleDTO that = (ArticleDTO) o;
        return id.equals(that.id) &&
                title.equals(that.title) &&
                content.equals(that.content) &&
                description.equals(that.description) &&
                date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, description, date);
    }
}
