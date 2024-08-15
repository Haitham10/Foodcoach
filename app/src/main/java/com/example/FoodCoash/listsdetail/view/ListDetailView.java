package com.example.FoodCoash.listsdetail.view;


import com.example.FoodCoash.model.DTO.MealsDetail;

import java.util.List;

public interface ListDetailView {
    public void showMealDetailData(List<MealsDetail> mealsItem);
    public void addMealToFav(MealsDetail mealsItem);
    public void showMealDetailErrorMsg(String error);
}
