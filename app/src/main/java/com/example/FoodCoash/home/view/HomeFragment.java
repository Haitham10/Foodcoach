package com.example.FoodCoash.home.view;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.myfoodplaner.R;
import com.example.FoodCoash.home.presenter.CategoryPresenter;
import com.example.FoodCoash.home.presenter.CategoryPresenterIN;
import com.example.FoodCoash.home.presenter.RandomMealPresenter;
import com.example.FoodCoash.home.presenter.RandomMealPresenterView;
import com.example.FoodCoash.model.DTO.CategoriesItem;
import com.example.FoodCoash.model.DTO.MealsItem;
import com.example.FoodCoash.model.MealRepository;
import com.example.FoodCoash.model.netowark.MealRemoteDataSourceImpl;
import com.example.FoodCoash.model.netowark.database.MealLocalDataSourceImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements RandomMealView , CategoryView, CategoryListener {
    private Context context;
    private CategoryAdapter categoryAdapter;
    private RecyclerView recyclerView;
    List<MealsItem> mealsItemList;
    private ImageView image;
    private TextView mealName;
    private TextView mealCountry;
    private RandomMealPresenterView presenterView;
    private CategoryPresenterIN categoryPresenterIN;
    private LinearLayoutManager linearLayoutManager;
    private LottieAnimationView lottieAnimationView;
    private NestedScrollView nestedScrollView;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_, container, false);
        image = view.findViewById(R.id.iv_random);
        mealName = view.findViewById(R.id.RandomTextMeal);
        nestedScrollView = view.findViewById(R.id.nestedScrollView);
        presenterView = new RandomMealPresenter(this, MealRepository.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext())));
        presenterView.getMeal();
        if (!isNetworkAvailable()) {
            nestedScrollView.setVisibility(View.GONE);

        }else {
            nestedScrollView.setVisibility(View.VISIBLE);

        }


        presenterView = new RandomMealPresenter(this, MealRepository.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));
        presenterView.getMeal();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.categoryRecyclerView);


        linearLayoutManager = new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        categoryAdapter = new CategoryAdapter(getContext(), new ArrayList<>(), this);
        recyclerView.setAdapter(categoryAdapter);
        categoryPresenterIN = new CategoryPresenter(this, MealRepository.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));
        categoryAdapter.setCategoryClickListener(this);
        categoryPresenterIN.getCategory();
    }

    @Override
    public void showData(List<MealsItem> mealsItemList) {
        MealsItem item = mealsItemList.get(0);
        mealName.setText(item.getStrMeal());
        Glide.with(getContext()).load(item.getStrMealThumb()).into(image);
        image.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("item", item);
            Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_mealDetailsFragment, bundle);
        });
    }

    @Override
    public void showErrorMsg(String error) {
    }

    @Override
    public void showCategoryData(List<CategoriesItem> categoriesItemList) {
        categoryAdapter.setList(categoriesItemList);
        categoryAdapter.notifyDataSetChanged();
        Toast.makeText(requireActivity(), "Success: " + categoriesItemList.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMsgCategory(String error) {
        Toast.makeText(requireActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCategoryClick(CategoriesItem category) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("category", (Serializable) category);
        Toast.makeText(requireActivity(), "category" + category, Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_categoryFragment, bundle);
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
        return false;
    }
}