package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.repositorymodule.models.Item;
import com.gmail.roadtojob2019.repositorymodule.repositories.ItemRepository;
import com.gmail.roadtojob2019.servicemodule.services.ItemService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ItemDto;
import com.gmail.roadtojob2019.servicemodule.services.exception.OnlineMarketSuchItemNotFoundException;
import com.gmail.roadtojob2019.servicemodule.services.mappers.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public Page<ItemDto> getPageOfItemsSortedByName(final int pageNumber, final int pageSize) {
        final Sort.Direction sortDirection = Sort.Direction.ASC;
        final String sortField = "name";
        final Pageable pageParameters = PageRequest.of(pageNumber, pageSize, sortDirection, sortField);
        final Page<Item> page = itemRepository.findAll(pageParameters);
        return page.map(itemMapper::itemToItemDto);
    }

    @Override
    public void deleteItemById(final Long itemID) {
        itemRepository.deleteById(itemID);
    }

    @Override
    public ItemDto getItemById(final Long itemID) throws OnlineMarketSuchItemNotFoundException {
        final Item item = itemRepository.findById(itemID)
                .orElseThrow(() -> new OnlineMarketSuchItemNotFoundException("Item with id = " + itemID + " was not found"));
        return itemMapper.itemToItemDto(item);
    }

    @Override
    public List<ItemDto> getAllItems() {
        final List<Item> items = itemRepository.findAll();
        return toItemDtoList(items);
    }

    private List<ItemDto> toItemDtoList(final List<Item> items) {
        return items
                .stream()
                .map(itemMapper::itemToItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long addItem(ItemDto itemDto) {
        final Item item = itemMapper.itemDtoToItem(itemDto);
        final Item addedItem = itemRepository.save(item);
        return addedItem.getId();
    }
}
