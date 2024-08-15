package com.example.FoodCoash.model.netowark;

import com.example.FoodCoash.model.DTO.MealsItem;

import java.util.List;

public interface RandomMealItemCallback {
    public void onSuccessResult(List<MealsItem> mealsItem);
    public void onFailureResult(String errorMsg);
}
