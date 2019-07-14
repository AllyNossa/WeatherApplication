package com.aknosova.weatherapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
    private static final String TAG = "LifeCycle";

    DataDisplayFragment dataDisplayFragment;
    LocalParcel localParcel;
    public static final String STATE = "STATE";
    private EditText editTextCity;
    private Button searchBtn;
    private CheckBox humidityParam;
    private String cityValue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        writeLogs("onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        return inflater.inflate(R.layout.fragment_search_city, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        writeLogs("onActivityCreated()");
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
                localParcel = new LocalParcel();
                cityValue = editTextCity.getText().toString();
                localParcel.setText(cityValue);
                localParcel.setChecked(isCheckedCheckbox());

                Bundle bundle = new Bundle();
                bundle.putSerializable(STATE, localParcel);

                dataDisplayFragment = new DataDisplayFragment();
                dataDisplayFragment.setArguments(bundle);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_container, dataDisplayFragment)
                        .commit();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        writeLogs("onPause()");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        writeLogs("onAttach()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("CITY", editTextCity.getText().toString());
    }

    @Override
    public void onDestroyView() {
        writeLogs("onDestroyView()");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        writeLogs("onDetach()");
        super.onDetach();
    }

    private boolean isCheckedCheckbox() {
        return humidityParam.isChecked();
    }

    private void writeLogs(String state) {
        Log.d(TAG, "Фрагмент " + state);
    }
}

