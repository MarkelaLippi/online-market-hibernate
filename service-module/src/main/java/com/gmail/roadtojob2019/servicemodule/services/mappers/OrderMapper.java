package com.gmail.roadtojob2019.servicemodule.services.mappers;

import com.gmail.roadtojob2019.repositorymodule.models.Item;
import com.gmail.roadtojob2019.repositorymodule.models.Order;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ItemDto;
import com.gmail.roadtojob2019.servicemodule.services.dtos.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto orderToOrderDto(Order order);

    @Mappings({
            @Mapping(target = "identifier", expression = "java( item.getIdentifier().toString() )")
    })
    ItemDto itemToItemDto(Item item);

}
