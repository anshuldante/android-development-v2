package com.test.notesapptrial.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.test.notesapptrial.R;
import com.test.notesapptrial.model.Note;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteViewHolder> {
  private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK =
      new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
          return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
          return oldItem.equals(newItem);
        }
      };
  private OnItemCLickListener listener;

  public NoteAdapter() {
    super(DIFF_CALLBACK);
  }

  @NonNull
  @Override
  public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new NoteViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
    Note note = getItem(position);
    holder.textViewTitle.setText(note.getTitle());
    holder.textViewPriority.setText(String.valueOf(note.getPriority()));
    holder.textViewDescription.setText(note.getDescription());
  }

  public Note getNoteAt(int position) {
    return getItem(position);
  }

  class NoteViewHolder extends RecyclerView.ViewHolder {

    private final TextView textViewTitle;
    private final TextView textViewDescription;
    private final TextView textViewPriority;

    public NoteViewHolder(@NonNull View itemView) {
      super(itemView);
      textViewTitle = itemView.findViewById(R.id.text_view_title);
      textViewPriority = itemView.findViewById(R.id.text_view_priority);
      textViewDescription = itemView.findViewById(R.id.text_view_description);

      itemView.setOnClickListener(
          v -> {
            int position = getAdapterPosition();
            if (listener != null && position != RecyclerView.NO_POSITION)
              listener.onItemClick(getItem(position));
          });
    }
  }

  public interface OnItemCLickListener {
    void onItemClick(Note note);
  }

  public void setOnItemClickListener(OnItemCLickListener listener) {
    this.listener = listener;
  }
}
