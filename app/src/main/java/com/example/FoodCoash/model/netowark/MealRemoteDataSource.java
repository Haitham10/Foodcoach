package com.example.FoodCoash.model.netowark;

import com.example.FoodCoash.model.DTO.ListsDetailsResponse;
import com.example.FoodCoash.model.DTO.MealsDetailResponse;
import com.example.FoodCoash.model.DTO.MealsItemResponse;

import io.reactivex.rxjava3.core.Single;

public interface MealRemoteDataSource {
    public void RandomMealNetworkCall(RandomMealItemCallback networkCallback);
    public void IngredientsNetworkCall(IngredientsItemCallback ingredientsItemCallback);
    public void AreasNetworkCall(  AreaItemCallback  areaItemCallback );
    public void CategoryNetworkCall(CategoryItemCallBack categoryCallBack);

    public Single<ListsDetailsResponse> CategoryDetailsNetworkCall(String category);
    public Single<ListsDetailsResponse> IngredientDetailsNetworkCall(String category);
    public Single<ListsDetailsResponse> AreaDetailsNetworkCall(String category);
    public Single<MealsItemResponse> searchByNameNetworkCall(String name);
    public Single<MealsDetailResponse> getMealDetailNetworkCall(String name);

}
