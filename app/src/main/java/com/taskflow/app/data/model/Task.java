package com.taskflow.app.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.taskflow.app.data.converters.StringListConverter;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "tasks")
@TypeConverters({StringListConverter.class})
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String project;
    private String priority;
    private boolean completed;
    private long createdAt;
    private String category;
    private long deadline;
    private boolean hasReminder;
    private int sortOrder;

    // Новые поля для расширенного функционала
    private String description;
    private List<String> tags;
    private List<String> subtasks;
    private int completedSubtasks;
    private String repeatType; // none, daily, weekly, monthly
    private long repeatEndDate;
    private int pomodoroCount;
    private long estimatedTime; // в минутах
    private long actualTime; // в минутах
    private String color; // для кастомных цветов
    private boolean isFavorite;
    private int streakDays;

    public Task(String title, String project, String priority) {
        this.title = title;
        this.project = project;
        this.priority = priority;
        this.completed = false;
        this.createdAt = System.currentTimeMillis();
        this.category = "personal";
        this.deadline = 0;
        this.hasReminder = false;
        this.sortOrder = 0;

        // Инициализация новых полей
        this.description = "";
        this.tags = new ArrayList<>();
        this.subtasks = new ArrayList<>();
        this.completedSubtasks = 0;
        this.repeatType = "none";
        this.repeatEndDate = 0;
        this.pomodoroCount = 0;
        this.estimatedTime = 0;
        this.actualTime = 0;
        this.color = "";
        this.isFavorite = false;
        this.streakDays = 0;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getProject() { return project; }
    public void setProject(String project) { this.project = project; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public long getDeadline() { return deadline; }
    public void setDeadline(long deadline) { this.deadline = deadline; }

    public boolean hasReminder() { return hasReminder; }
    public void setHasReminder(boolean hasReminder) { this.hasReminder = hasReminder; }

    public int getSortOrder() { return sortOrder; }
    public void setSortOrder(int sortOrder) { this.sortOrder = sortOrder; }

    // Новые геттеры/сеттеры
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    public List<String> getSubtasks() { return subtasks; }
    public void setSubtasks(List<String> subtasks) { this.subtasks = subtasks; }

    public int getCompletedSubtasks() { return completedSubtasks; }
    public void setCompletedSubtasks(int completedSubtasks) { this.completedSubtasks = completedSubtasks; }

    public String getRepeatType() { return repeatType; }
    public void setRepeatType(String repeatType) { this.repeatType = repeatType; }

    public long getRepeatEndDate() { return repeatEndDate; }
    public void setRepeatEndDate(long repeatEndDate) { this.repeatEndDate = repeatEndDate; }

    public int getPomodoroCount() { return pomodoroCount; }
    public void setPomodoroCount(int pomodoroCount) { this.pomodoroCount = pomodoroCount; }

    public long getEstimatedTime() { return estimatedTime; }
    public void setEstimatedTime(long estimatedTime) { this.estimatedTime = estimatedTime; }

    public long getActualTime() { return actualTime; }
    public void setActualTime(long actualTime) { this.actualTime = actualTime; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }

    public int getStreakDays() { return streakDays; }
    public void setStreakDays(int streakDays) { this.streakDays = streakDays; }
}