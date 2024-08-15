package com.example.FoodCoash.categories.view;


import com.example.FoodCoash.model.DTO.ListsDetails;

import java.util.List;

public interface CategoryView {
    public void getCategoryData(List<ListsDetails> categoryDetailsList);
    public void CategoryErrorMsg(String error);
}
