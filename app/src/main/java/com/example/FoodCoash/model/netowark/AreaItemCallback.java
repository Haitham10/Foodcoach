package com.example.FoodCoash.model.netowark;

import com.example.FoodCoash.model.DTO.AreaItem;

import java.util.List;

public interface AreaItemCallback {
    public void onSuccessResult(List<AreaItem>areaItemList);
    public void onFailureResult(String errorMsg);
}
