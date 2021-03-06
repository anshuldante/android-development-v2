package com.example.reminderone.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.example.reminderone.model.ReminderDetails;

public class ReminderNameChangedListener implements TextWatcher {
    private final ReminderDetails reminderDetails;

    public ReminderNameChangedListener(ReminderDetails reminderDetails) {
        this.reminderDetails = reminderDetails;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        String name = null;
        if (s != null) {
            Log.i("Edit Text to name: ", s.toString());
            name = s.toString().trim();
        }
        reminderDetails.setName(name);
    }
}
