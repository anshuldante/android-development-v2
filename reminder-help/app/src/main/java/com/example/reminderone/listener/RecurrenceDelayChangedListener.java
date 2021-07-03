package com.example.reminderone.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.example.reminderone.model.ReminderDetails;

public class RecurrenceDelayChangedListener implements TextWatcher {
    private final ReminderDetails reminderDetails;

    public RecurrenceDelayChangedListener(ReminderDetails reminderDetails) {
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
        if (s != null) {
            Log.i("Edit Text to Recurrence Delay: ", s.toString());
            reminderDetails.setRecurrenceDelay(Integer.parseInt(s.toString()));
        }
    }
}
