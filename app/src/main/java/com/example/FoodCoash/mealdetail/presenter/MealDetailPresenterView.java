package com.example.FoodCoash.mealdetail.presenter;


import com.example.FoodCoash.model.DTO.MealsItem;
import com.example.FoodCoash.model.DTO.WeekPlan;

public interface MealDetailPresenterView {
    public void SetClickedItemData(WeekPlan selectedDate);
    public void addToFav(MealsItem mealsItem);
}
