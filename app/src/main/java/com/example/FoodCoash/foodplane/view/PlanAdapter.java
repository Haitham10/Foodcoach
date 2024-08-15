package com.example.FoodCoash.foodplane.view;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfoodplaner.R;
import com.example.FoodCoash.model.DTO.WeekPlan;
import com.example.FoodCoash.foodplane.presenter.WeekPresenterIN;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder>{

    private Context context;
    private List<WeekPlan> weekPlanListMealList;
    private OnWeekPlanMealClickListener onWeekPlanMealClickListener;
    private ImageView removeFromWeekPlanImage;
    private WeekPresenterIN weekPresenterIN;

    public PlanAdapter(Context context) {
        this.context = context;
    }

    public PlanAdapter(Context context, List<WeekPlan> weekPlanListMealList, OnWeekPlanMealClickListener onWeekPlanMealClickListener, WeekPresenterIN weekPresenterIN) {
        this.context = context;
        this.weekPlanListMealList = weekPlanListMealList;
        this.onWeekPlanMealClickListener = onWeekPlanMealClickListener;
        this.weekPresenterIN = weekPresenterIN;
    }

    public void setWeekPlanMealList(List<WeekPlan> weekPlanListMealList) {
        this.weekPlanListMealList = weekPlanListMealList;
    }
    @NonNull
    @Override
    public PlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.week_plan_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanAdapter.ViewHolder holder, int position) {
        WeekPlan weekPlan = weekPlanListMealList.get(position);
        holder.bind(weekPlan);
        holder.deleteMealImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWeekPlanMealClickListener.onDeleteItemClick(weekPlanListMealList.get(holder.getAdapterPosition()));
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onWeekPlanMealClickListener != null) {

                    onWeekPlanMealClickListener.onWeekPlanMealClick(weekPlan);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return weekPlanListMealList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mealImage;
        private ImageView deleteMealImage;
        private TextView mealName;
        private TextView mealCountry;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.weekMealImage);
            deleteMealImage = itemView.findViewById(R.id.deleteWeekMealImageView);
            mealName = itemView.findViewById(R.id.tvWeekMealName);
            cardView = itemView.findViewById(R.id.cardViewWeekPlanRow);
        }

        public void bind(WeekPlan weekPlan) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    mealName.setText(weekPlan.getStrMeal());
                    Glide.with(context).load(weekPlan.getStrMealThumb()).into(mealImage);

                }

            });
        }
}
}

