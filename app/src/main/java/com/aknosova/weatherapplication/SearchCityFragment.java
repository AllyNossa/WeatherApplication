package com.aknosova.weatherapplication;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class SearchCityFragment extends Fragment {
    public static final String TAG = "SearchCityFragment";
    public static final String STATE = "STATE";
    private static final String SAVE_KEY = "SAVE_KEY";

    private LocalParcel localParcel;

    private TextInputEditText editTextCity;
    private Button searchBtn;
    private String cityValue;
    private SharedPreferences sharedPreferences;

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

        final DataDisplayFragment dataDisplayFragment = new DataDisplayFragment();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            sharedPreferences = Objects.requireNonNull(getActivity()).getPreferences(MODE_PRIVATE);
        }

        if (sharedPreferences != null) {
            String value = loadPreferences(sharedPreferences, SAVE_KEY, "Moscow");
            editTextCity.setText(value);
        }

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextCity.getText() != null) {
                    cityValue = editTextCity.getText().toString();

                    if (cityValue.matches("")) {
                        editTextCity.setError(getString(R.string.empty_error));
                    } else {
                        localParcel = new LocalParcel(editTextCity.getText().toString());

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
        if (editTextCity.getText() != null) {
            savePreferences(SAVE_KEY, editTextCity.getText().toString());
        }

        searchBtn.setOnClickListener(null);
        super.onDestroyView();
    }

    private void savePreferences(@NonNull String key, @NonNull String value) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    private String loadPreferences(@NonNull SharedPreferences sharedPreferences, String key, String value) {
        String loadValue = sharedPreferences.getString(key, value);
        return loadValue;
    }
}

