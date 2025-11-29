package com.taskflow.app.data.dao;

import androidx.room.*;
import com.taskflow.app.data.model.Task;
import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY sortOrder ASC, createdAt DESC")
    List<Task> getAllTasks();

    @Query("SELECT * FROM tasks WHERE title LIKE '%' || :query || '%' OR project LIKE '%' || :query || '%'")
    List<Task> searchTasks(String query);

    @Query("SELECT * FROM tasks WHERE category = :category ORDER BY createdAt DESC")
    List<Task> getTasksByCategory(String category);

    @Query("SELECT * FROM tasks WHERE priority = :priority ORDER BY createdAt DESC")
    List<Task> getTasksByPriority(String priority);

    @Insert
    long insertTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("SELECT COUNT(*) FROM tasks WHERE completed = 1")
    int getCompletedTaskCount();

    @Query("SELECT COUNT(*) FROM tasks WHERE completed = 0")
    int getActiveTaskCount();

    @Query("SELECT COUNT(*) FROM tasks WHERE deadline > 0 AND deadline < :timestamp AND completed = 0")
    int getOverdueTaskCount(long timestamp);
}