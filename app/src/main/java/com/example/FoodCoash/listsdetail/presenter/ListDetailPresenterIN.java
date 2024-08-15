package com.example.FoodCoash.listsdetail.presenter;


import com.example.FoodCoash.model.DTO.MealsDetail;
import com.example.FoodCoash.model.DTO.MealsDetailResponse;

import io.reactivex.rxjava3.core.Single;

public interface ListDetailPresenterIN {
    public Single<MealsDetailResponse> getMealDetail(String category);
    public void addToFav(MealsDetail mealsDetail);
}
