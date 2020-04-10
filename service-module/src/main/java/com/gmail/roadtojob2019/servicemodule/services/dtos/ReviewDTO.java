package com.gmail.roadtojob2019.servicemodule.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private String content;
    private Date date;
    private boolean isActive;
    private String userLastName;
    private String userName;
    private String userMiddleName;
}
