package com.aknosova.weatherapplication;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class FeedbackFragment extends Fragment {
    private EditText feedbackText;
    private Button clearButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        feedbackText = view.findViewById(R.id.edit_text_feedback);
        clearButton = view.findViewById(R.id.btn_clear);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(feedbackText.getText() != null) {
                    feedbackText.setText("");
                }
            }
        });
    }
}


