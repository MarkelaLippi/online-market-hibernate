package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.servicemodule.services.ItemService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ItemDto;
import com.gmail.roadtojob2019.servicemodule.services.exception.OnlineMarketSuchItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items")
    @ResponseStatus(HttpStatus.OK)
    String getPageOfItemsSortedByName(@RequestParam final Optional<Integer> pageNumber,
                                      @RequestParam final Optional<Integer> pageSize,
                                      final Model model){
        final Integer currentPageNumber = pageNumber.orElse(1);
        final Integer currentPageSize = pageSize.orElse(10);
        final Page<ItemDto> items = itemService.getPageOfItemsSortedByName(currentPageNumber, currentPageSize);
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/items/delete/{itemID}")
    @ResponseStatus(HttpStatus.OK)
    String deleteItemById(@PathVariable final Long itemID){
        itemService.deleteItemById(itemID);
        return "forward:/items";
    }

    @GetMapping("/items/{itemID}")
    @ResponseStatus(HttpStatus.OK)
    String getItemById(@PathVariable final Long itemID) throws OnlineMarketSuchItemNotFoundException {
        itemService.getItemById(itemID);
        return "forward:/items";
    }
}
