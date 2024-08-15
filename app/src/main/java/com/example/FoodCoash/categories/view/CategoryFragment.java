package com.example.FoodCoash.categories.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.FoodCoash.categories.presenter.CategoryPresenter;
import com.example.FoodCoash.categories.presenter.CategoryPresenterIN;
import com.example.FoodCoash.model.DTO.CategoriesItem;
import com.example.FoodCoash.model.DTO.ListsDetails;
import com.example.FoodCoash.model.DTO.ListsDetailsResponse;
import com.example.FoodCoash.model.MealRepository;
import com.example.FoodCoash.model.netowark.MealRemoteDataSourceImpl;
import com.example.FoodCoash.model.netowark.database.MealLocalDataSourceImpl;
import com.example.myfoodplaner.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class CategoryFragment extends Fragment  implements CategoryView, CategoryListener {

    private Context context;
    private CategoryAdapter categoryAdapter;
    private RecyclerView recyclerView;
    Single<ListsDetailsResponse> categoryDetailsList;
    private CategoryPresenterIN categoryPresenterIN;
    private LinearLayoutManager linearLayoutManager;
    CardView randomCardView;
    CategoriesItem category;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_category, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        categoryAdapter = new CategoryAdapter(requireActivity(),new ArrayList<>(),this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        categoryPresenterIN = new CategoryPresenter(this, MealRepository.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));

        category = (CategoriesItem) getArguments().getSerializable("category");
        Toast.makeText(requireActivity(), "strmeal"+ category.getStrCategory(), Toast.LENGTH_SHORT).show();
        if (category != null) {
            categoryDetailsList = categoryPresenterIN.getCategory(category.getStrCategory());
            Log.i("TAG", "s()categoryDetailsList " +categoryDetailsList);
            categoryDetailsList.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(item -> {
                                categoryAdapter.setList(item.getListDetails());
                                recyclerView.setAdapter(categoryAdapter);
                            },
                            throwable -> {
                                Log.i("TAG", "showCategoryDetail: unable to show products because: " + throwable.getMessage());
                            });
        }


    }

    @Override
    public void getCategoryData(List<ListsDetails> categoryDetailsList) {

    }

    @Override
    public void CategoryErrorMsg(String error) {

    }

    @Override
    public void onCategoryClick(ListsDetails listsDetails) {
        Bundle bundle = new Bundle();
       bundle.putSerializable("categoryDetail", listsDetails);
        Navigation.findNavController(requireView()).navigate(R.id.action_categoryFragment_to_listDetailFragment, bundle);
    }
}