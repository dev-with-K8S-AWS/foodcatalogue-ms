package com.microservices.foodcatalogue.service;
import com.microservices.foodcatalogue.dto.FoodCataloguePage;
import com.microservices.foodcatalogue.dto.FoodItemDTO;
import com.microservices.foodcatalogue.dto.Restaurant;
import com.microservices.foodcatalogue.entity.FoodItem;
import com.microservices.foodcatalogue.mapper.FoodItemMapper;
import com.microservices.foodcatalogue.repo.FoodItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FoodCatalogueServiceTest {

    @Mock
    FoodItemRepository foodItemRepository;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    FoodCatalogueService foodCatalogueService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddFoodItem(){
        FoodItem foodItem = new FoodItem();
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        when(foodItemRepository.save(foodItem)).thenReturn(foodItem);
        FoodItemDTO foodItemDTOAcual = foodCatalogueService.addFoodItem(foodItemDTO);
        assertEquals(FoodItemMapper.INSTANCE.getFoodItemDTOFromFoodItem(foodItem),foodItemDTOAcual);
        verify(foodItemRepository,times(1)).save(foodItem);
    }

    @Test
    public void testFetchFoodCataloguePageDetails(){
        Integer restaurantId = 1;
        List<FoodItem> foodItemList = new ArrayList<>();
        Restaurant restaurant = new Restaurant();
        when(foodItemRepository.findByRestaurantId(restaurantId)).thenReturn(foodItemList);
        when(restTemplate.getForObject(anyString(),eq(Restaurant.class))).thenReturn(restaurant);

        FoodCataloguePage result = foodCatalogueService.fetchFoodCataloguePageDetails(restaurantId);

        assertEquals(foodItemList,result.getFoodItem());
        assertEquals(restaurant,result.getRestaurant());

        verify(foodItemRepository,times(1)).findByRestaurantId(restaurantId);
        verify(restTemplate,times(1)).getForObject(anyString(),eq(Restaurant.class));
    }


}
