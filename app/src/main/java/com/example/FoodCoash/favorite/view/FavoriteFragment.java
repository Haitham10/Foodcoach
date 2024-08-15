package com.example.FoodCoash.favorite.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodplaner.R;
import com.example.FoodCoash.favorite.presenter.FavPresenter;
import com.example.FoodCoash.favorite.presenter.FavourPresenterIN;
import com.example.FoodCoash.model.MealRepository;
import com.example.FoodCoash.model.DTO.MealsItem;
import com.example.FoodCoash.model.netowark.database.MealLocalDataSourceImpl;
import com.example.FoodCoash.model.netowark.MealRemoteDataSourceImpl;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteFragment extends Fragment implements FavourView, FavoriteListener {

    private RecyclerView favMealRecyclerView;
    private FavAdapter favMealsAdapter;
    private FavourPresenterIN favourPresenterIN;
    private FrameLayout frameLayout;
    private Single<List<MealsItem>> favMealsList;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favMealRecyclerView = view.findViewById(R.id.favoriteMeals_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        favMealRecyclerView.setLayoutManager(gridLayoutManager);
        favMealsAdapter = new FavAdapter(getContext(), new ArrayList<>(), this);
        favMealRecyclerView.setAdapter(favMealsAdapter);


        favourPresenterIN = FavPresenter.getInstance(
                this,
                MealRepository.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity()))
        );

        showFav();
    }

    @Override
    public void showFav() {

        favourPresenterIN.getFavMealList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealsItemList -> {

                    favMealsAdapter.setFavMealsList(mealsItemList);
                    favMealsAdapter.notifyDataSetChanged();
                }, throwable -> {
                    Toast.makeText(getContext(), "Unable to load favorite meals: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                });


    }


    @Override
    public void onDeleteItemClick(MealsItem meal) {
        new AlertDialog.Builder(getContext())
                .setTitle("Delete Meal")
                .setMessage("Are you sure you want to delete this meal?")
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                    favourPresenterIN.deleteMeal(meal);
                    Toast.makeText(getActivity(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    showFav(); // Refresh list after deletion
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    }

