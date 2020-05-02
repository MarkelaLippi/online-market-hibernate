package com.gmail.roadtojob2019.servicemodule.services;

import com.gmail.roadtojob2019.servicemodule.services.dtos.OrderDto;
import org.springframework.data.domain.Page;

public interface OrderService {

    Page<OrderDto> getPageOfOrdersSortedByDate(final int pageNumber, final int pageSize);
}
