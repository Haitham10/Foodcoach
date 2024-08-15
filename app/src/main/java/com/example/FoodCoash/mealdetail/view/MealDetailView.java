package com.example.FoodCoash.mealdetail.view;


import com.example.FoodCoash.model.DTO.MealsItem;

public interface MealDetailView {
    public void showItemDetailData(MealsItem mealsItem);
    public void addToFav(MealsItem mealsItem);
    public void showItemDetailErrorMsg(String error);
}
