package com.example.FoodCoash.model.netowark.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.FoodCoash.model.DTO.MealsDetail;
import com.example.FoodCoash.model.DTO.MealsItem;
import com.example.FoodCoash.model.DTO.WeekPlan;


@Database(entities = {MealsItem.class, WeekPlan.class , MealsDetail.class}, version = 3)
public abstract class AppDataBase extends RoomDatabase {
    public abstract MealDAO getMealsDAO();
    private static AppDataBase instance = null;
    private MealDAO mealDAO;

    public static synchronized AppDataBase getInstance(Context context){
        if(instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class,"meals.db").build();

        return instance;
    }
}
