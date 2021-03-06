package com.gmail.roadtojob2019.servicemodule.services;

import com.gmail.roadtojob2019.servicemodule.services.dtos.ItemDto;
import com.gmail.roadtojob2019.servicemodule.services.exception.OnlineMarketSuchItemNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemService {

    Page<ItemDto> getPageOfItemsSortedByName(final int pageNumber, final int pageSize);

    void deleteItemById(Long itemID);

    ItemDto getItemById(Long itemID) throws OnlineMarketSuchItemNotFoundException;

    List<ItemDto> getAllItems();

    Long addItem(ItemDto itemDto);
}
