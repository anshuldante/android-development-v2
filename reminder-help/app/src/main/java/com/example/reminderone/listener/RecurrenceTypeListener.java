package com.example.reminderone.listener;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.reminderone.model.RecurrenceType;
import com.example.reminderone.model.ReminderDetails;

public class RecurrenceTypeListener implements AdapterView.OnItemSelectedListener {
    private final ReminderDetails reminderDetails;

    public RecurrenceTypeListener(ReminderDetails reminderDetails) {
        this.reminderDetails = reminderDetails;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i("Recurrence Type selected: ", "position: "
                + position + " value: " + parent.getItemAtPosition(position).toString());
        reminderDetails.setRecurrenceType(
                RecurrenceType.getRecurrenceTypeByValue((String) parent.getItemAtPosition(position)));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
