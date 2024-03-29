package com.aknosova.weatherapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WeeklyWeatherFragment extends Fragment implements OnRecyclerViewClickListener{
    public static final String TAG = "WeeklyWeatherFragment";

    private String[] week = {"Monday +17°C", "Tuesday +23°C", "Wednesday +20°C", "Thursday +16°C", "Friday +23°C", "Saturday +23°C", "Sunday +20°C"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weekly_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        if (getContext() == null) {
            return;
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        WeeklyWeatherAdapter weeklyWeatherAdapter = new WeeklyWeatherAdapter(week, this);
        recyclerView.setAdapter(weeklyWeatherAdapter);
    }

    @Override
    public void onClick(View view, int position) {
        if (getActivity() != null) {
            String message;
            message = String.format("Выбран день недели - %s", position);
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }
}
