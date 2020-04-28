package com.gmail.roadtojob2019.controllermodule.controllers.restcontrollers;

import com.gmail.roadtojob2019.servicemodule.services.ItemService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ItemDto;
import com.gmail.roadtojob2019.servicemodule.services.exception.OnlineMarketSuchItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}