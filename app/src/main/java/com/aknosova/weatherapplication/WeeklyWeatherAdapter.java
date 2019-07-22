package com.aknosova.weatherapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeeklyWeatherAdapter extends RecyclerView.Adapter<WeeklyWeatherAdapter.ViewHolder> {
    private String[] data;
    private OnRecyclerViewClickListener recyclerItemClickListener;

    public WeeklyWeatherAdapter(String[] data, OnRecyclerViewClickListener recyclerItemClickListener) {
        this.data = data;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    @NonNull
    @Override
    public WeeklyWeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day_item, parent, false);
        ViewHolder viewHolder = new ViewHolder((CardView) view, recyclerItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.textView.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.length ;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private CardView cardView;


        public ViewHolder(@NonNull CardView view, final OnRecyclerViewClickListener recyclerItemClickListener) {
            super(view);

            textView = view.findViewById(R.id.text_view);
            cardView = view.findViewById(R.id.card_view);

            cardView = view;

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerItemClickListener != null) {
                        recyclerItemClickListener.onClick(view, getAdapterPosition());
                    }
                }
            });
        }
    }
}
