package com.example.FoodCoash.home.presenter;

import com.example.FoodCoash.home.view.CategoryView;
import com.example.FoodCoash.model.DTO.CategoriesItem;
import com.example.FoodCoash.model.MealRepositoryIN;
import com.example.FoodCoash.model.netowark.CategoryItemCallBack;

import java.util.List;

public class CategoryPresenter implements CategoryPresenterIN, CategoryItemCallBack {
    private final CategoryView categoryView;
    private final MealRepositoryIN repositoryView;

    public CategoryPresenter(CategoryView categoryView, MealRepositoryIN repositoryView) {
        this.categoryView = categoryView;
        this.repositoryView = repositoryView;
    }


    @Override
    public void getCategory() {
        repositoryView.CategoryNetworkCall(this);
    }

    @Override
    public void onSuccessResult(List<CategoriesItem> categoriesItemList) {
        categoryView.showCategoryData(categoriesItemList);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        categoryView.showErrorMsgCategory(errorMsg);
    }
}
