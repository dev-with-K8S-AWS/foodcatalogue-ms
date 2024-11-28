package com.microservices.foodcatalogue.controller;

import com.microservices.foodcatalogue.dto.FoodCataloguePage;
import com.microservices.foodcatalogue.dto.FoodItemDTO;
import com.microservices.foodcatalogue.service.FoodCatalogueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FoodCatalogueControllerTest {

    @Mock
    FoodCatalogueService foodCatalogueService;

    @InjectMocks
    FoodCatalogueController foodCatalogueController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddFoodItem(){

        FoodItemDTO foodItemDTO = new FoodItemDTO();
        when(foodCatalogueService.addFoodItem(any(FoodItemDTO.class))).thenReturn(foodItemDTO);
        ResponseEntity<FoodItemDTO> responseEntity = foodCatalogueController.addFoodItem(foodItemDTO);
        assertEquals(foodItemDTO,responseEntity.getBody());
        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        verify(foodCatalogueService,times(1)).addFoodItem(foodItemDTO);
    }


    @Test
    public void testFetchFoodCataloguePageDetails(){
        Integer restauranId = 1;
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        when(foodCatalogueService.fetchFoodCataloguePageDetails(restauranId)).thenReturn(foodCataloguePage);
        ResponseEntity<FoodCataloguePage> responseEntity = foodCatalogueController.fetchFoodCataloguePageDetails(restauranId);
        assertEquals(foodCataloguePage,responseEntity.getBody());
        assert responseEntity.getStatusCode() == HttpStatus.OK;
        verify(foodCatalogueService,times(1)).fetchFoodCataloguePageDetails(restauranId);
    }

}
