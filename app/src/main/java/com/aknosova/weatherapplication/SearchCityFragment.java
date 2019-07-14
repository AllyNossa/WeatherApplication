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
    private CheckBox humidityParam;
    private String cityValue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        writeLogs("onCreateView()");
        return inflater.inflate(R.layout.fragment_search_city, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        writeLogs("onActivityCreated()");

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

