package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.repositorymodule.models.Item;
import com.gmail.roadtojob2019.repositorymodule.repositories.ItemRepository;
import com.gmail.roadtojob2019.servicemodule.services.ItemService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ItemDto;
import com.gmail.roadtojob2019.servicemodule.services.mappers.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
}
