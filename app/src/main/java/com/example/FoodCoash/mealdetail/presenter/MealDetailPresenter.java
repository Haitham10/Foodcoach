package com.example.FoodCoash.mealdetail.presenter;


import com.example.FoodCoash.mealdetail.view.MealDetailView;
import com.example.FoodCoash.model.DTO.MealsItem;
import com.example.FoodCoash.model.DTO.WeekPlan;
import com.example.FoodCoash.model.MealRepositoryIN;

public class MealDetailPresenter implements MealDetailPresenterView{

    private MealDetailView mealDetailView;
    private MealRepositoryIN mealRepositoryIN;

    public MealDetailPresenter(MealDetailView mealDetailView, MealRepositoryIN mealRepositoryIN) {
        this.mealDetailView = mealDetailView;
        this.mealRepositoryIN = mealRepositoryIN;
    }

    @Override
    public void SetClickedItemData(WeekPlan selectedDate) {
        mealRepositoryIN.insertWeekPlanMeal(selectedDate);
    }

    @Override
    public void addToFav(MealsItem mealsItem) {
        mealRepositoryIN.insertMeal(mealsItem);
    }



}
