package com.example.reminderone;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addReminderButton = findViewById(R.id.floatingActionButton);

        addReminderButton.setOnClickListener(this::attachDatePicker);
    }

    private void attachTimePicker(View v) {
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);
        TimePickerDialog tpd = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new MyTimeSetListener(), hour, minute, true);
        tpd.setTitle("Select Hour and Minute!");
        tpd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        tpd.show();
    }

    private void attachDatePicker(View v) {
        Calendar currentTime = Calendar.getInstance();
        int year = currentTime.get(Calendar.YEAR);
        int month = currentTime.get(Calendar.MONTH);
        int dayOfMonth = currentTime.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new MyDateSetListener(), year, month, dayOfMonth);
        dpd.setTitle("Select the Date!!");
        dpd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dpd.show();
    }

    private static class MyTimeSetListener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Log.i("Time picked: ", "Hour of day: " + hourOfDay + ", minute: " + minute);
            Log.i("Time picked: ", "Hour of day v2: " + view.getHour() + ", minute v2: " + view.getMinute());
        }
    }

    private static class MyDateSetListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Log.i("Time picked: ", "Year: " + year + ", month: " + month + ", day: " + dayOfMonth);
        }
    }
}