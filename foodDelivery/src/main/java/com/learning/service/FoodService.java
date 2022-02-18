//user service interface

package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.dto.Food;
import com.learning.exception.FoodTypeNotFoundException;
import com.learning.exception.IdNotFoundException;

public interface FoodService {

	public Food add(Food food) throws IdNotFoundException;

	public Food update(Food food) throws IdNotFoundException;

	public Food getFoodById(int id) throws IdNotFoundException;

	public Food[] getAllFoods();

	public String deleteFoodById(int id) throws IdNotFoundException;

	public Optional<List<Food>> getAllFoodDetails();

	Food[] getAllFoodsByFoodType(String foodType) throws FoodTypeNotFoundException;


}