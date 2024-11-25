package com.microservices.foodcatalogue.repo;

import com.microservices.foodcatalogue.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem,Integer> {
    List<FoodItem> findByRestaurantId(Integer restauranId);
}
