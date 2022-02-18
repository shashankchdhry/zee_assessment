package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dto.Food;
import com.learning.exception.FoodTypeNotFoundException;
import com.learning.exception.IdNotFoundException;
import com.learning.repo.FoodRepository;
import com.learning.service.FoodService;

@Service // using this we get the singleton object
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepository foodRepository;

	@Override
	public Food add(Food food) throws IdNotFoundException {
		boolean status = foodRepository.existsByFoodId(food.getFoodId());
		if (status) {
			throw new IdNotFoundException("Sorry food not found");
		}
		Food food2 = foodRepository.save(food);
		return food2;
	}

//update
	@Override
	public Food update(Food food) throws IdNotFoundException {
		return foodRepository.save(food);
	}

//get food by id
	@Override
	public Food getFoodById(int id) throws IdNotFoundException {
		Optional<Food> optional = foodRepository.findById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("sorry food not found");
		} else {
			return optional.get();
		}
	}

// get all foods
	@Override
	public Food[] getAllFoods() {
		List<Food> list = foodRepository.findAll();
		Food[] array = new Food[list.size()];
		return list.toArray(array);
	}

//delete food by id
	@Override
	public String deleteFoodById(int id) throws IdNotFoundException {
		Food optional;
		try {
			optional = this.getFoodById(id);
			if (optional != null) {
				throw new IdNotFoundException("food not found");
			} else {
				foodRepository.deleteById(id);
				return "food deleted";
			}
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IdNotFoundException(e.getMessage());
		}
	}

//get all food details
	@Override
	public Optional<List<Food>> getAllFoodDetails() {
		return Optional.ofNullable(foodRepository.findAll());
	}

	@SuppressWarnings("null")
	@Override
	public Food[] getAllFoodsByFoodType(String foodType) throws FoodTypeNotFoundException {
		List<Food> foodItems = foodRepository.findAll();
		List<Food> temp = new ArrayList<Food>();
		for (Food foodItems2 : foodItems) {
			if (foodItems2.getFoodType().toString().equals(foodType)) {
				temp.add(foodItems2);
			}
		}
		if (temp.isEmpty()) {
			throw new FoodTypeNotFoundException("Sorry Food Type Not Found");
		}
		Food[] array = new Food[temp.size()];
		return temp.toArray(array);
	}

}