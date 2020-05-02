package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.servicemodule.services.OrderService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    String getPageOfOrdersSortedByDateIsOk(@RequestParam final Optional<Integer> pageNumber,
                                           @RequestParam final Optional<Integer> pageSize,
                                           final Model model
                                           ){
        final int currentPageNumber = pageNumber.orElse(1);
        final int currentPageSize = pageSize.orElse(10);
        final Page<OrderDto> orders = orderService.getPageOfOrdersSortedByDate(currentPageNumber, currentPageSize);
        model.addAttribute("orders", orders);
        return "orders";
    }
}
