package com.aknosova.weatherapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class SearchCityFragment extends Fragment {

    DataDisplayFragment dataDisplayFragment;
    LocalParcel localParcel;
    public static final String STATE = "STATE";
    private EditText editTextCity;
    private CheckBox humidityParam;
    private String cityValue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_city, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activityContext = getActivity();

        if (activityContext == null) {
            return;
        }

        Button searchBtn = getActivity().findViewById(R.id.search_button);
        humidityParam = getActivity().findViewById(R.id.cb_humidity);
        editTextCity = getActivity().findViewById(R.id.search_input);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localParcel = new LocalParcel();
                cityValue = editTextCity.getText().toString();
                localParcel.setText(cityValue);
                localParcel.setChecked(isCheckedCheckbox());

                Bundle bundle = new Bundle();
                bundle.putSerializable(STATE, localParcel);

                dataDisplayFragment = new DataDisplayFragment();
                dataDisplayFragment.setArguments(bundle);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_container, dataDisplayFragment).addToBackStack(null).commit();
            }
        });
    }

    private boolean isCheckedCheckbox() {
        return humidityParam.isChecked();
    }
}

