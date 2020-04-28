package com.gmail.roadtojob2019.controllermodule.controllers.restcontrollers;

import com.gmail.roadtojob2019.repositorymodule.models.Item;
import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.repositorymodule.repositories.ItemRepository;
import com.gmail.roadtojob2019.servicemodule.services.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class RestApiItemControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestService testService;

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
        mockMvc.perform(get("/api/items/"+itemID))
                //then
                .andExpect(status().isOk())
                .andExpect(content().json("" +
                        "  {\n" +
                        "   \"id\" : 1, \n" +
                        "   \"name\" : \"newItem\",\n" +
                        "   \"identifier\" : \"44e128a5-ac7a-4c9a-be4c-224b6bf81b20\",\n" +
                        "   \"price\" : 12.50\n" +
                        "  }\n"));
        verify(itemRepository, times(1)).findById(itemID);
    }
}
