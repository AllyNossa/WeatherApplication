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
    TextView textViewCity;
    TextView textViewhumidity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_data_display, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activityContext = getActivity();

        textViewCity = getView().findViewById(R.id.city);
        textViewhumidity = getView().findViewById(R.id.humidity);

        if (activityContext == null) {
            return;
        }

        Bundle fromSearchCityFragment = getArguments();

        LocalParcel parcel = (LocalParcel) fromSearchCityFragment.getSerializable(STATE);

        if (getArguments() != null) {

            textViewCity.setText(parcel.getText());

            if (parcel.isChecked()) {
                textViewhumidity.setVisibility(View.VISIBLE);
            } else textViewhumidity.setVisibility(View.INVISIBLE);
        }
    }
}
