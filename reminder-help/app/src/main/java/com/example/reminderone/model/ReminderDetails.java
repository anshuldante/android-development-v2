package com.example.reminderone.model;

import java.util.Date;
import java.util.UUID;

public class ReminderDetails {

    private String id;
    private boolean active;
    private String name;

    private int startDateYear;
    private int startDateMonth;
    private int startDateDay;

    private int startTimeHour;
    private int startTimeMinute;

    private boolean isRecurring;

    private int endDateYear;
    private int endDateMonth;
    private int endDateDay;

    private int endTimeHour;
    private int endTimeMinute;

    private Date createdDate;
    private Date modifiedDate;

    private ReminderDetails(String id, boolean active, String name, int startDateYear, int startDateMonth, int startDateDay, int startTimeHour, int startTimeMinute, boolean isRecurring, int endDateYear, int endDateMonth, int endDateDay, int endTimeHour, int endTimeMinute, Date createdDate, Date modifiedDate) {
        this.id = id;
        this.active = active;
        this.name = name;
        this.startDateYear = startDateYear;
        this.startDateMonth = startDateMonth;
        this.startDateDay = startDateDay;
        this.startTimeHour = startTimeHour;
        this.startTimeMinute = startTimeMinute;
        this.isRecurring = isRecurring;
        this.endDateYear = endDateYear;
        this.endDateMonth = endDateMonth;
        this.endDateDay = endDateDay;
        this.endTimeHour = endTimeHour;
        this.endTimeMinute = endTimeMinute;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
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

    public int getStartDateYear() {
        return startDateYear;
    }

    public void setStartDateYear(int startDateYear) {
        this.startDateYear = startDateYear;
    }

    public int getStartDateMonth() {
        return startDateMonth;
    }

    public void setStartDateMonth(int startDateMonth) {
        this.startDateMonth = startDateMonth;
    }

    public int getStartDateDay() {
        return startDateDay;
    }

    public void setStartDateDay(int startDateDay) {
        this.startDateDay = startDateDay;
    }

    public int getStartTimeHour() {
        return startTimeHour;
    }

    public void setStartTimeHour(int startTimeHour) {
        this.startTimeHour = startTimeHour;
    }

    public int getStartTimeMinute() {
        return startTimeMinute;
    }

    public void setStartTimeMinute(int startTimeMinute) {
        this.startTimeMinute = startTimeMinute;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public int getEndDateYear() {
        return endDateYear;
    }

    public void setEndDateYear(int endDateYear) {
        this.endDateYear = endDateYear;
    }

    public int getEndDateMonth() {
        return endDateMonth;
    }

    public void setEndDateMonth(int endDateMonth) {
        this.endDateMonth = endDateMonth;
    }

    public int getEndDateDay() {
        return endDateDay;
    }

    public void setEndDateDay(int endDateDay) {
        this.endDateDay = endDateDay;
    }

    public int getEndTimeHour() {
        return endTimeHour;
    }

    public void setEndTimeHour(int endTimeHour) {
        this.endTimeHour = endTimeHour;
    }

    public int getEndTimeMinute() {
        return endTimeMinute;
    }

    public void setEndTimeMinute(int endTimeMinute) {
        this.endTimeMinute = endTimeMinute;
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

    public static ReminderDetailsBuilder builder() {
        return new ReminderDetailsBuilder();
    }

    public static class ReminderDetailsBuilder {

        private String id;
        private boolean active;
        private String name;

        private int startDateYear;
        private int startDateMonth;
        private int startDateDay;

        private int startTimeHour;
        private int startTimeMinute;

        private boolean isRecurring;

        private int endDateYear;
        private int endDateMonth;
        private int endDateDay;

        private int endTimeHour;
        private int endTimeMinute;

        private Date createdDate;
        private Date modifiedDate;

        private ReminderDetailsBuilder() {
            this.id = UUID.randomUUID().toString();
            this.active = true;
            this.createdDate = new Date();
            this.modifiedDate = new Date();
        }

        public ReminderDetails build() {
            return new ReminderDetails(id, active, name, startDateYear, startDateMonth, startDateDay, startTimeHour, startTimeMinute, isRecurring, endDateYear, endDateMonth, endDateDay, endTimeHour, endTimeMinute, createdDate, modifiedDate);
        }

        public ReminderDetailsBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ReminderDetailsBuilder active(boolean active) {
            this.active = active;
            return this;
        }

        public ReminderDetailsBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ReminderDetailsBuilder startDateYear(int startDateYear) {
            this.startDateYear = startDateYear;
            return this;
        }

        public ReminderDetailsBuilder startDateMonth(int startDateMonth) {
            this.startDateMonth = startDateMonth;
            return this;
        }

        public ReminderDetailsBuilder startDateDay(int startDateDay) {
            this.startDateDay = startDateDay;
            return this;
        }

        public ReminderDetailsBuilder startTimeHour(int startTimeHour) {
            this.startTimeHour = startTimeHour;
            return this;
        }

        public ReminderDetailsBuilder startTimeMinute(int startTimeMinute) {
            this.startTimeMinute = startTimeMinute;
            return this;
        }

        public ReminderDetailsBuilder isRecurring(boolean isRecurring) {
            this.isRecurring = isRecurring;
            return this;
        }

        public ReminderDetailsBuilder endDateYear(int endDateYear) {
            this.endDateYear = endDateYear;
            return this;
        }

        public ReminderDetailsBuilder endDateMonth(int endDateMonth) {
            this.endDateMonth = endDateMonth;
            return this;
        }

        public ReminderDetailsBuilder endDateDay(int endDateDay) {
            this.endDateDay = endDateDay;
            return this;
        }

        public ReminderDetailsBuilder endTimeHour(int endTimeHour) {
            this.endTimeHour = endTimeHour;
            return this;
        }

        public ReminderDetailsBuilder endTimeMinute(int endTimeMinute) {
            this.endTimeMinute = endTimeMinute;
            return this;
        }

        public ReminderDetailsBuilder createdDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public ReminderDetailsBuilder modifiedDate(Date modifiedDate) {
            this.modifiedDate = modifiedDate;
            return this;
        }
    }
}
