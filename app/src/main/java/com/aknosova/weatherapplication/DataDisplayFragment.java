package com.aknosova.weatherapplication;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.aknosova.weatherapplication.rest.entities.WeatherModelRequest;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.SENSOR_SERVICE;
import static com.aknosova.weatherapplication.SearchCityFragment.STATE;


public class DataDisplayFragment extends Fragment {
    final static String BROADCAST_ACTION = "android_2.lesson04.app01.service_finished";
    final static String CITYVALUE = "CITYVALUE";
    static final String TAG = "DataDisplayFragment";
    private final static String LOG_TAG = DataDisplayFragment.TAG;
    private final Handler handler = new Handler();

    private TextView textViewCity;
    private TextView textViewInfo;
    private TextView textViewHumiditySensor;
    private TextView textViewTemperatureSensor;
    private Button nextWeekBtn;
    private SensorManager sensorManager;
    private List<Sensor> sensors;
    private Sensor sensorTemperature;
    private Sensor sensorHumidity;
    //    private MyReceiver receiver;
    private TextView tempTextView;
    private IntentFilter intentFilter;
    private Intent intentServiceSend;
    private TextView weatherIcon;
    private Context context;
    private Typeface weatherFont;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_data_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);

        context = getContext();

        initViews(view);
        initFonts();

        updateWeatherData(getParcelData());

        getSensors();

        setViewsValuesSensors();

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

        registerSensorListener(sensorListenerTemperature, sensorTemperature);
        registerSensorListener(sensorListenerHumidity, sensorHumidity);

        FragmentActivity activityContext = getActivity();

        if (activityContext == null) {
            return;
        }

        if (getArguments() != null) {
            getParcelData();

        }

        final WeeklyWeatherFragment weeklyWeatherFragment = new WeeklyWeatherFragment();

        btnNextSetOnClickListener(weeklyWeatherFragment);
    }

    private void setViewsValuesSensors() {
        if (sensorTemperature != null) {
            textViewTemperatureSensor.setVisibility(View.VISIBLE);
        } else textViewTemperatureSensor.setVisibility(View.GONE);

        if (sensorHumidity != null) {
            textViewHumiditySensor.setVisibility(View.VISIBLE);
        } else textViewHumiditySensor.setVisibility(View.GONE);
    }

    private void registerSensorListener(SensorEventListener sensorEventListener, Sensor sensor) {
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void getSensors() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            sensorManager = (SensorManager) Objects.requireNonNull(getActivity()).getSystemService(SENSOR_SERVICE);
        }

        if (sensorManager == null) {
            return;
        }

        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        if (sensorManager != null) {
            sensorTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        }

        if (sensorManager != null) {
            sensorHumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        }

        if (sensorTemperature != null) {
            textViewTemperatureSensor.setVisibility(View.VISIBLE);
        } else textViewTemperatureSensor.setVisibility(View.GONE);

        if (sensorHumidity != null) {
            textViewHumiditySensor.setVisibility(View.VISIBLE);
        } else textViewHumiditySensor.setVisibility(View.GONE);
    }

    private String getParcelData() {
        String cityValue;
        LocalParcel parcel = null;
        if (getArguments() != null) {
            parcel = (LocalParcel) getArguments().getSerializable(STATE);
        }

        if (parcel == null) {
            return null;
        }
        cityValue = parcel.getText();

        return cityValue;
    }

    private void btnNextSetOnClickListener(final Fragment fragment) {
        nextWeekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    mainActivity.startSecondFragment(WeeklyWeatherFragment.TAG, null, fragment);
                }
            }
        });
    }

    private void showSensor(SensorEvent event, TextView textView, String text) {
        StringBuilder sensorValueText = new StringBuilder();

        sensorValueText.append(event.values[0]);
        textView.setText(text + sensorValueText);
    }
