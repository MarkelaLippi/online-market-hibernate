package com.gmail.roadtojob2019.servicemodule.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private String description;
    private Date date;
    private String userLastName;
    private String userName;
}
