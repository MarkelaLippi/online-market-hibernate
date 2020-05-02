package com.gmail.roadtojob2019.repositorymodule.repositories;

import com.gmail.roadtojob2019.repositorymodule.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
