package com.aknosova.weatherapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import static com.aknosova.weatherapplication.SearchCityFragment.STATE;


public class DataDisplayFragment extends Fragment {
    public static final String TAG = "DataDisplayFragment";

    private TextView textViewCity;
    private TextView textViewhumidity;
    private String defaultCity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_data_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);

        textViewCity = view.findViewById(R.id.city);
        textViewhumidity = view.findViewById(R.id.humidity);

        FragmentActivity activityContext = getActivity();

        if (activityContext == null) {
            return;
        }

        if (getArguments() != null) {

            LocalParcel parcel = (LocalParcel) getArguments().getSerializable(STATE);

            if (parcel != null) {


                textViewCity.setText(parcel.getText());

                if (parcel.isChecked()) {
                    textViewhumidity.setVisibility(View.VISIBLE);
                } else textViewhumidity.setVisibility(View.GONE);
            } else {
                defaultCity = "Москва";
                textViewCity.setText(defaultCity);
                textViewhumidity.setVisibility(View.GONE);
            }
        }
    }
}
