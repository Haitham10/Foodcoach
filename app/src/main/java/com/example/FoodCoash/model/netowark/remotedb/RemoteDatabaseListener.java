package com.example.FoodCoash.model.netowark.remotedb;


import com.example.FoodCoash.model.DTO.MealsItem;
import com.example.FoodCoash.model.DTO.WeekPlan;

import io.reactivex.rxjava3.core.Completable;

public interface RemoteDatabaseListener {
    Completable insertToFavorite(MealsItem mealsItem);
    Completable insertToWeekPlan(WeekPlan weekPlan);
    void deleteFromWeekPlane(WeekPlan weekPlan);
    void deleteFromFavorite(MealsItem mealsItem);
}
