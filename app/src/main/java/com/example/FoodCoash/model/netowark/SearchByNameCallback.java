package com.example.FoodCoash.model.netowark;

import com.example.FoodCoash.model.DTO.MealsItem;

import java.util.List;

public interface SearchByNameCallback {
    public void onSuccessResult(List<MealsItem> searchByNameList);
    public void onFailureResult(String errorMsg);
}
