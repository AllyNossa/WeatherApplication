package com.aknosova.weatherapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class SearchCityFragment extends Fragment {
    public static final String TAG = "SearchCityFragment";
    public static final String STATE = "STATE";

    private LocalParcel localParcel;

    private TextInputEditText editTextCity;
    private Button searchBtn;
    private CheckBox humidityParam;
    private CheckBox pressureParam;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);

        editTextCity = view.findViewById(R.id.search_input);
        searchBtn = view.findViewById(R.id.search_button);
        humidityParam = view.findViewById(R.id.cb_humidity);
        pressureParam = view.findViewById(R.id.cb_pressure);

        final DataDisplayFragment dataDisplayFragment = new DataDisplayFragment();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextCity.getText() != null) {
                    String value = editTextCity.getText().toString();

                    if (value.matches("")) {
                        editTextCity.setError(getString(R.string.empty_error));
                    } else {
                        localParcel = new LocalParcel(editTextCity.getText().toString(), humidityParam.isChecked(), pressureParam.isChecked());

                        Bundle bundle = new Bundle();
                        bundle.putSerializable(STATE, localParcel);

                        if (getActivity() != null) {

                            MainActivity mainActivity = (MainActivity) getActivity();
                            mainActivity.startSecondFragment(DataDisplayFragment.TAG, bundle, dataDisplayFragment);
                        }
                    }
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

