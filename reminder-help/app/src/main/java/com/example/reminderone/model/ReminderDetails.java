package com.example.reminderone.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class ReminderDetails {

    private String id;
    private boolean active;
    private String name;

    private Calendar startDateTime;

    private Integer recurrenceDelay;
    private RecurrenceType recurrenceType;

    private Calendar endDateTime;

    private Date createdDate;
    private Date modifiedDate;

    public ReminderDetails() {
        this.active = true;
        this.createdDate = new Date();
        this.modifiedDate = new Date();
        this.id = UUID.randomUUID().toString();
        this.recurrenceType = RecurrenceType.DAY;
        this.endDateTime = Calendar.getInstance();
        this.startDateTime = Calendar.getInstance();
    }

    public ReminderDetails(String name) {
        this();
        this.name = name;
    }

    public ReminderDetails(String name, Integer recurrenceDelay, RecurrenceType recurrenceType, Calendar endDateTime) {
        this(name);
        this.recurrenceDelay = recurrenceDelay;
        this.recurrenceType = recurrenceType;
        this.endDateTime = endDateTime;
    }

    public ReminderDetails(ReminderDetails reminderDetails) {
        this.id = reminderDetails.getId();
        this.active = reminderDetails.isActive();
        this.name = reminderDetails.getName();
        this.startDateTime = reminderDetails.getStartDateTime();
        this.recurrenceDelay = reminderDetails.getRecurrenceDelay();
        this.recurrenceType = reminderDetails.getRecurrenceType();
        this.endDateTime = reminderDetails.getEndDateTime();
        this.createdDate = reminderDetails.getCreatedDate();
        this.modifiedDate = reminderDetails.getModifiedDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getRecurrenceDelay() {
        return recurrenceDelay;
    }

    public void setRecurrenceDelay(Integer recurrenceDelay) {
        this.recurrenceDelay = recurrenceDelay;
    }

    public RecurrenceType getRecurrenceType() {
        return recurrenceType;
    }

    public void setRecurrenceType(RecurrenceType recurrenceType) {
        this.recurrenceType = recurrenceType;
    }

    public Calendar getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Calendar startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Calendar getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Calendar endDateTime) {
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "ReminderDetails{" +
                "id='" + id + '\'' +
                ", active=" + active +
                ", name='" + name + '\'' +
                ", startDateTime=" + startDateTime.getTime() +
                ", recurrenceDelay=" + recurrenceDelay +
                ", recurrenceType=" + recurrenceType +
                ", endDateTime=" + endDateTime.getTime() +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
