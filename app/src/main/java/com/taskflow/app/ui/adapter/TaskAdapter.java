package com.taskflow.app.ui.adapter;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.taskflow.app.R;
import com.taskflow.app.data.model.Task;
import java.text.SimpleDateFormat;
import java.util.*;

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
        holder.bind(tasks.get(position));
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
        TextView titleText, projectText, priorityText, deadlineText, categoryBadge;
        ImageView deleteIcon, editIcon;

        TaskViewHolder(View v) {
            super(v);
            checkBox = v.findViewById(R.id.checkBox);
            titleText = v.findViewById(R.id.titleText);
            projectText = v.findViewById(R.id.projectText);
            priorityText = v.findViewById(R.id.priorityText);
            deadlineText = v.findViewById(R.id.deadlineText);
            categoryBadge = v.findViewById(R.id.categoryBadge);
            deleteIcon = v.findViewById(R.id.deleteIcon);
            editIcon = v.findViewById(R.id.editIcon);
        }

        void bind(Task task) {
            titleText.setText(task.getTitle());
            projectText.setText("📁 " + task.getProject());
            checkBox.setChecked(task.isCompleted());

            // Приоритет с цветами
            String priorityEmoji = getPriorityEmoji(task.getPriority());
            priorityText.setText(priorityEmoji + " " + getPriorityText(task.getPriority()));
            priorityText.setTextColor(getPriorityColor(task.getPriority()));

            // Категория
            categoryBadge.setText(getCategoryEmoji(task.getCategory()));
            categoryBadge.setVisibility(View.VISIBLE);

            // Дедлайн
            if (task.getDeadline() > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM HH:mm", Locale.getDefault());
                deadlineText.setText("⏰ " + sdf.format(new Date(task.getDeadline())));
                deadlineText.setVisibility(View.VISIBLE);

                // Подсветка просроченных
                if (task.getDeadline() < System.currentTimeMillis() && !task.isCompleted()) {
                    deadlineText.setTextColor(Color.RED);
                } else {
                    deadlineText.setTextColor(Color.GRAY);
                }
            } else {
                deadlineText.setVisibility(View.GONE);
            }

            // Зачеркивание выполненных
            if (task.isCompleted()) {
                titleText.setPaintFlags(titleText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                titleText.setAlpha(0.5f);
            } else {
                titleText.setPaintFlags(titleText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                titleText.setAlpha(1.0f);
            }

            checkBox.setOnClickListener(v -> listener.onTaskCheck(task));
            deleteIcon.setOnClickListener(v -> listener.onTaskDelete(task));
            editIcon.setOnClickListener(v -> listener.onTaskEdit(task));
        }

        private String getPriorityEmoji(String priority) {
            switch (priority) {
                case "high": return "🔴";
                case "medium": return "🟡";
                case "low": return "🟢";
                default: return "⚪";
            }
        }

        private String getPriorityText(String priority) {
            switch (priority) {
                case "high": return "Высокий";
                case "medium": return "Средний";
                case "low": return "Низкий";
                default: return "Средний";
            }
        }

        private int getPriorityColor(String priority) {
            switch (priority) {
                case "high": return Color.parseColor("#F44336");
                case "medium": return Color.parseColor("#FF9800");
                case "low": return Color.parseColor("#4CAF50");
                default: return Color.GRAY;
            }
        }

        private String getCategoryEmoji(String category) {
            switch (category) {
                case "work": return "💼";
                case "personal": return "👤";
                case "shopping": return "🛒";
                case "health": return "💊";
                default: return "📌";
            }
        }
    }
}