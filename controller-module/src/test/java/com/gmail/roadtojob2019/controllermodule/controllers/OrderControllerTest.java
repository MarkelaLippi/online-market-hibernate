package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.repositorymodule.models.Order;
import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.repositorymodule.repositories.OrderRepository;
import com.gmail.roadtojob2019.servicemodule.services.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestService testService;

    @Autowired
    @MockBean
    private OrderRepository orderRepository;

    @Test
    void testGetPageOfOrdersSortedByDateIsOk() throws Exception {
        //given
        final int pageNumber = 1;
        final int pageSize = 10;
        final Sort.Direction sortDirection = Sort.Direction.DESC;
        final String sortField = "date";
        final Pageable pageParameters = PageRequest.of(pageNumber, pageSize, sortDirection, sortField);
        final User user = testService.getUser();
        final Order order = testService.getOrder(user);
        final List<Order> orders = List.of(order);
        final long totalAmountOfOrders = orders.size();
        final PageImpl<Order> page = new PageImpl<>(orders, pageParameters, totalAmountOfOrders);
        willReturn(page).given(orderRepository).findAll(pageParameters);
        //when
        mockMvc.perform(get("/orders"))
                //then
                .andExpect(status().isOk());
        verify(orderRepository, times(1)).findAll(pageParameters);
    }
}
