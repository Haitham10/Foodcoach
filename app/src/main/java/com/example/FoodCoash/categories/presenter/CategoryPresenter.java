package com.example.FoodCoash.categories.presenter;


import com.example.FoodCoash.categories.view.CategoryView;
import com.example.FoodCoash.model.DTO.ListsDetails;
import com.example.FoodCoash.model.DTO.ListsDetailsResponse;
import com.example.FoodCoash.model.MealRepositoryIN;
import com.example.FoodCoash.model.netowark.CategoryDetailsCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class CategoryPresenter implements CategoryPresenterIN, CategoryDetailsCallback {
    private final CategoryView categoryView;
    private final MealRepositoryIN mealRepositoryIN;

    public CategoryPresenter(CategoryView categoryView, MealRepositoryIN mealRepositoryIN) {
        this.categoryView = categoryView;
        this.mealRepositoryIN = mealRepositoryIN;
    }

    @Override
    public Single<ListsDetailsResponse> getCategory(String category) {
        return mealRepositoryIN.CategoryDetailsNetworkCall(category);
    }

    @Override
    public void onSuccessResult(List<ListsDetails> categoryDetailsList) {
        categoryView.getCategoryData(categoryDetailsList);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        categoryView.CategoryErrorMsg(errorMsg);
    }
}
