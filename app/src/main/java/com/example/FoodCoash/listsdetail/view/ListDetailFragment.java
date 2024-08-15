package com.example.FoodCoash.listsdetail.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.myfoodplaner.R;
import com.example.FoodCoash.listsdetail.presenter.ListDetailPresenter;
import com.example.FoodCoash.listsdetail.presenter.ListDetailPresenterIN;
import com.example.FoodCoash.model.DTO.GeneratingListIngridentsArrayLists;
import com.example.FoodCoash.model.DTO.ListsDetails;
import com.example.FoodCoash.model.DTO.MealsDetail;
import com.example.FoodCoash.model.DTO.MealsDetailResponse;
import com.example.FoodCoash.model.DTO.MealsItem;
import com.example.FoodCoash.model.DTO.WeekPlan;
import com.example.FoodCoash.model.MealRepository;
import com.example.FoodCoash.model.MealRepositoryIN;
import com.example.FoodCoash.model.netowark.MealRemoteDataSourceImpl;
import com.example.FoodCoash.model.netowark.database.MealLocalDataSourceImpl;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ListDetailFragment extends Fragment implements ListDetailView, OnMealDetailClickListener {

    private ImageView itemImage;
    private TextView tvItemName;
    private TextView tvItemCountry;
    private TextView tvItemCategory;
    private TextView tvProcedures;
    private ImageView addToFavImage;
    private ListDetailPresenterIN listDetailPresenterIN;
    private ListsDetails listsDetails;
    private ListsDetails listAreaDetails;
    private ListsDetails listingredientDetails;
    private MealsItem searchByName;
    private MealsItem favMeal;
    private WeekPlan weekPlanMeal;
    private Context context;
    private ListDetailAdapter listDetailAdapter;
    private RecyclerView recyclerView;
    private Single <MealsDetailResponse> mealsDetailList;
    private ImageView itemingredientImage;
    private TextView itemingredientName;
    private LinearLayoutManager linearLayoutManager;
    private CardView ingridentCardView;
    private YouTubePlayerView youTubePlayerView;
    private MealRepositoryIN mealRepositoryIN;
    private ImageView addToCalender;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_list_detail, container, false);




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(R.id.recyclerViewIngredientsItemDetails);

        tvItemName = view.findViewById(R.id.txtViewMealNameItemDetails);
        tvItemCountry = view.findViewById(R.id.textViewMealCountryItemDetails);
        tvItemCategory = view.findViewById(R.id.textViewMealCateItemDetails);
        itemImage = view.findViewById(R.id.mealImage);
        addToFavImage = view.findViewById(R.id.imageViewAddToFavITemDetails);
        tvProcedures = view.findViewById(R.id.textViewProcedures);
        youTubePlayerView = view.findViewById(R.id.ytPlayer);
        addToCalender= view .findViewById(R.id.imageViewAddToCalendarItemDetails);





        linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        listDetailAdapter = new ListDetailAdapter(requireContext());
        recyclerView.setAdapter(listDetailAdapter);



        listDetailPresenterIN = new ListDetailPresenter(this, MealRepository.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));

        listsDetails = (ListsDetails) getArguments().getSerializable("categoryDetail");
        listAreaDetails = (ListsDetails) getArguments().getSerializable("areaDetails");
        listingredientDetails =(ListsDetails) getArguments().getSerializable("ingredientDetails");
        searchByName = (MealsItem) getArguments().getSerializable("SearchByName");
        favMeal = (MealsItem) getArguments().getSerializable("Favorite");
        weekPlanMeal = (WeekPlan) getArguments().getSerializable("weekPlan");


        Single<MealsDetailResponse> mealsDetailSingle = listDetailPresenterIN.getMealDetail(listsDetails != null ? listsDetails.getIdMeal() :
                listAreaDetails != null ? listAreaDetails.getIdMeal() :
                        listingredientDetails != null ? listingredientDetails.getIdMeal() : searchByName != null ? searchByName.getIdMeal() :favMeal != null ? favMeal.getIdMeal() :weekPlanMeal != null ? weekPlanMeal.getIdMeal() :"");






        mealsDetailSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealsDetailResponse -> {
                    MealsDetail mealsDetail = mealsDetailResponse.getMeals().get(0);
                    tvItemName.setText(mealsDetail.getStrMeal());
                    tvItemCountry.setText(mealsDetail.getStrArea());
                    tvItemCategory.setText(mealsDetail.getStrCategory());
                    tvProcedures.setText(mealsDetail.getStrInstructions());
                    Glide.with(requireActivity()).load(mealsDetail.getStrMealThumb()).into(itemImage);
                    listDetailAdapter.setMealItemDetailList(GeneratingListIngridentsArrayLists.getIngridentsListArray(mealsDetail));

                    addToFavImage.setOnClickListener(v -> {
                        listDetailPresenterIN.addToFav(mealsDetail);
                    });




                    String youtubeVideoId = mealsDetail.getStrYoutube();
                    if (youtubeVideoId != null && !youtubeVideoId.isEmpty()) {
                        youTubePlayerView.setVisibility(View.VISIBLE);
                        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                            @Override
                            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                                super.onReady(youTubePlayer);
                                loadVideo(youTubePlayer, youtubeVideoId);
                            }
                        });
                    } else {
                        // Hide YouTube player if there's no video URL
                        youTubePlayerView.setVisibility(View.GONE);
                    }
                }, throwable -> {
                    Toast.makeText(requireContext(), "Error fetching meal details", Toast.LENGTH_SHORT).show();
                });


    }

    @Override
    public void onPause() {
        super.onPause();
        youTubePlayerView.release();
    }

    @Override
    public void showMealDetailData(List<MealsDetail> mealsDetailList) {
    }

    @Override
    public void addMealToFav(MealsDetail mealsDetail) {
        mealRepositoryIN.insertMeal(mealsDetail);
    }

    @Override
    public void showMealDetailErrorMsg(String error) {
        Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealDetailClickListener(MealsItem mealsItem) {

    }

    private void loadVideo(@NonNull YouTubePlayer youTubePlayer, String youtubeVideoId) {
        youTubePlayer.loadVideo(getVideoId(youtubeVideoId), 0);
    }

    private String getVideoId(String videoUrl) {
        String videoId = null;
        String[] urlParts = videoUrl.split("v=");
        if (urlParts.length > 1) {
            videoId = urlParts[1];
        }
        return videoId;
    }
}