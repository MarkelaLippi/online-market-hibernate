package com.gmail.roadtojob2019.servicemodule.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private LocalDateTime date;
    private String status;
    private Set<ItemDto> items=new LinkedHashSet<>();
}
