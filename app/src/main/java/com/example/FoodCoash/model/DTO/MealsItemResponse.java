package com.example.FoodCoash.model.DTO;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MealsItemResponse {

	@SerializedName("meals")
	private List<MealsItem> meals;

	public List<MealsItem> getRandomMealList(){
		return meals;
	}
}