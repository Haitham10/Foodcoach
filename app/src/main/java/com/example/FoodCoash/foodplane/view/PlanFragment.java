package com.example.FoodCoash.foodplane.view;

import android.app.AlertDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.myfoodplaner.R;
import com.example.FoodCoash.model.DTO.WeekPlan;
import com.example.FoodCoash.model.MealRepository;
import com.example.FoodCoash.model.netowark.MealRemoteDataSourceImpl;
import com.example.FoodCoash.model.netowark.database.MealLocalDataSourceImpl;
import com.example.FoodCoash.foodplane.presenter.FoodPlanPresenter;
import com.example.FoodCoash.foodplane.presenter.WeekPresenterIN;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanFragment extends Fragment implements FoodPlanMealView, OnWeekPlanMealClickListener {
    private OnWeekPlanMealClickListener onWeekPlanMealClickListener ;
    private RecyclerView recyclerView;
    private PlanAdapter planAdapter;
    private WeekPresenterIN weekPresenterIN;
    private Single<List<WeekPlan>> weekPlanMealList;
    private Calendar calendar;
    private CalendarView calendarView;
    private WeekPlan date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calender, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        calendarView = view.findViewById(R.id.calendarView);
        calendar = Calendar.getInstance();
        calendarView.setDate(calendar.getTimeInMillis());
        calendarView.setFirstDayOfWeek(Calendar.SUNDAY);


        weekPresenterIN = new FoodPlanPresenter(this, MealRepository.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));

        recyclerView = view.findViewById(R.id.weekPlanRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        planAdapter = new PlanAdapter(requireActivity(), new ArrayList<>(), this, weekPresenterIN);
        recyclerView.setAdapter(planAdapter);

        showList();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                String selectedDate = getStringFromDate(calendar.getTime());
                getMealsForDate(selectedDate);
            }
        });
    }
    private void getMealsForDate(String selectedDate) {

        weekPresenterIN.getMealsForDate(selectedDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(planItemList -> {
                    updateUIWithMeals(planItemList);
                }, throwable -> {
                });
    }

    private void updateUIWithMeals(List<WeekPlan> mealsItemList) {
        planAdapter.setWeekPlanMealList(mealsItemList);
        planAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteItemClick(WeekPlan weekPlan) {
        new AlertDialog.Builder(getContext())
                .setTitle("Delete Meal")
                .setMessage("Are you sure you want to remove the meal?")
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                    weekPresenterIN.deleteMeal(weekPlan);
                    Toast.makeText(getActivity(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    showList(); // Refresh list after deletion
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    @Override
    public void onWeekPlanMealClick(WeekPlan weekPlan) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("weekPlan", weekPlan);
        Navigation.findNavController(requireView()).navigate(R.id.action_calenderFragment_to_listDetailFragment, bundle);
    }

    private String getStringFromDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return format.format(date);
    }

    private String getStringFromDate(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return format.format(calendar.getTime());
    }
    @Override
    public void deleteWeekPlan(WeekPlan weekPlan) {

    }
    @Override
    public void showList() {
        weekPlanMealList = weekPresenterIN.getWeekPlanMealList();
        Log.i("TAG", "showList: "+weekPlanMealList);
        weekPlanMealList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weekPlanItemList -> {
                    planAdapter.setWeekPlanMealList(weekPlanItemList);
                    planAdapter.notifyDataSetChanged();
                }, throwable -> {
                    Log.i("TAG", "Unable to show Meal because: "+throwable.getMessage());
                });
    }
}

