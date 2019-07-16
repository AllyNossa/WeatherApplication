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

public class SearchCityFragment extends Fragment {
    public static final String TAG = "SearchCityFragment";
    public static final String STATE = "STATE";

    private LocalParcel localParcel;

    private EditText editTextCity;
    private Button searchBtn;
    private CheckBox humidityParam;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);

        editTextCity = getView().findViewById(R.id.search_input);
        searchBtn = getView().findViewById(R.id.search_button);
        humidityParam = getView().findViewById(R.id.cb_humidity);

        FragmentActivity activityContext = getActivity();

        if (activityContext == null) {
            return;
        }

        if (savedInstanceState != null) {
            editTextCity.setText(savedInstanceState.getString("CITY"));
        }

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localParcel = new LocalParcel(editTextCity.getText().toString(), humidityParam.isChecked());

                Bundle bundle = new Bundle();
                bundle.putSerializable(STATE, localParcel);

                if (getActivity() != null) {

                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.startFragment(DataDisplayFragment.TAG, bundle);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        searchBtn.setOnClickListener(null);
        super.onDestroyView();
    }
}

