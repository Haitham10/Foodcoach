package com.example.FoodCoash.model.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AreaItem implements Serializable {

	@SerializedName("strArea")
	private String strArea;

	public String getStrArea(){
		return strArea;
	}
}