package com.example.FoodCoash.favorite.presenter;


import com.example.FoodCoash.model.DTO.MealsItem;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface FavourPresenterIN {
    public Single<List<MealsItem>> getFavMealList();
    public void deleteMeal(MealsItem mealsItem);
}