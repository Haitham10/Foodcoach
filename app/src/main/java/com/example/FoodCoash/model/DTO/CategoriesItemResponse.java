package com.example.FoodCoash.model.DTO;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CategoriesItemResponse {

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	public List<CategoriesItem> getCategories(){
		return categories;
	}
}