package com.microservices.foodcatalogue.service;

import com.microservices.foodcatalogue.dto.FoodCataloguePage;
import com.microservices.foodcatalogue.dto.FoodItemDTO;
import com.microservices.foodcatalogue.dto.Restaurant;
import com.microservices.foodcatalogue.entity.FoodItem;
import com.microservices.foodcatalogue.mapper.FoodItemMapper;
import com.microservices.foodcatalogue.repo.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {

    @Autowired
     FoodItemRepository foodItemRepository;
    @Autowired
    RestTemplate restTemplate;

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem savedFoodItem = foodItemRepository.save(FoodItemMapper.INSTANCE.getFoodItemFromFoodItemDTO(foodItemDTO));
        return FoodItemMapper.INSTANCE.getFoodItemDTOFromFoodItem(savedFoodItem);

    }

    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restauranId) {
        //fetch food itm list
        List<FoodItem> foodItemList = fetchFoodItemList(restauranId);
        // fetch restaurant details by calling Restaurant microservice
        Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMS(restauranId);
        //create the Food Catalogue page
        FoodCataloguePage foodCataloguePage = createFoodCatraloguePage(foodItemList,restaurant);
        return foodCataloguePage;
    }

    private FoodCataloguePage createFoodCatraloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItem(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);
        return  foodCataloguePage;
    }

    private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
     return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantId,Restaurant.class);
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        return foodItemRepository.findByRestaurantId(restaurantId);
    }
}
