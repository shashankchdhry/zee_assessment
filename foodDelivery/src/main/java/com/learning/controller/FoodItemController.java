package com.learning.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.learning.dto.Food;
import com.learning.exception.IdNotFoundException;
import com.learning.service.FoodService;

@RestController
@RequestMapping("/food")
public class FoodItemController {

	@Autowired
	FoodService foodService;

//adding
	@PostMapping("/add")
	public ResponseEntity<?> addUser(@Valid @RequestBody Food food) throws IdNotFoundException {
		Food result = foodService.add(food);
		System.out.println(result);
		return ResponseEntity.status(201).body(result);
	}

//retrieving specific record
	@JsonCreator
	@GetMapping("/{foodid}")
	public ResponseEntity<?> getFoodById(@PathVariable("foodid") int id) throws IdNotFoundException {
		Food food = foodService.getFoodById(id);
		return ResponseEntity.ok(food);
	}

//retrieving all records
	@GetMapping("/all")
	public ResponseEntity<?> getAllFoods() throws IdNotFoundException {

		Optional<java.util.List<Food>> optional = foodService.getAllFoodDetails();

		if (optional.isEmpty()) {
			Map<String, String> map = new HashMap<>();
			map.put("message", "no food found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);

		}
		return ResponseEntity.ok(optional.get());
	}

//delete food by id
	@DeleteMapping("/delete/{foodid}")
	public ResponseEntity<?> deleteUserById(@PathVariable("id") int id) throws IdNotFoundException, SQLException {
		String result = foodService.deleteFoodById(id);
		Map<String, String> map = new HashMap<>();
		map.put("message", "User deleted successfully");
		return ResponseEntity.status(201).body(result);

	}

//update food by id
	@PutMapping("/update/{foodid}")
	public ResponseEntity<?> update(@PathVariable("foodid") int foodid, @RequestBody Food food)
			throws IdNotFoundException {
		Food result = foodService.update(foodid, food);
		return ResponseEntity.status(201).body(result);
	}

}
