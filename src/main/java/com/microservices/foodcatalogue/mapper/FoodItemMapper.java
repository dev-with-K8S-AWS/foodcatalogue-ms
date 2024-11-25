package com.microservices.foodcatalogue.mapper;

import com.microservices.foodcatalogue.dto.FoodItemDTO;
import com.microservices.foodcatalogue.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodItemMapper{

    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

    FoodItem getFoodItemFromFoodItemDTO(FoodItemDTO foodItemDTO);

    FoodItemDTO getFoodItemDTOFromFoodItem(FoodItem foodItem);
}
