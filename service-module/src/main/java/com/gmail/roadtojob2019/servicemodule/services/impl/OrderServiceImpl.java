package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.repositorymodule.models.Order;
import com.gmail.roadtojob2019.repositorymodule.repositories.OrderRepository;
import com.gmail.roadtojob2019.servicemodule.services.OrderService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.OrderDto;
import com.gmail.roadtojob2019.servicemodule.services.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Page<OrderDto> getPageOfOrdersSortedByDate(final int pageNumber, final int pageSize) {
        final Sort.Direction sortDirection = Sort.Direction.DESC;
        final String sortField="date";
        final PageRequest pageParameters = PageRequest.of(pageNumber, pageSize, sortDirection, sortField);
        final Page<Order> page = orderRepository.findAll(pageParameters);
        return page.map(orderMapper::orderToOrderDto);
    }
}
