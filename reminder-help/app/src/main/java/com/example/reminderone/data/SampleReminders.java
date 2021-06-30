package com.example.reminderone.data;

import com.example.reminderone.model.ReminderDetails;

import java.util.ArrayList;
import java.util.List;

public class SampleReminders {

    public static List<ReminderDetails> getSampleReminderList() {
        List<ReminderDetails> list = new ArrayList<>(5);

        list.add(ReminderDetails.builder().name("One").startDateYear(2021).startDateMonth(7).startDateDay(1).startTimeHour(10).startTimeMinute(30).build());
        list.add(ReminderDetails.builder().name("Two").startDateYear(2021).startDateMonth(7).startDateDay(2).startTimeHour(1).startTimeMinute(30).build());
        list.add(ReminderDetails.builder().name("Three").startDateYear(2021).startDateMonth(7).startDateDay(3).startTimeHour(12).startTimeMinute(30).build());
        list.add(ReminderDetails.builder().name("Four").startDateYear(2021).startDateMonth(7).startDateDay(4).startTimeHour(13).startTimeMinute(30).build());
        list.add(ReminderDetails.builder().name("Five").startDateYear(2021).startDateMonth(7).startDateDay(5).startTimeHour(14).startTimeMinute(30).build());
        return list;
    }
}
