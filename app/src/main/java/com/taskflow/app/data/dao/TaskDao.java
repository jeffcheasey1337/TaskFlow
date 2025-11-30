package com.taskflow.app.data.dao;

import androidx.room.*;
import com.taskflow.app.data.model.Task;
import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insertTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("SELECT * FROM tasks ORDER BY sortOrder ASC")
    List<Task> getAllTasks();

    @Query("SELECT * FROM tasks WHERE category = :category ORDER BY sortOrder ASC")
    List<Task> getTasksByCategory(String category);

    @Query("SELECT * FROM tasks WHERE priority = :priority ORDER BY sortOrder ASC")
    List<Task> getTasksByPriority(String priority);

    @Query("SELECT * FROM tasks WHERE title LIKE '%' || :query || '%' OR project LIKE '%' || :query || '%'")
    List<Task> searchTasks(String query);

    @Query("SELECT COUNT(*) FROM tasks WHERE completed = 1")
    int getCompletedTaskCount();

    @Query("SELECT COUNT(*) FROM tasks WHERE completed = 0")
    int getActiveTaskCount();

    @Query("SELECT COUNT(*) FROM tasks WHERE deadline > 0 AND deadline < :currentTime AND completed = 0")
    int getOverdueTaskCount(long currentTime);

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    Task getTaskById(int taskId);

    // Новые методы для расширенного функционала

    @Query("SELECT * FROM tasks WHERE deadline >= :startTime AND deadline <= :endTime ORDER BY deadline ASC")
    List<Task> getTasksForDateRange(long startTime, long endTime);

    @Query("SELECT COUNT(*) FROM tasks WHERE deadline >= :startTime AND deadline <= :endTime")
    int getTaskCountForDate(long startTime, long endTime);

    @Query("SELECT COUNT(*) FROM tasks WHERE completed = 1 AND deadline >= :startTime AND deadline <= :endTime")
    int getCompletedTaskCountForDate(long startTime, long endTime);

    @Query("SELECT COUNT(DISTINCT category) FROM tasks")
    int getDistinctCategoriesCount();

    @Query("SELECT * FROM tasks WHERE isFavorite = 1 ORDER BY sortOrder ASC")
    List<Task> getFavoriteTasks();

    @Query("SELECT * FROM tasks WHERE repeatType != 'none' ORDER BY sortOrder ASC")
    List<Task> getRepeatingTasks();

    @Query("SELECT * FROM tasks WHERE deadline > 0 AND deadline >= :startTime AND deadline <= :endTime ORDER BY deadline ASC")
    List<Task> getTasksWithDeadlineInRange(long startTime, long endTime);

    @Query("DELETE FROM tasks WHERE completed = 1")
    void deleteCompletedTasks();

    @Query("SELECT * FROM tasks ORDER BY createdAt DESC LIMIT :limit")
    List<Task> getRecentTasks(int limit);

    @Query("SELECT * FROM tasks WHERE project = :projectName ORDER BY sortOrder ASC")
    List<Task> getTasksByProject(String projectName);

    @Query("SELECT DISTINCT project FROM tasks WHERE project != 'Без проекта'")
    List<String> getAllProjects();


}