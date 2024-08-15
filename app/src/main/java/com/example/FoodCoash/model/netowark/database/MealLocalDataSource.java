package com.example.FoodCoash.model.netowark.database;

import com.example.FoodCoash.model.DTO.MealsDetail;
import com.example.FoodCoash.model.DTO.MealsItem;
import com.example.FoodCoash.model.DTO.WeekPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface MealLocalDataSource {
    void  insertMealToFavorite(MealsItem mealsItem);
    void deleteMealFromFavorite(MealsItem mealsItem);
    Single<List<MealsItem>> getAllFavoriteStoredMeals();


    void  insertMealDetailToFavorite(MealsDetail mealsDetail);
    void deleteMealDetailFromFavorite(MealsDetail mealsDetail);
    Single<List<MealsDetail>> getAllFavoriteStoredMealsDetail();

    Single<List<WeekPlan>> getWeekPlanMeals();
    void insertWeekPlanMealToCalender(WeekPlan weekPlan);
    void deleteWeekPlanMealFromCalender(WeekPlan weekPlan);
    public Single<List<WeekPlan>> getMealsForDate(String date);

    public void deleteAllTheCalenderList();
    public void deleteAllTheFavoriteList();


}
