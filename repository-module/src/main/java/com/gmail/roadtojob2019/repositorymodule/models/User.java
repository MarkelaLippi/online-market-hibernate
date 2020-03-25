package com.gmail.roadtojob2019.repositorymodule.models;

import com.gmail.roadtojob2019.repositorymodule.converters.RoleConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "name")
    private String name;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Convert(converter = RoleConverter.class)
    private Role role;
    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews=new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Article> articles=new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments=new HashSet<>();
}
