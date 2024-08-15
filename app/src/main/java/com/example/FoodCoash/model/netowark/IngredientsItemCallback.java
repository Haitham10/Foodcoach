package com.example.FoodCoash.model.netowark;

import com.example.FoodCoash.model.DTO.IngredientsItem;

import java.util.List;

public interface IngredientsItemCallback {
    public void onSuccessResult(List<IngredientsItem>ingredientsItemList);
    public void onFailuresResult( String errorMsg);
}
