package com.example.reminderone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddReminderActivity extends AppCompatActivity {

    Spinner typeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        initRecurrenceTypeSpinner();

        initCalenderViewers();
    }

    private void initRecurrenceTypeSpinner() {
        typeSpinner = findViewById(R.id.ara_sn_recurrence_type);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.recurrence_type_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typeSpinner.setAdapter(spinnerAdapter);
    }

    private void initCalenderViewers() {

    }
}