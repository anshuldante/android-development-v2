package com.example.reminderone.data;

import com.example.reminderone.model.ReminderDetails;

import java.util.ArrayList;
import java.util.List;

public class SampleReminders {

    public static List<ReminderDetails> getSampleReminderList() {
        List<ReminderDetails> list = new ArrayList<>(10);

        list.add(new ReminderDetails("One"));
        list.add(new ReminderDetails("Two"));
        list.add(new ReminderDetails("Three"));
        list.add(new ReminderDetails("Four"));
        list.add(new ReminderDetails("Five"));

        list.add(new ReminderDetails("Six"));
        list.add(new ReminderDetails("Seven"));
        list.add(new ReminderDetails("Eight"));
        list.add(new ReminderDetails("Nine"));
        list.add(new ReminderDetails("Ten"));

        return list;
    }
}
