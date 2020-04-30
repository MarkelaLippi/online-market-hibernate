package com.gmail.roadtojob2019.controllermodule.controllers.restcontrollers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.roadtojob2019.repositorymodule.models.Item;
import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.repositorymodule.repositories.ItemRepository;
import com.gmail.roadtojob2019.servicemodule.services.TestService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ItemDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class RestApiItemControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestService testService;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @MockBean
    private ItemRepository itemRepository;

    @Test
    void testGetItemByIdIsOk() throws Exception {
        //given
        final User user = testService.getUser();
        final Item item = testService.getItem(user);
        final Long itemID = item.getId();
        final Optional<Item> requiredItem = Optional.of(item);
        willReturn(requiredItem).given(itemRepository).findById(itemID);
        //when
        final MvcResult mvcResult = mockMvc.perform(get("/api/items/" + itemID))
                //then
                .andExpect(status().isOk())
                .andReturn();
        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final ItemDto actualItem = objectMapper.readValue(contentAsString, ItemDto.class);

        assertEquals(1L, actualItem.getId(), "itemID should be 1");
        assertEquals("newItem", actualItem.getName(), "itemName should be newItem");
        assertEquals("44e128a5-ac7a-4c9a-be4c-224b6bf81b20", actualItem.getIdentifier(),
                "itemIdentifier should be 44e128a5-ac7a-4c9a-be4c-224b6bf81b20");
        assertEquals(BigDecimal.valueOf(12.50), actualItem.getPrice(), "itemPrice should be 12.50");
        verify(itemRepository, times(1)).findById(itemID);
    }
    @Test
    void testGetItemByIdThrowsOnlineMarketSuchItemNotFoundException() throws Exception {
        //given
        final Long itemID = 100L;
        willReturn(Optional.empty()).given(itemRepository).findById(itemID);
        //when
        mockMvc.perform(get("/api/items/" + itemID))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("Item with id = 100 was not found"));
        verify(itemRepository, times(1)).findById(itemID);
    }

    @Test
    void testGetAllItemsIsOk() throws Exception {
        //given
        final User user = testService.getUser();
        final Item item = testService.getItem(user);
        final List<Item> items = List.of(item);
        willReturn(items).given(itemRepository).findAll();
        //when
        final MvcResult mvcResult = mockMvc.perform(get("/api/items"))
                //then
                .andExpect(status().isOk())
                .andReturn();
        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final List<ItemDto> actualItemDtos = objectMapper.readValue(contentAsString, new TypeReference<List<ItemDto>>() {
        });
        assertEquals(1, actualItemDtos.size(), "Size of itemDto list should be 1");
        assertEquals(1, actualItemDtos.get(0).getId(), "itemID should be 1");
        assertEquals("newItem", actualItemDtos.get(0).getName(), "itemName should be newItem");
        assertEquals("44e128a5-ac7a-4c9a-be4c-224b6bf81b20", actualItemDtos.get(0).getIdentifier(),
                "itemIdentifier should be 44e128a5-ac7a-4c9a-be4c-224b6bf81b20");
        assertEquals(BigDecimal.valueOf(12.50), actualItemDtos.get(0).getPrice(), "itemPrice should be 12.50");
        verify(itemRepository, times(1)).findAll();
    }
}
