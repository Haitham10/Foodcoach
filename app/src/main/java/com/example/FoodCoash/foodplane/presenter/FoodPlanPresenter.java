package com.example.FoodCoash.foodplane.presenter;


import android.util.Log;

import com.example.FoodCoash.model.DTO.WeekPlan;
import com.example.FoodCoash.model.MealRepositoryIN;
import com.example.FoodCoash.foodplane.view.FoodPlanMealView;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class FoodPlanPresenter implements WeekPresenterIN {

    private FoodPlanMealView foodPlanMealView;
    private MealRepositoryIN mealRepositoryIN;

    public FoodPlanPresenter(FoodPlanMealView foodPlanMealView, MealRepositoryIN mealRepositoryIN){
        this.foodPlanMealView = foodPlanMealView;
        this.mealRepositoryIN = mealRepositoryIN;
    }
    @Override
    public Single<List<WeekPlan>> getWeekPlanMealList() {
        return mealRepositoryIN.getWeekPlanMeals();
    }

    @Override
    public void deleteMeal(WeekPlan weekPlan) {
        mealRepositoryIN.deleteWeekPlanMeal(weekPlan);
        Log.i("TAG", "deleteMeal: Presenter");
    }

    @Override
    public Single<List<WeekPlan>> getMealsForDate(String date) {
        return mealRepositoryIN.getMealsForDate(date);
    }

}