//
//    private void infoServiceSend() {
//        intentServiceSend = new Intent(getActivity(), MainService.class);
//        intentServiceSend.putExtra(CITYVALUE, textViewCity.getText().toString());
//        getActivity().startService(intentServiceSend);
//    }
//
//    private void registerReceiver() {
//        receiver = new MyReceiver();
//        intentFilter = new IntentFilter(BROADCAST_ACTION);
//        getActivity().registerReceiver(receiver, intentFilter);
//    }

    private void initViews(View view) {
        textViewCity = view.findViewById(R.id.city);
        textViewInfo = view.findViewById(R.id.info_data);
        nextWeekBtn = view.findViewById(R.id.weekly_btn);
        textViewTemperatureSensor = view.findViewById(R.id.temperature_sensor);
        textViewHumiditySensor = view.findViewById(R.id.humidity_sensor);
        tempTextView = view.findViewById(R.id.temperature);
        weatherIcon = view.findViewById(R.id.image_weather);

    }

    private void updateWeatherData(final String city) {
        OpenWeatherAdapter.getSingleton().getiOpenWeather().loadWeather(city,
                "762ee61f52313fbd10a4eb54ae4d4de2", "metric")
                .enqueue(new Callback<WeatherModelRequest>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherModelRequest> call,
                                           @NonNull Response<WeatherModelRequest> response) {
                        if (response.body() == null) {
                            textViewCity.setText(R.string.change_request);

                            Toast.makeText(context, R.string.city_not_found, Toast.LENGTH_LONG).show();
                            return;
                        }

                        if (response.code() != 200) {
                            checkResponseCode(response.code());
                            return;
                        }

                        renderWeather(response.body());
                    }

                    @Override
                    public void onFailure(@NotNull Call<WeatherModelRequest> call, Throwable t) {
                        Toast.makeText(context, R.string.internet_fail,
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void renderWeather(WeatherModelRequest weatherModelRequest) {
        setPlaceName(weatherModelRequest.name, weatherModelRequest.sys.country);
        setDetails(weatherModelRequest.weather[0].description, weatherModelRequest.main.humidity, weatherModelRequest.main.pressure);
        setCurrentTemp(weatherModelRequest.main.temp);
        setWeatherIcon(weatherModelRequest.weather[0].id,
                weatherModelRequest.sys.sunrise * 1000,
                weatherModelRequest.sys.sunset * 1000);
    }

    private void setPlaceName(String name, String country) {
        String cityText = name.toUpperCase() + ", " + country;
        textViewCity.setText(cityText);
    }

    private void setDetails(String description, float humidity, float pressure) {
        String detailsText = description.toUpperCase() + "\n"
                + "Humidity: " + humidity + "%" + "\n"
                + "Pressure: " + pressure + "hPa";
        textViewInfo.setText(detailsText);
    }

    private void setCurrentTemp(float temp) {
        String currentTextText = String.format(Locale.getDefault(), "%.0f", temp) + "\u2103";
        tempTextView.setText(currentTextText);
    }

//    class MyReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String tempValue = intent.getStringExtra(CITYVALUE);
//            tempTextView.setText(tempValue);
//        }
//    }

    private void setWeatherIcon(int actualId, long sunrise, long sunset) {
        int id = actualId / 100;
        String icon = "";

        if (actualId == 800) {
            long currentTime = new Date().getTime();
            if (currentTime >= sunrise && currentTime < sunset) {
                icon = "\u2600";
            } else {
                icon = getString(R.string.weather_clear_night);
            }
        } else {
            switch (id) {
                case 2: {
                    icon = getString(R.string.weather_thunder);
                    break;
                }
                case 3: {
                    icon = getString(R.string.weather_drizzle);
                    break;
                }
                case 5: {
                    icon = getString(R.string.weather_rainy);
                    break;
                }
                case 6: {
                    icon = getString(R.string.weather_snowy);
                    break;
                }
                case 7: {
                    icon = getString(R.string.weather_foggy);
                    break;
                }
                case 8: {
                    icon = "\u2601";
                    break;
                }
            }
        }
        weatherIcon.setText(icon);
    }

    private void checkResponseCode(int code) {
        switch (code) {
            case 500:
                textViewCity.setText("Повторите запрос позднее");
                Toast.makeText(context, R.string.error_server,
                        Toast.LENGTH_LONG).show();
                break;

            case 401:

                textViewCity.setText("Повторите запрос еще раз");
                Toast.makeText(context, R.string.lost_token,
                        Toast.LENGTH_LONG).show();
                break;

        }
    }

    private void initFonts() {
        weatherFont = Typeface.createFromAsset(context.getAssets(), "fonts/weather.ttf");

        weatherIcon.setTypeface(weatherFont);
    }

//    private void updateWeatherData(final String city) {
//        new Thread() {
//            @Override
//            public void run() {
//                final JSONObject jsonObject = DataLoadWeatherOkHttp.getJSONData(city);
//                if (jsonObject == null) {
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                                textViewCity.setText(R.string.city_not_found);
//                                Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), R.string.city_not_found,
//                                        Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    });
//                } else {
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            renderWeather(jsonObject);
//                        }
//                    });
//                }
//            }
//        }.start();
//    }

    //    private void renderWeather(JSONObject jsonObject) {
//        Log.d(LOG_TAG, "json: " + jsonObject.toString());
//        try {
//            JSONObject details = jsonObject.getJSONArray("weather").getJSONObject(0);
//            JSONObject main = jsonObject.getJSONObject("main");
//
//            setPlaceName(jsonObject);
//            setDetails(details, main);
//            setCurrentTemp(main);
//            setWeatherIcon(details.getInt("id"),
//                    jsonObject.getJSONObject("sys").getLong("sunrise") * 1000,
//                    jsonObject.getJSONObject("sys").getLong("sunset") * 1000);
//        } catch (Exception exc) {
//            exc.printStackTrace();
//            Log.e(LOG_TAG, "One or more fields not found in the JSON data");
//        }
//    }
//
//    private void setPlaceName(JSONObject jsonObject) throws JSONException {
//        String cityText = jsonObject.getString("name").toUpperCase() + ", "
//                + jsonObject.getJSONObject("sys").getString("country");
//        Log.d("TEST", cityText);
//        textViewCity.setText(cityText);
//    }
//
//    private void setDetails(JSONObject details, JSONObject main) throws JSONException {
//        String detailsText = details.getString("description").toUpperCase() + "\n"
//                + "Humidity: " + main.getString("humidity") + "%" + "\n"
//                + "Pressure: " + main.getString("pressure") + "hPa";
//        textViewInfo.setText(detailsText);
//    }
//
//    private void setCurrentTemp(JSONObject main) throws JSONException {
//        String currentTextText = String.format(Locale.getDefault(), "%.2f",
//                main.getDouble("temp")) + "\u2103";
//        tempTextView.setText(currentTextText);
//    }
}

