package com.gmail.roadtojob2019.servicemodule.services.mappers;

import com.gmail.roadtojob2019.repositorymodule.models.Item;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface ItemMapper {
    @Mappings({
            @Mapping(target = "identifier", expression = "java( item.getIdentifier().toString() )")
    })
    ItemDto itemToItemDto(Item item);
}
