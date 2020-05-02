package com.gmail.roadtojob2019.repositorymodule.models;

import com.gmail.roadtojob2019.repositorymodule.converters.UnitConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "unit")
    @Convert(converter = UnitConverter.class)
    private Unit unit;
    @Column(name = "amount")
    private Long amount;
}
