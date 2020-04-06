package com.gmail.roadtojob2019.servicemodule.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String lastName;
    private String name;
    private String middleName;
    private String email;
    private String password;
    private String role;
    private Boolean isActive;
    private Set<ReviewDTO> reviewDTOs = new HashSet<>();

}
