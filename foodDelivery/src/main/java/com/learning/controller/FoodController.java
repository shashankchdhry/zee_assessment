package com.learning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.Food;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.FoodTypeNotFoundException;
import com.learning.exception.IdNotFoundException;
import com.learning.service.FoodService;

@RestController
@RequestMapping("/api/food")
public class FoodController {

	@Autowired
	FoodService foodService;

	@PostMapping
	@PreAuthorize("hasRole('ADMIN') ")
	public ResponseEntity<?> addFood(@Valid @RequestBody Food food) throws AlreadyExistsException, IdNotFoundException {
		Food result = foodService.add(food);
		System.out.println(result);
		return ResponseEntity.status(201).body(result);
	}

	@GetMapping("/{foodId}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
	public ResponseEntity<?> getFoodById(@PathVariable("foodId") int foodId) throws IdNotFoundException {
		Food food = foodService.getFoodById(foodId);
		return ResponseEntity.status(200).body(food);
	}

	@PutMapping("/update/food")
	@PreAuthorize("hasRole('ADMIN') ")
	public ResponseEntity<?> updateFoodById(@RequestBody Food food) throws IdNotFoundException {
		Food result = foodService.update(food);
		return ResponseEntity.status(200).body(result);
	}

	@GetMapping("/foods")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
	public ResponseEntity<?> getAllFood() {

		Optional<List<Food>> food = foodService.getAllFoodDetails();
		return ResponseEntity.status(200).body(food);
	}

	@GetMapping("/foodType/{foodType}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
	public ResponseEntity<?> getFoodByFoodType(@PathVariable("foodType") String foodType)
			throws FoodTypeNotFoundException {
		Food[] food = foodService.getAllFoodsByFoodType(foodType);
		return ResponseEntity.status(200).body(food);
	}

	@DeleteMapping("/delete/{foodId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUserById(@PathVariable("foodId") int foodId) throws IdNotFoundException {
		String result = foodService.deleteFoodById(foodId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", result);
		return ResponseEntity.status(200).body(map);
	}

}
