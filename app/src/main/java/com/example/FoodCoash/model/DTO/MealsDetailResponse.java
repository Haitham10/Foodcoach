package com.example.FoodCoash.model.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MealsDetailResponse implements Serializable {

	@SerializedName("meals")
	private List<MealsDetail> meals;

	public List<MealsDetail> getMeals(){
		return meals;
	}
}