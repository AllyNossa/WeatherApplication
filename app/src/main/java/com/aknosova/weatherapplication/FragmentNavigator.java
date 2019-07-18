package com.aknosova.weatherapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public interface FragmentNavigator {
    void startFirstFragment(@NonNull String tag, @NonNull Fragment fragment);
    void startSecondFragment(@NonNull String tag, @NonNull Bundle bundle, @NonNull Fragment fragment);
}
