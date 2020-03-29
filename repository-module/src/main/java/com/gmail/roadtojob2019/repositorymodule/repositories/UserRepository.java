package com.gmail.roadtojob2019.repositorymodule.repositories;

import com.gmail.roadtojob2019.repositorymodule.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

    void deleteUsersByIdIn(List<Long> usersIDs);
}
