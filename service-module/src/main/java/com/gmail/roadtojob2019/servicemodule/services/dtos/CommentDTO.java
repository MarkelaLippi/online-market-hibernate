package com.gmail.roadtojob2019.servicemodule.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private Date date;
    private String content;
    private String userName;
    private String userLastName;
}
