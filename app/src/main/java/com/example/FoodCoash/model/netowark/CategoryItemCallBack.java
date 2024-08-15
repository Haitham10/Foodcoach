package com.example.FoodCoash.model.netowark;

import com.example.FoodCoash.model.DTO.CategoriesItem;

import java.util.List;

public interface CategoryItemCallBack {

    public void  onSuccessResult (List<CategoriesItem> categoriesItemList);
    public void onFailureResult(String errorMsg);
}
