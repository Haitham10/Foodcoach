package com.example.FoodCoash.categories.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.FoodCoash.model.DTO.ListsDetails;
import com.example.myfoodplaner.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private List<ListsDetails> categoryList;
    private CategoryListener categoryListener;

    public CategoryAdapter(Context context, List<ListsDetails> categoryList, CategoryListener categoryListener) {
        this.context = context;
        this.categoryList = categoryList;
        this.categoryListener = categoryListener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListsDetails categoriesDetailsItem = categoryList.get(position);
        holder.textViewcatagory.setText(categoriesDetailsItem.getStrMeal());
        Glide.with(context).load(categoriesDetailsItem.getStrMealThumb()).into(holder.imageView);

        holder.imageView.setOnClickListener(v -> {
            if (categoryListener != null) {
                categoryListener.onCategoryClick(categoriesDetailsItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewcatagory;

        public ViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.categoryDetailsItemImage);
            textViewcatagory = v.findViewById(R.id.txtCategoryDetailsItemName);
        }
    }
    public void setList(List<ListsDetails> categoryDetailsList) {
        this.categoryList = categoryDetailsList;
        notifyDataSetChanged();
    }
}
