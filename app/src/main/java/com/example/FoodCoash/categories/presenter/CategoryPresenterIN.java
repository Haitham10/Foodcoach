package com.example.FoodCoash.categories.presenter;


import com.example.FoodCoash.model.DTO.ListsDetailsResponse;

import io.reactivex.rxjava3.core.Single;

public interface CategoryPresenterIN {
    public Single<ListsDetailsResponse> getCategory(String category);
}
