package com.example.reminderone;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.reminderone.listener.RecurrenceDelayChangedListener;
import com.example.reminderone.listener.RecurrenceTypeListener;
import com.example.reminderone.listener.ReminderNameChangedListener;
import com.example.reminderone.model.ReminderDetails;

import java.util.Calendar;

import static java.util.Calendar.DATE;
import static java.util.Calendar.HOUR;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class AddReminderActivity extends AppCompatActivity {

    ArrayAdapter<CharSequence> spinnerAdapter;
    private ReminderDetails reminderDetails;
    private Spinner recurrenceTypeSpinner;
    private SwitchCompat recurrenceSwitch;
    private ImageView startDateImageView;
    private EditText recurrenceDelayEt;
    private TimePicker startTimePicker;
    private TextView startDateTextView;
    private TextView endDateTextView;
    private TextView endTimeTextView;
    private EditText reminderName;
    private Button saveButton;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        reminderDetails = new ReminderDetails();
        initComponentMappings();
        initPrimaryComponents();
        initRecurrenceComponents();
    }

    private void initComponentMappings() {
        spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.recurrence_type_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        recurrenceTypeSpinner = findViewById(R.id.ara_sn_recurrence_type);
        recurrenceDelayEt = findViewById(R.id.ara_et_recurrence_number);
        recurrenceSwitch = findViewById(R.id.ara_sc_recurring_reminder);
        startDateTextView = findViewById(R.id.ara_display_date);
        startTimePicker = findViewById(R.id.ara_time_picker_primary);
        startDateImageView = findViewById(R.id.ara_iv_calendar);
        reminderName = findViewById(R.id.ara_et_reminder_name);
        endDateTextView = findViewById(R.id.ara_tv_end_date);
        endTimeTextView = findViewById(R.id.ara_tv_end_time);
        saveButton = findViewById(R.id.ara_button_save);
        editButton = findViewById(R.id.ara_button_reset);
    }

    private void initPrimaryComponents() {
        initStartTimeComponents();
        initStartDateComponents();
        initReminderNameComponents();
        initRecurrenceSwitchListener();
        initSaveButton();
        initResetButton();
    }

    private void initRecurrenceComponents() {
        initRecurrenceTypeSpinner();
        initRecurrenceDelayComponent();
        initEndDateComponents();
        initEndTimeComponents();
    }

    private void initStartTimeComponents() {
        initStartTimeView();

        Calendar startDateTime = reminderDetails.getStartDateTime();
        startTimePicker.setIs24HourView(true);
        startTimePicker.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            Log.i("View to Start Time: ", hourOfDay + ":" + minute);
            startDateTime.set(HOUR_OF_DAY, hourOfDay);
            startDateTime.set(MINUTE, minute);
        });
    }

    private void initStartDateComponents() {
        initStartDateView();
        Calendar startDateTime = reminderDetails.getStartDateTime();
        startDateImageView.setOnClickListener(view -> attachDatePickerDialog(startDateTime));
    }

    private void attachDatePickerDialog(Calendar dateTime) {
        DatePickerDialog dpd = new DatePickerDialog(this, (view, year, month, dayOfMonth) ->
                dateSetListener(view, dateTime), dateTime.get(YEAR), dateTime.get(MONTH), dateTime.get(DATE));
        dpd.show();
    }

    private void dateSetListener(DatePicker view, Calendar dateTime) {
        Log.i("View to Date: ", view.getDayOfMonth() + "/" + (view.getMonth() + 1) + "/" + view.getYear());

        dateTime.set(YEAR, view.getYear());
        dateTime.set(MONTH, view.getMonth());
        dateTime.set(DATE, view.getDayOfMonth());
    }

    private void initReminderNameComponents() {
        initReminderNameView();
        reminderName.addTextChangedListener(new ReminderNameChangedListener(reminderDetails));
    }

    private void initRecurrenceSwitchListener() {
        initRecurrenceSwitchView();
        recurrenceSwitch.setOnCheckedChangeListener((buttonView, isChecked) ->
                findViewById(R.id.ara_cl_recurrence_delay).setVisibility(isChecked ? View.VISIBLE : View.GONE));
    }

    private void initSaveButton() {
        saveButton.setOnClickListener(this::onSaveButtonClickListener);
    }

    private void initResetButton() {
        editButton.setOnClickListener(this::onResetButtonClickListener);
    }

    private void onSaveButtonClickListener(View v) {
        Log.i("Saving Reminder: ", reminderDetails.toString());
    }

    private void onResetButtonClickListener(View v) {
        Log.i("Resetting Reminder: ", reminderDetails.toString());
    }

    private void initRecurrenceTypeSpinner() {
        initRecurrenceTypeView();
        recurrenceTypeSpinner.setAdapter(spinnerAdapter);
        recurrenceTypeSpinner.setOnItemSelectedListener(new RecurrenceTypeListener(reminderDetails));
    }

    private void initRecurrenceDelayComponent() {
        initRecurrenceDelayView();
        recurrenceDelayEt.addTextChangedListener(new RecurrenceDelayChangedListener(reminderDetails));
    }

    private void initEndDateComponents() {
        initEndDateView();
        ImageView endDateImageView = findViewById(R.id.ara_iv_end_calendar);
        endDateImageView.setOnClickListener(
                view -> attachDatePickerDialog(reminderDetails.getEndDateTime()));
    }

    private void initEndTimeComponents() {
        initEndTimeView();
        ImageView endTimeImageView = findViewById(R.id.ara_iv_end_clock);
        endTimeImageView.setOnClickListener(
                view -> attachTimePickerDialog(reminderDetails.getEndDateTime()));
    }

    private void attachTimePickerDialog(Calendar dateTime) {
        TimePickerDialog tpd = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                ((view, hourOfDay, minute) -> timeSetListener(view, dateTime)),
                dateTime.get(HOUR_OF_DAY), dateTime.get(MINUTE), true);
        tpd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        tpd.show();
    }

    private void timeSetListener(TimePicker view, Calendar dateTime) {
        Log.i("View to Time: ", dateTime.get(HOUR) + ":" + dateTime.get(MINUTE));

        dateTime.set(HOUR_OF_DAY, view.getHour());
        dateTime.set(MINUTE, view.getMinute());
    }

    private void initStartTimeView() {
        Calendar startDateTime = reminderDetails.getStartDateTime();
        Log.i("Start time to view: ", startDateTime.get(HOUR_OF_DAY) + ":" + startDateTime.get(MINUTE));
        startTimePicker.setHour(startDateTime.get(HOUR_OF_DAY));
        startTimePicker.setMinute(startDateTime.get(MINUTE));
    }

    private void initStartDateView() {
        Calendar startDateTime = reminderDetails.getStartDateTime();
        Log.i("Start Date to view: ", startDateTime.get(DATE) + "/" + (startDateTime.get(MONTH) + 1) + "/" + startDateTime.get(YEAR));
        startDateTextView.setText(getString(R.string.ria_reminder_date, startDateTime.get(DATE),
                startDateTime.get(MONTH) + 1, startDateTime.get(YEAR)));
    }

    private void initReminderNameView() {
        reminderName.setText(reminderDetails.getName());
    }

    private void initRecurrenceSwitchView() {
        recurrenceSwitch.setChecked(reminderDetails.getRecurrenceDelay() != null);
    }

    private void initRecurrenceTypeView() {
        String recurrenceType = reminderDetails.getRecurrenceType().getValue();
        Log.i("Recurrence Type to View: ", recurrenceType);
        recurrenceTypeSpinner.setSelection(spinnerAdapter.getPosition(recurrenceType));
    }

    private void initRecurrenceDelayView() {
        Integer recurrenceDelay = reminderDetails.getRecurrenceDelay();
        Log.i("Recurrence Delay to View: ", recurrenceDelay != null ? recurrenceDelay.toString() : "is null ");
        if (recurrenceDelay != null) {
            recurrenceDelayEt.setText(recurrenceDelay);
        }
    }

    private void initEndDateView() {
        Calendar dateTime = reminderDetails.getEndDateTime();
        Log.i("End Date to view: ", dateTime.get(DATE) + "/" + (dateTime.get(MONTH) + 1) + "/" + dateTime.get(YEAR));
        endDateTextView.setText(getString(R.string.ara_reminder_end_date, dateTime.get(DATE),
                dateTime.get(MONTH) + 1, dateTime.get(YEAR)));
    }

    private void initEndTimeView() {
        Calendar dateTime = reminderDetails.getEndDateTime();
        Log.i("End time to view: ", dateTime.get(HOUR_OF_DAY) + ":" + dateTime.get(MINUTE));
        endTimeTextView.setText(getString(R.string.ara_reminder_end_time, dateTime.get(HOUR_OF_DAY), dateTime.get(MINUTE)));
    }
}