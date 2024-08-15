package com.example.FoodCoash.foodplane.presenter;


import com.example.FoodCoash.model.DTO.WeekPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface WeekPresenterIN {
    public Single<List<WeekPlan>> getWeekPlanMealList();
    public void deleteMeal(WeekPlan weekPlan);
    Single<List<WeekPlan>> getMealsForDate(String date);
}
