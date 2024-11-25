package com.microservices.foodcatalogue.controller;

import com.microservices.foodcatalogue.dto.FoodCataloguePage;
import com.microservices.foodcatalogue.dto.FoodItemDTO;
import com.microservices.foodcatalogue.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodCatalogue")
public class FoodCatalogueController {

    @Autowired
     FoodCatalogueService foodCatalogueService;

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO){
       FoodItemDTO savedFoodItemDTO =  foodCatalogueService.addFoodItem(foodItemDTO);
        return new ResponseEntity<>(savedFoodItemDTO, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantAndFoodItemsById/{restauranId}")
    public ResponseEntity<FoodCataloguePage> fetchFoodCataloguePageDetails(@PathVariable Integer restauranId){
        FoodCataloguePage foodCataloguePage = foodCatalogueService.fetchFoodCataloguePageDetails(restauranId);
        return new ResponseEntity<>(foodCataloguePage,HttpStatus.OK);

    }
}
