package com.example.reminderone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reminderone.adapter.ReminderItemAdapter;
import com.example.reminderone.data.SampleReminders;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ReminderItemAdapter reminderItemAdapter;
    private FloatingActionButton addReminderButton;
    private RecyclerView reminderRecyclerView;
    private TextView emptyReminderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addReminderButton = findViewById(R.id.am_fab_add_reminder);
        addReminderButton.setOnClickListener(v -> startActivity(new Intent(this, AddReminderActivity.class)));

        emptyReminderList = findViewById(R.id.am_tv_no_reminders);
        emptyReminderList.setVisibility(View.GONE);

        reminderItemAdapter = new ReminderItemAdapter(SampleReminders.getSampleReminderList(), this);

        reminderRecyclerView = findViewById(R.id.am_rv_reminders);

        reminderRecyclerView.setAdapter(reminderItemAdapter);
    }
}