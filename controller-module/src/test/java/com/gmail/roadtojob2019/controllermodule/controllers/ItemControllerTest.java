package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.repositorymodule.models.Item;
import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.repositorymodule.repositories.ItemRepository;
import com.gmail.roadtojob2019.servicemodule.services.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestService testService;

    @MockBean
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void testPageOfItemsSortedByNameIsOk() throws Exception {
        //given
        final int pageNumber = 1;
        final int pageSize = 10;
        final String sortField = "name";
        final Sort.Direction sortDirection = Sort.Direction.ASC;
        final Pageable pageParameters = PageRequest.of(pageNumber, pageSize, sortDirection, sortField);
        final User user = testService.getUser();
        final Item item = testService.getItem(user);
        final List<Item> items = List.of(item);
        final int totalAmountOfArticles = items.size();
        final Page<Item> page = new PageImpl<>(items, pageParameters, totalAmountOfArticles);
        willReturn(page).given(itemRepository).findAll(pageParameters);
        //when
        mockMvc.perform(get("/items"))
                //then
                .andExpect(status().isOk());
        verify(itemRepository, times(1)).findAll(pageParameters);
    }

    @Test
    void testDeleteItemByIdIsOk() throws Exception {
        //given
        final Long itemID = 1L;
        willDoNothing().given(itemRepository).deleteById(itemID);
        //when
        mockMvc.perform(get("/items/delete/" + itemID))
                //then
                .andExpect(status().isOk());
        verify(itemRepository, times(1)).deleteById(itemID);
    }

    @Test
    void testGetItemByIdIsOk() throws Exception {
        //given
        final User user = testService.getUser();
        final Item item = testService.getItem(user);
        final Long itemID = item.getId();
        final Optional<Item> requiredItem = Optional.of(item);
        willReturn(requiredItem).given(itemRepository).findById(itemID);
        //when
        mockMvc.perform(get("/items/" + itemID))
                //then
                .andExpect(status().isOk());
        verify(itemRepository, times(1)).findById(itemID);
    }

    @Test
    void testGetItemByIdThrowsOnlineMarketSuchItemNotFoundException() throws Exception {
        //given
        final Long itemID = 100L;
        willReturn(Optional.empty()).given(itemRepository).findById(itemID);
        //when
        mockMvc.perform(get("/items/" + itemID))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("Item with id = " + itemID + " was not found"));
        verify(itemRepository, times(1)).findById(itemID);
    }
}
