package com.example.reminderone.model;

import java.util.Date;
import java.util.UUID;

public class ReminderTriggerDetails {

    private String id;
    private String reminderId;
    private Date triggerDateTime;

    private Date createdDate;
    private Date modifiedDate;

    private ReminderTriggerDetails(String id, String reminderId, Date triggerDateTime, Date createdDate, Date modifiedDate) {
        this.id = id;
        this.reminderId = reminderId;
        this.triggerDateTime = triggerDateTime;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReminderId() {
        return reminderId;
    }

    public void setReminderId(String reminderId) {
        this.reminderId = reminderId;
    }

    public Date getTriggerDateTime() {
        return triggerDateTime;
    }

    public void setTriggerDateTime(Date triggerDateTime) {
        this.triggerDateTime = triggerDateTime;
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

    public Builder builder() {
        return new Builder();
    }

    private static class Builder {


        private String id;
        private String reminderId;
        private Date triggerDateTime;

        private Date createdDate;
        private Date modifiedDate;

        public Builder() {
            this.reminderId = UUID.randomUUID().toString();
            this.createdDate = new Date();
            this.modifiedDate = new Date();
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder reminderId(String reminderId) {
            this.reminderId = reminderId;
            return this;
        }

        public Builder triggerDateTime(Date triggerDateTime) {
            this.triggerDateTime = triggerDateTime;
            return this;
        }

        public Builder createdDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder modifiedDate(Date modifiedDate) {
            this.modifiedDate = modifiedDate;
            return this;
        }
    }
}
