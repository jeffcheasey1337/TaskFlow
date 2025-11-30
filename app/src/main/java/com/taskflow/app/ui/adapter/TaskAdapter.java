package com.taskflow.app.ui.adapter;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.taskflow.app.R;
import com.taskflow.app.data.model.Task;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> tasks = new ArrayList<>();
    private OnTaskClickListener listener;

    public interface OnTaskClickListener {
        void onTaskCheck(Task task);
        void onTaskDelete(Task task);
        void onTaskEdit(Task task);
    }

    public TaskAdapter(OnTaskClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.bind(task);

        // Анимация появления (iOS style)
        holder.itemView.setAlpha(0f);
        holder.itemView.setTranslationY(50f);
        holder.itemView.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(300)
                .setStartDelay(position * 50L)
                .start();
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView titleText;
        TextView projectTag;
        TextView deadlineText;
        View priorityIndicator;
        View reminderIcon;
        ImageButton editButton;
        ImageButton deleteButton;
        CardView taskCard;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            titleText = itemView.findViewById(R.id.titleText);
            projectTag = itemView.findViewById(R.id.projectTag);
            deadlineText = itemView.findViewById(R.id.deadlineText);
            priorityIndicator = itemView.findViewById(R.id.priorityIndicator);
            reminderIcon = itemView.findViewById(R.id.reminderIcon);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            taskCard = itemView.findViewById(R.id.taskCard);
        }

        public void bind(Task task) {
            titleText.setText(task.getTitle());
            projectTag.setText(task.getProject());
            checkBox.setChecked(task.isCompleted());

            // Зачеркивание выполненных задач
            if (task.isCompleted()) {
                titleText.setPaintFlags(titleText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                titleText.setAlpha(0.5f);
                projectTag.setAlpha(0.5f);
            } else {
                titleText.setPaintFlags(titleText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                titleText.setAlpha(1f);
                projectTag.setAlpha(1f);
            }

            // Цвет приоритета
            int priorityColor = getPriorityColor(task.getPriority());
            priorityIndicator.setBackgroundColor(priorityColor);

            // Дедлайн
            if (task.getDeadline() > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, HH:mm", new Locale("ru"));
                deadlineText.setText("• " + sdf.format(new Date(task.getDeadline())));
                deadlineText.setVisibility(View.VISIBLE);

                // Проверка просроченности
                if (task.getDeadline() < System.currentTimeMillis() && !task.isCompleted()) {
                    deadlineText.setTextColor(Color.parseColor("#FF3B30")); // iOS Red
                } else {
                    deadlineText.setTextColor(Color.parseColor("#8E8E93")); // iOS Secondary
                }
            } else {
                deadlineText.setVisibility(View.GONE);
            }

            // Напоминание
            reminderIcon.setVisibility(task.hasReminder() ? View.VISIBLE : View.GONE);

            // Слушатели с анимацией
            checkBox.setOnClickListener(v -> {
                // Анимация нажатия
                checkBox.animate()
                        .scaleX(0.8f)
                        .scaleY(0.8f)
                        .setDuration(100)
                        .withEndAction(() -> {
                            checkBox.animate()
                                    .scaleX(1f)
                                    .scaleY(1f)
                                    .setDuration(100)
                                    .start();
                        })
                        .start();

                listener.onTaskCheck(task);
            });

            editButton.setOnClickListener(v -> {
                animateButton(editButton);
                listener.onTaskEdit(task);
            });

            deleteButton.setOnClickListener(v -> {
                animateButton(deleteButton);
                // Анимация удаления
                itemView.animate()
                        .alpha(0f)
                        .translationX(-itemView.getWidth())
                        .setDuration(300)
                        .withEndAction(() -> listener.onTaskDelete(task))
                        .start();
            });

            // Клик по всей карточке
            taskCard.setOnClickListener(v -> {
                animateCard(taskCard);
                listener.onTaskEdit(task);
            });
        }

        private int getPriorityColor(String priority) {
            switch (priority.toLowerCase()) {
                case "high":
                case "высокий":
                    return Color.parseColor("#FF3B30"); // iOS Red
                case "medium":
                case "средний":
                    return Color.parseColor("#FF9500"); // iOS Orange
                case "low":
                case "низкий":
                    return Color.parseColor("#34C759"); // iOS Green
                default:
                    return Color.parseColor("#007AFF"); // iOS Blue
            }
        }

        private void animateButton(View button) {
            button.animate()
                    .scaleX(0.85f)
                    .scaleY(0.85f)
                    .setDuration(100)
                    .withEndAction(() -> {
                        button.animate()
                                .scaleX(1f)
                                .scaleY(1f)
                                .setDuration(100)
                                .start();
                    })
                    .start();
        }

        private void animateCard(View card) {
            card.animate()
                    .scaleX(0.98f)
                    .scaleY(0.98f)
                    .setDuration(100)
                    .withEndAction(() -> {
                        card.animate()
                                .scaleX(1f)
                                .scaleY(1f)
                                .setDuration(100)
                                .start();
                    })
                    .start();
        }
    }
}