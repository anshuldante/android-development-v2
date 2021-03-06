package com.example.reminderone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reminderone.R;
import com.example.reminderone.model.ReminderDetails;

import java.util.Calendar;
import java.util.List;

public class ReminderItemAdapter extends RecyclerView.Adapter<ReminderItemAdapter.ReminderItemViewHolder> {
    private final List<ReminderDetails> dataset;
    private final Context context;

    public ReminderItemAdapter(List<ReminderDetails> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    @NonNull
    @Override
    public ReminderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_reminder, parent, false);
        return new ReminderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderItemAdapter.ReminderItemViewHolder holder, int position) {
        ReminderDetails reminderDetails = dataset.get(position);
        holder.reminderName.setText(reminderDetails.getName());

        Calendar startDateTime = reminderDetails.getStartDateTime();

        holder.reminderTime.setText(context.getString(R.string.ria_reminder_time, startDateTime.get(Calendar.HOUR_OF_DAY), startDateTime.get(Calendar.HOUR_OF_DAY)));

        holder.reminderDate.setText(context.getString(R.string.ria_reminder_date, startDateTime.get(Calendar.DATE), startDateTime.get(Calendar.MONTH), startDateTime.get(Calendar.YEAR)));

        holder.activeSwitch.setChecked(reminderDetails.isActive());

        holder.nextOccurrenceDelay.setText(context.getString(R.string.ria_occurrence_delay, 1, "hours", 3, "minutes"));

        if (reminderDetails.getRecurrenceType() != null) {
            Calendar endDateTime = reminderDetails.getEndDateTime();
            holder.recurrenceDetails.setText(context.getString(R.string.ria_recurrence_details, reminderDetails.getRecurrenceDelay(), reminderDetails.getRecurrenceType()));
            holder.endDateTime.setText(context.getString(R.string.ria_end_date_time, endDateTime.get(Calendar.HOUR_OF_DAY),
                    endDateTime.get(Calendar.MINUTE), endDateTime.get(Calendar.DATE), endDateTime.get(Calendar.MONTH), endDateTime.get(Calendar.YEAR)));
        } else {
            holder.recurrenceDetails.setVisibility(View.GONE);
            holder.endDateTime.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ReminderItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView reminderName;
        private final TextView reminderTime;
        private final TextView recurrenceDetails;
        private final SwitchCompat activeSwitch;
        private final TextView reminderDate;
        private final TextView nextOccurrenceDelay;
        private final TextView endDateTime;

        public ReminderItemViewHolder(View itemView) {
            super(itemView);
            reminderName = itemView.findViewById(R.id.rir_tv_reminderName);
            reminderTime = itemView.findViewById(R.id.rir_tv_reminder_time);
            recurrenceDetails = itemView.findViewById(R.id.rir_tv_reminder_recurrence_details);
            activeSwitch = itemView.findViewById(R.id.rir_sw_active);
            reminderDate = itemView.findViewById(R.id.rir_tv_reminder_date);
            nextOccurrenceDelay = itemView.findViewById(R.id.rir_tv_next_occurrence_delay);
            endDateTime = itemView.findViewById(R.id.rir_tv_end_date_time);
        }
    }
}
