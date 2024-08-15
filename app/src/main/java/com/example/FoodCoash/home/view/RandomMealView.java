package com.example.FoodCoash.home.view;


import com.example.FoodCoash.model.DTO.MealsItem;

import java.util.List;

public interface RandomMealView {
    public void showData(List<MealsItem> mealsItemList);
    public void showErrorMsg(String error);
}
