package com.example.FoodCoash.model.netowark;

import com.example.FoodCoash.model.DTO.ListsDetails;

import java.util.List;

public interface IngredientDetailsCallback {
    public void onSuccessResult(List<ListsDetails> categoryDetailsList);
    public void onFailureResult(String errorMsg);
}
