package com.gmail.roadtojob2019.controllermodule.controllers.restcontrollers;

import com.gmail.roadtojob2019.servicemodule.services.ItemService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ItemDto;
import com.gmail.roadtojob2019.servicemodule.services.exception.OnlineMarketSuchItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/items")
@RequiredArgsConstructor
public class RestApiItemController {

    private final ItemService itemService;

    @GetMapping("/{itemID}")
    @ResponseStatus(HttpStatus.OK)
    ItemDto getItemById(@PathVariable final Long itemID) throws OnlineMarketSuchItemNotFoundException {
        return itemService.getItemById(itemID);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Long addItem(@RequestBody final ItemDto itemDto) {
        return itemService.addItem(itemDto);
    }

    @DeleteMapping("/{itemID}")
    @ResponseStatus(HttpStatus.OK)
    void deleteItemById(@PathVariable final Long itemID) {
        itemService.deleteItemById(itemID);
    }
}
