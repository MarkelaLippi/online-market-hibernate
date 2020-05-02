package com.gmail.roadtojob2019.repositorymodule.models;

import com.gmail.roadtojob2019.repositorymodule.converters.RoleConverter;
import com.gmail.roadtojob2019.repositorymodule.converters.StatusConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "status")
    @Convert(converter = StatusConverter.class)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Item> items = new HashSet<>();
}
