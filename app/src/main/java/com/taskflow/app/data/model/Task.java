package com.taskflow.app.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String project;
    private String priority; // high, medium, low
    private String category; // work, personal, shopping, health, other
    private boolean completed;
    private long createdAt;
    private long deadline; // NEW: дедлайн
    private boolean hasReminder; // NEW: есть ли напоминание
    private int sortOrder; // NEW: для drag & drop

    public Task(String title, String project, String priority) {
        this.title = title;
        this.project = project;
        this.priority = priority;
        this.category = "personal";
        this.completed = false;
        this.createdAt = System.currentTimeMillis();
        this.deadline = 0;
        this.hasReminder = false;
        this.sortOrder = 0;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getProject() { return project; }
    public String getPriority() { return priority; }
    public String getCategory() { return category; }
    public boolean isCompleted() { return completed; }
    public long getCreatedAt() { return createdAt; }
    public long getDeadline() { return deadline; }
    public boolean hasReminder() { return hasReminder; }
    public int getSortOrder() { return sortOrder; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setProject(String project) { this.project = project; }
    public void setPriority(String priority) { this.priority = priority; }
    public void setCategory(String category) { this.category = category; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
    public void setDeadline(long deadline) { this.deadline = deadline; }
    public void setHasReminder(boolean hasReminder) { this.hasReminder = hasReminder; }
    public void setSortOrder(int sortOrder) { this.sortOrder = sortOrder; }
}