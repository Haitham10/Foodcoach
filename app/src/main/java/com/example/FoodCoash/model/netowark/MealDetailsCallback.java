package com.example.FoodCoash.model.netowark;

import com.example.FoodCoash.model.DTO.MealsDetail;

import java.util.List;

public interface MealDetailsCallback {
    public void onSuccessResult(List<MealsDetail> mealsDetailList);
    public void onFailureResult(String errorMsg);
}
