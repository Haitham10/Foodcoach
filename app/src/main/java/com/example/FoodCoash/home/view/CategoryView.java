package com.example.FoodCoash.home.view;


import com.example.FoodCoash.model.DTO.CategoriesItem;

import java.util.List;

public interface CategoryView {
    public void showCategoryData(List<CategoriesItem> categoriesItemList);
    public void showErrorMsgCategory(String error);
}
