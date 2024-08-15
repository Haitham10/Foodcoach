package com.example.FoodCoash.favorite.presenter;


import com.example.FoodCoash.favorite.view.FavourView;
import com.example.FoodCoash.model.DTO.MealsItem;
import com.example.FoodCoash.model.MealRepositoryIN;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class FavPresenter implements FavourPresenterIN {
    private static FavPresenter favPresenter;
    private FavourView favourView;
    private MealRepositoryIN mealRepositoryIN;

    public static synchronized FavPresenter getInstance(FavourView favourView, MealRepositoryIN mealRepositoryIN) {
        if (favPresenter == null) {
            favPresenter = new FavPresenter(favourView, mealRepositoryIN);
        }
        return favPresenter;
    }

    private FavPresenter(FavourView favourView, MealRepositoryIN mealRepositoryIN) {
        this.favourView = favourView;
        this.mealRepositoryIN = mealRepositoryIN;
    }



    @Override
    public Single<List<MealsItem>> getFavMealList() {
        return mealRepositoryIN.getFavoriteMeals();
    }

    @Override
    public void deleteMeal(MealsItem mealsItem) {
        mealRepositoryIN.deleteMeal(mealsItem);
    }
}
