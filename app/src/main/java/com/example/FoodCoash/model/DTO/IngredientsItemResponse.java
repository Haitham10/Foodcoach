package com.example.FoodCoash.model.DTO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientsItemResponse {

	@SerializedName("meals")
	private List<IngredientsItem> meals;

	public List<IngredientsItem> getIngredientList(){
		return meals;
	}
}