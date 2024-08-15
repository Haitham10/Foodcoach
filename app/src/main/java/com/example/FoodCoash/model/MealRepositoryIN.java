package com.example.FoodCoash.model;


import com.example.FoodCoash.model.DTO.ListsDetailsResponse;
import com.example.FoodCoash.model.DTO.MealsDetail;
import com.example.FoodCoash.model.DTO.MealsDetailResponse;
import com.example.FoodCoash.model.DTO.MealsItem;
import com.example.FoodCoash.model.DTO.MealsItemResponse;
import com.example.FoodCoash.model.DTO.WeekPlan;
import com.example.FoodCoash.model.netowark.AreaItemCallback;
import com.example.FoodCoash.model.netowark.CategoryItemCallBack;
import com.example.FoodCoash.model.netowark.IngredientsItemCallback;
import com.example.FoodCoash.model.netowark.RandomMealItemCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface MealRepositoryIN {
    //Remote
    public void RandomMealNetworkCall(RandomMealItemCallback networkCallback);
    public void CategoryNetworkCall(CategoryItemCallBack categoryCallBack);
    public void IngredientsNetworkCall(IngredientsItemCallback ingredientsCallback);
    public void AreasNetworkCall(AreaItemCallback areaMealCallback);
    public Single<ListsDetailsResponse> CategoryDetailsNetworkCall(String category);
    public Single<ListsDetailsResponse> IngredientDetailsNetworkCall(String category);
    public Single<ListsDetailsResponse> AreaDetailsNetworkCall(String category);
    public Single<MealsItemResponse> SearchByNameNetworkCall(String name);
    public Single<MealsDetailResponse> getMealByIdNetworkCall(String name);


    //Local
    public Single<List<MealsItem>> getFavoriteMeals();
    public Single<List<MealsDetail>>getListMealDetails();
    public void deleteMeal(MealsItem mealsItem);
    public void insertMeal(MealsItem mealsItem);
    public void deleteMeal(MealsDetail mealsItem);
    public void insertMeal(MealsDetail mealsItem);

    public Single<List<WeekPlan>> getWeekPlanMeals();
    Single<List<WeekPlan>> getMealsForDate(String date);
    public void deleteWeekPlanMeal( WeekPlan weekPlan);
    public void insertWeekPlanMeal(WeekPlan weekPlan);


    Single<List<MealsItem>> getFavoriteMealsSingle();


    //RemoteDB
    void insertMealRemoteToFavorite(MealsItem mealsItem);
    void insertMealRemoteToWeekPlan(WeekPlan weekPlan);
    void deleteMealRemoteFromFavorite(MealsItem mealsItem);
    void deleteMealRemoteFromWeekPlan(WeekPlan weekPlan);

    public void deleteAllTheCalenderList();
    public void deleteAllTheFavoriteList();

}
