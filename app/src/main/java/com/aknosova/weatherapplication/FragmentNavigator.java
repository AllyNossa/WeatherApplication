package com.aknosova.weatherapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public interface FragmentNavigator {
    void startFragment(@Nullable String tag, @Nullable Bundle bundle);
    void commitFragment(@NonNull Fragment fragment, String tag);
}
