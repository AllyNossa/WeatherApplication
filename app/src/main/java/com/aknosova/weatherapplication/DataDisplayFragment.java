package com.aknosova.weatherapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.List;

import static android.content.Context.SENSOR_SERVICE;
import static com.aknosova.weatherapplication.SearchCityFragment.STATE;


public class DataDisplayFragment extends Fragment {
    public static final String TAG = "DataDisplayFragment";

    private TextView textViewCity;
    private TextView textViewHumidity;
    private TextView textViewHumiditySensor;
    private TextView textViewTemperatureSensor;
    private TextView textViewPressure;
    private String defaultCity;
    private Button nextWeekBtn;
    private SensorManager sensorManager;
    private List<Sensor> sensors;
    private Sensor sensorTemperature;
    private Sensor sensorHumidity;

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
        textViewHumidity = view.findViewById(R.id.humidity);
        textViewPressure = view.findViewById(R.id.pressure);
        nextWeekBtn = view.findViewById(R.id.weekly_btn);
        textViewTemperatureSensor = view.findViewById(R.id.temperature_sensor);
        textViewHumiditySensor = view.findViewById(R.id.humidity_sensor);

        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensorTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorHumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        if (sensorTemperature != null) {
            textViewTemperatureSensor.setVisibility(View.VISIBLE);
        } else textViewTemperatureSensor.setVisibility(View.GONE);

        if (sensorHumidity != null) {
            textViewHumiditySensor.setVisibility(View.VISIBLE);
        } else textViewHumiditySensor.setVisibility(View.GONE);

        SensorEventListener sensorListenerTemperature = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                showSensor(event, textViewTemperatureSensor, "Температура по датчику ");
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        SensorEventListener sensorListenerHumidity = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                showSensor(event, textViewHumiditySensor, "Влажность по датчику ");
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(sensorListenerTemperature, sensorTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorListenerHumidity, sensorHumidity, SensorManager.SENSOR_DELAY_NORMAL);

        FragmentActivity activityContext = getActivity();

        if (activityContext == null) {
            return;
        }

        if (getArguments() != null) {

            LocalParcel parcel = (LocalParcel) getArguments().getSerializable(STATE);

            if (parcel != null) {

                textViewCity.setText(parcel.getText());

                if (parcel.isHumidityChecked()) {
                    textViewHumidity.setVisibility(View.VISIBLE);
                } else textViewHumidity.setVisibility(View.GONE);

                if (parcel.isPressureChecked()) {
                    textViewPressure.setVisibility(View.VISIBLE);
                } else textViewPressure.setVisibility(View.GONE);

            } else {
                defaultCity = "Москва";
                textViewCity.setText(defaultCity);
                textViewHumidity.setVisibility(View.GONE);
                textViewPressure.setVisibility(View.GONE);
            }
        }

        final WeeklyWeatherFragment weeklyWeatherFragment = new WeeklyWeatherFragment();

        nextWeekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.startSecondFragment(WeeklyWeatherFragment.TAG, null, weeklyWeatherFragment);
            }
        });
    }

    private void showSensor(SensorEvent event, TextView textView, String text) {
        StringBuilder sensorValueText = new StringBuilder();

        sensorValueText.append(event.values[0]);
        textView.setText(text + sensorValueText);
    }
}
