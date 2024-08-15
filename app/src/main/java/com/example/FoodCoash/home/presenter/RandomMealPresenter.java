package com.example.FoodCoash.home.presenter;


import com.example.FoodCoash.home.view.RandomMealView;
import com.example.FoodCoash.model.DTO.MealsItem;
import com.example.FoodCoash.model.MealRepositoryIN;
import com.example.FoodCoash.model.netowark.RandomMealItemCallback;

import java.util.List;

public class RandomMealPresenter implements RandomMealPresenterView, RandomMealItemCallback {

    private final RandomMealView randomMealView;
    private final MealRepositoryIN mealRepositoryIN;

    public RandomMealPresenter(RandomMealView randomMealView, MealRepositoryIN mealRepositoryIN){
        this.randomMealView = randomMealView;
        this.mealRepositoryIN = mealRepositoryIN;
    }

    @Override
    public void getMeal() {
        mealRepositoryIN.RandomMealNetworkCall(this);
    }

    @Override
    public void onSuccessResult(List<MealsItem> mealsItemList) {
            randomMealView.showData(mealsItemList);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        randomMealView.showErrorMsg(errorMsg);
    }
}
