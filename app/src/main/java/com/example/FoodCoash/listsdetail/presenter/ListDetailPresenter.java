package com.example.FoodCoash.listsdetail.presenter;

import com.example.FoodCoash.listsdetail.view.ListDetailView;
import com.example.FoodCoash.model.MealRepositoryIN;
import com.example.FoodCoash.model.DTO.MealsDetail;
import com.example.FoodCoash.model.DTO.MealsDetailResponse;
import com.example.FoodCoash.model.netowark.MealDetailsCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class ListDetailPresenter implements ListDetailPresenterIN, MealDetailsCallback {
    private ListDetailView listDetailView;
    private MealsDetail mealsDetail;
    private MealRepositoryIN mealRepositoryIN;

    public ListDetailPresenter(ListDetailView listDetailView, MealRepositoryIN mealRepositoryIN){
        this.listDetailView = listDetailView;
        this.mealRepositoryIN = mealRepositoryIN;
    }
    @Override
    public Single<MealsDetailResponse> getMealDetail(String category) {
        return mealRepositoryIN.getMealByIdNetworkCall(category);
    }

    @Override
    public void addToFav(MealsDetail mealsDetail) {
        this.mealsDetail = mealsDetail;
    }

    @Override
    public void onSuccessResult(List<MealsDetail> mealsDetailList) {
        listDetailView.showMealDetailData(mealsDetailList);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        listDetailView.showMealDetailErrorMsg(errorMsg);
    }
}
