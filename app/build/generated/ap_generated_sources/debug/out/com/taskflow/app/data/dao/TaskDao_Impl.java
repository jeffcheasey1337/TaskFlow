package com.taskflow.app.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.taskflow.app.data.converters.StringListConverter;
import com.taskflow.app.data.model.Task;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TaskDao_Impl implements TaskDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Task> __insertionAdapterOfTask;

  private final EntityDeletionOrUpdateAdapter<Task> __deletionAdapterOfTask;

  private final EntityDeletionOrUpdateAdapter<Task> __updateAdapterOfTask;

  private final SharedSQLiteStatement __preparedStmtOfDeleteCompletedTasks;

  public TaskDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTask = new EntityInsertionAdapter<Task>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `tasks` (`id`,`title`,`project`,`priority`,`completed`,`createdAt`,`category`,`deadline`,`hasReminder`,`sortOrder`,`description`,`tags`,`subtasks`,`completedSubtasks`,`repeatType`,`repeatEndDate`,`pomodoroCount`,`estimatedTime`,`actualTime`,`color`,`isFavorite`,`streakDays`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Task entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getProject() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getProject());
        }
        if (entity.getPriority() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPriority());
        }
        final int _tmp = entity.isCompleted() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getCreatedAt());
        if (entity.getCategory() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCategory());
        }
        statement.bindLong(8, entity.getDeadline());
        final int _tmp_1 = entity.hasReminder() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
        statement.bindLong(10, entity.getSortOrder());
        if (entity.getDescription() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getDescription());
        }
        final String _tmp_2 = StringListConverter.fromList(entity.getTags());
        if (_tmp_2 == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, _tmp_2);
        }
        final String _tmp_3 = StringListConverter.fromList(entity.getSubtasks());
        if (_tmp_3 == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, _tmp_3);
        }
        statement.bindLong(14, entity.getCompletedSubtasks());
        if (entity.getRepeatType() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getRepeatType());
        }
        statement.bindLong(16, entity.getRepeatEndDate());
        statement.bindLong(17, entity.getPomodoroCount());
        statement.bindLong(18, entity.getEstimatedTime());
        statement.bindLong(19, entity.getActualTime());
        if (entity.getColor() == null) {
          statement.bindNull(20);
        } else {
          statement.bindString(20, entity.getColor());
        }
        final int _tmp_4 = entity.isFavorite() ? 1 : 0;
        statement.bindLong(21, _tmp_4);
        statement.bindLong(22, entity.getStreakDays());
      }
    };
    this.__deletionAdapterOfTask = new EntityDeletionOrUpdateAdapter<Task>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `tasks` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Task entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfTask = new EntityDeletionOrUpdateAdapter<Task>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `tasks` SET `id` = ?,`title` = ?,`project` = ?,`priority` = ?,`completed` = ?,`createdAt` = ?,`category` = ?,`deadline` = ?,`hasReminder` = ?,`sortOrder` = ?,`description` = ?,`tags` = ?,`subtasks` = ?,`completedSubtasks` = ?,`repeatType` = ?,`repeatEndDate` = ?,`pomodoroCount` = ?,`estimatedTime` = ?,`actualTime` = ?,`color` = ?,`isFavorite` = ?,`streakDays` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Task entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getProject() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getProject());
        }
        if (entity.getPriority() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPriority());
        }
        final int _tmp = entity.isCompleted() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getCreatedAt());
        if (entity.getCategory() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCategory());
        }
        statement.bindLong(8, entity.getDeadline());
        final int _tmp_1 = entity.hasReminder() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
        statement.bindLong(10, entity.getSortOrder());
        if (entity.getDescription() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getDescription());
        }
        final String _tmp_2 = StringListConverter.fromList(entity.getTags());
        if (_tmp_2 == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, _tmp_2);
        }
        final String _tmp_3 = StringListConverter.fromList(entity.getSubtasks());
        if (_tmp_3 == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, _tmp_3);
        }
        statement.bindLong(14, entity.getCompletedSubtasks());
        if (entity.getRepeatType() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getRepeatType());
        }
        statement.bindLong(16, entity.getRepeatEndDate());
        statement.bindLong(17, entity.getPomodoroCount());
        statement.bindLong(18, entity.getEstimatedTime());
        statement.bindLong(19, entity.getActualTime());
        if (entity.getColor() == null) {
          statement.bindNull(20);
        } else {
          statement.bindString(20, entity.getColor());
        }
        final int _tmp_4 = entity.isFavorite() ? 1 : 0;
        statement.bindLong(21, _tmp_4);
        statement.bindLong(22, entity.getStreakDays());
        statement.bindLong(23, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteCompletedTasks = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM tasks WHERE completed = 1";
        return _query;
      }
    };
  }

  @Override
  public void insertTask(final Task task) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTask.insert(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTask(final Task task) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfTask.handle(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateTask(final Task task) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTask.handle(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteCompletedTasks() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteCompletedTasks.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteCompletedTasks.release(_stmt);
    }
  }

  @Override
  public List<Task> getAllTasks() {
    final String _sql = "SELECT * FROM tasks ORDER BY sortOrder ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfProject = CursorUtil.getColumnIndexOrThrow(_cursor, "project");
      final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
      final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
      final int _cursorIndexOfHasReminder = CursorUtil.getColumnIndexOrThrow(_cursor, "hasReminder");
      final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
      final int _cursorIndexOfSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "subtasks");
      final int _cursorIndexOfCompletedSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "completedSubtasks");
      final int _cursorIndexOfRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatType");
      final int _cursorIndexOfRepeatEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatEndDate");
      final int _cursorIndexOfPomodoroCount = CursorUtil.getColumnIndexOrThrow(_cursor, "pomodoroCount");
      final int _cursorIndexOfEstimatedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedTime");
      final int _cursorIndexOfActualTime = CursorUtil.getColumnIndexOrThrow(_cursor, "actualTime");
      final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
      final int _cursorIndexOfStreakDays = CursorUtil.getColumnIndexOrThrow(_cursor, "streakDays");
      final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Task _item;
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpProject;
        if (_cursor.isNull(_cursorIndexOfProject)) {
          _tmpProject = null;
        } else {
          _tmpProject = _cursor.getString(_cursorIndexOfProject);
        }
        final String _tmpPriority;
        if (_cursor.isNull(_cursorIndexOfPriority)) {
          _tmpPriority = null;
        } else {
          _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
        }
        _item = new Task(_tmpTitle,_tmpProject,_tmpPriority);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final boolean _tmpCompleted;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfCompleted);
        _tmpCompleted = _tmp != 0;
        _item.setCompleted(_tmpCompleted);
        final long _tmpCreatedAt;
        _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _item.setCreatedAt(_tmpCreatedAt);
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        _item.setCategory(_tmpCategory);
        final long _tmpDeadline;
        _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
        _item.setDeadline(_tmpDeadline);
        final boolean _tmpHasReminder;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfHasReminder);
        _tmpHasReminder = _tmp_1 != 0;
        _item.setHasReminder(_tmpHasReminder);
        final int _tmpSortOrder;
        _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
        _item.setSortOrder(_tmpSortOrder);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        final List<String> _tmpTags;
        final String _tmp_2;
        if (_cursor.isNull(_cursorIndexOfTags)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getString(_cursorIndexOfTags);
        }
        _tmpTags = StringListConverter.toList(_tmp_2);
        _item.setTags(_tmpTags);
        final List<String> _tmpSubtasks;
        final String _tmp_3;
        if (_cursor.isNull(_cursorIndexOfSubtasks)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getString(_cursorIndexOfSubtasks);
        }
        _tmpSubtasks = StringListConverter.toList(_tmp_3);
        _item.setSubtasks(_tmpSubtasks);
        final int _tmpCompletedSubtasks;
        _tmpCompletedSubtasks = _cursor.getInt(_cursorIndexOfCompletedSubtasks);
        _item.setCompletedSubtasks(_tmpCompletedSubtasks);
        final String _tmpRepeatType;
        if (_cursor.isNull(_cursorIndexOfRepeatType)) {
          _tmpRepeatType = null;
        } else {
          _tmpRepeatType = _cursor.getString(_cursorIndexOfRepeatType);
        }
        _item.setRepeatType(_tmpRepeatType);
        final long _tmpRepeatEndDate;
        _tmpRepeatEndDate = _cursor.getLong(_cursorIndexOfRepeatEndDate);
        _item.setRepeatEndDate(_tmpRepeatEndDate);
        final int _tmpPomodoroCount;
        _tmpPomodoroCount = _cursor.getInt(_cursorIndexOfPomodoroCount);
        _item.setPomodoroCount(_tmpPomodoroCount);
        final long _tmpEstimatedTime;
        _tmpEstimatedTime = _cursor.getLong(_cursorIndexOfEstimatedTime);
        _item.setEstimatedTime(_tmpEstimatedTime);
        final long _tmpActualTime;
        _tmpActualTime = _cursor.getLong(_cursorIndexOfActualTime);
        _item.setActualTime(_tmpActualTime);
        final String _tmpColor;
        if (_cursor.isNull(_cursorIndexOfColor)) {
          _tmpColor = null;
        } else {
          _tmpColor = _cursor.getString(_cursorIndexOfColor);
        }
        _item.setColor(_tmpColor);
        final boolean _tmpIsFavorite;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp_4 != 0;
        _item.setFavorite(_tmpIsFavorite);
        final int _tmpStreakDays;
        _tmpStreakDays = _cursor.getInt(_cursorIndexOfStreakDays);
        _item.setStreakDays(_tmpStreakDays);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Task> getTasksByCategory(final String category) {
    final String _sql = "SELECT * FROM tasks WHERE category = ? ORDER BY sortOrder ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfProject = CursorUtil.getColumnIndexOrThrow(_cursor, "project");
      final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
      final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
      final int _cursorIndexOfHasReminder = CursorUtil.getColumnIndexOrThrow(_cursor, "hasReminder");
      final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
      final int _cursorIndexOfSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "subtasks");
      final int _cursorIndexOfCompletedSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "completedSubtasks");
      final int _cursorIndexOfRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatType");
      final int _cursorIndexOfRepeatEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatEndDate");
      final int _cursorIndexOfPomodoroCount = CursorUtil.getColumnIndexOrThrow(_cursor, "pomodoroCount");
      final int _cursorIndexOfEstimatedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedTime");
      final int _cursorIndexOfActualTime = CursorUtil.getColumnIndexOrThrow(_cursor, "actualTime");
      final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
      final int _cursorIndexOfStreakDays = CursorUtil.getColumnIndexOrThrow(_cursor, "streakDays");
      final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Task _item;
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpProject;
        if (_cursor.isNull(_cursorIndexOfProject)) {
          _tmpProject = null;
        } else {
          _tmpProject = _cursor.getString(_cursorIndexOfProject);
        }
        final String _tmpPriority;
        if (_cursor.isNull(_cursorIndexOfPriority)) {
          _tmpPriority = null;
        } else {
          _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
        }
        _item = new Task(_tmpTitle,_tmpProject,_tmpPriority);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final boolean _tmpCompleted;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfCompleted);
        _tmpCompleted = _tmp != 0;
        _item.setCompleted(_tmpCompleted);
        final long _tmpCreatedAt;
        _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _item.setCreatedAt(_tmpCreatedAt);
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        _item.setCategory(_tmpCategory);
        final long _tmpDeadline;
        _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
        _item.setDeadline(_tmpDeadline);
        final boolean _tmpHasReminder;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfHasReminder);
        _tmpHasReminder = _tmp_1 != 0;
        _item.setHasReminder(_tmpHasReminder);
        final int _tmpSortOrder;
        _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
        _item.setSortOrder(_tmpSortOrder);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        final List<String> _tmpTags;
        final String _tmp_2;
        if (_cursor.isNull(_cursorIndexOfTags)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getString(_cursorIndexOfTags);
        }
        _tmpTags = StringListConverter.toList(_tmp_2);
        _item.setTags(_tmpTags);
        final List<String> _tmpSubtasks;
        final String _tmp_3;
        if (_cursor.isNull(_cursorIndexOfSubtasks)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getString(_cursorIndexOfSubtasks);
        }
        _tmpSubtasks = StringListConverter.toList(_tmp_3);
        _item.setSubtasks(_tmpSubtasks);
        final int _tmpCompletedSubtasks;
        _tmpCompletedSubtasks = _cursor.getInt(_cursorIndexOfCompletedSubtasks);
        _item.setCompletedSubtasks(_tmpCompletedSubtasks);
        final String _tmpRepeatType;
        if (_cursor.isNull(_cursorIndexOfRepeatType)) {
          _tmpRepeatType = null;
        } else {
          _tmpRepeatType = _cursor.getString(_cursorIndexOfRepeatType);
        }
        _item.setRepeatType(_tmpRepeatType);
        final long _tmpRepeatEndDate;
        _tmpRepeatEndDate = _cursor.getLong(_cursorIndexOfRepeatEndDate);
        _item.setRepeatEndDate(_tmpRepeatEndDate);
        final int _tmpPomodoroCount;
        _tmpPomodoroCount = _cursor.getInt(_cursorIndexOfPomodoroCount);
        _item.setPomodoroCount(_tmpPomodoroCount);
        final long _tmpEstimatedTime;
        _tmpEstimatedTime = _cursor.getLong(_cursorIndexOfEstimatedTime);
        _item.setEstimatedTime(_tmpEstimatedTime);
        final long _tmpActualTime;
        _tmpActualTime = _cursor.getLong(_cursorIndexOfActualTime);
        _item.setActualTime(_tmpActualTime);
        final String _tmpColor;
        if (_cursor.isNull(_cursorIndexOfColor)) {
          _tmpColor = null;
        } else {
          _tmpColor = _cursor.getString(_cursorIndexOfColor);
        }
        _item.setColor(_tmpColor);
        final boolean _tmpIsFavorite;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp_4 != 0;
        _item.setFavorite(_tmpIsFavorite);
        final int _tmpStreakDays;
        _tmpStreakDays = _cursor.getInt(_cursorIndexOfStreakDays);
        _item.setStreakDays(_tmpStreakDays);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Task> getTasksByPriority(final String priority) {
    final String _sql = "SELECT * FROM tasks WHERE priority = ? ORDER BY sortOrder ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (priority == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, priority);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfProject = CursorUtil.getColumnIndexOrThrow(_cursor, "project");
      final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
      final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
      final int _cursorIndexOfHasReminder = CursorUtil.getColumnIndexOrThrow(_cursor, "hasReminder");
      final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
      final int _cursorIndexOfSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "subtasks");
      final int _cursorIndexOfCompletedSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "completedSubtasks");
      final int _cursorIndexOfRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatType");
      final int _cursorIndexOfRepeatEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatEndDate");
      final int _cursorIndexOfPomodoroCount = CursorUtil.getColumnIndexOrThrow(_cursor, "pomodoroCount");
      final int _cursorIndexOfEstimatedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedTime");
      final int _cursorIndexOfActualTime = CursorUtil.getColumnIndexOrThrow(_cursor, "actualTime");
      final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
      final int _cursorIndexOfStreakDays = CursorUtil.getColumnIndexOrThrow(_cursor, "streakDays");
      final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Task _item;
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpProject;
        if (_cursor.isNull(_cursorIndexOfProject)) {
          _tmpProject = null;
        } else {
          _tmpProject = _cursor.getString(_cursorIndexOfProject);
        }
        final String _tmpPriority;
        if (_cursor.isNull(_cursorIndexOfPriority)) {
          _tmpPriority = null;
        } else {
          _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
        }
        _item = new Task(_tmpTitle,_tmpProject,_tmpPriority);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final boolean _tmpCompleted;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfCompleted);
        _tmpCompleted = _tmp != 0;
        _item.setCompleted(_tmpCompleted);
        final long _tmpCreatedAt;
        _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _item.setCreatedAt(_tmpCreatedAt);
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        _item.setCategory(_tmpCategory);
        final long _tmpDeadline;
        _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
        _item.setDeadline(_tmpDeadline);
        final boolean _tmpHasReminder;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfHasReminder);
        _tmpHasReminder = _tmp_1 != 0;
        _item.setHasReminder(_tmpHasReminder);
        final int _tmpSortOrder;
        _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
        _item.setSortOrder(_tmpSortOrder);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        final List<String> _tmpTags;
        final String _tmp_2;
        if (_cursor.isNull(_cursorIndexOfTags)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getString(_cursorIndexOfTags);
        }
        _tmpTags = StringListConverter.toList(_tmp_2);
        _item.setTags(_tmpTags);
        final List<String> _tmpSubtasks;
        final String _tmp_3;
        if (_cursor.isNull(_cursorIndexOfSubtasks)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getString(_cursorIndexOfSubtasks);
        }
        _tmpSubtasks = StringListConverter.toList(_tmp_3);
        _item.setSubtasks(_tmpSubtasks);
        final int _tmpCompletedSubtasks;
        _tmpCompletedSubtasks = _cursor.getInt(_cursorIndexOfCompletedSubtasks);
        _item.setCompletedSubtasks(_tmpCompletedSubtasks);
        final String _tmpRepeatType;
        if (_cursor.isNull(_cursorIndexOfRepeatType)) {
          _tmpRepeatType = null;
        } else {
          _tmpRepeatType = _cursor.getString(_cursorIndexOfRepeatType);
        }
        _item.setRepeatType(_tmpRepeatType);
        final long _tmpRepeatEndDate;
        _tmpRepeatEndDate = _cursor.getLong(_cursorIndexOfRepeatEndDate);
        _item.setRepeatEndDate(_tmpRepeatEndDate);
        final int _tmpPomodoroCount;
        _tmpPomodoroCount = _cursor.getInt(_cursorIndexOfPomodoroCount);
        _item.setPomodoroCount(_tmpPomodoroCount);
        final long _tmpEstimatedTime;
        _tmpEstimatedTime = _cursor.getLong(_cursorIndexOfEstimatedTime);
        _item.setEstimatedTime(_tmpEstimatedTime);
        final long _tmpActualTime;
        _tmpActualTime = _cursor.getLong(_cursorIndexOfActualTime);
        _item.setActualTime(_tmpActualTime);
        final String _tmpColor;
        if (_cursor.isNull(_cursorIndexOfColor)) {
          _tmpColor = null;
        } else {
          _tmpColor = _cursor.getString(_cursorIndexOfColor);
        }
        _item.setColor(_tmpColor);
        final boolean _tmpIsFavorite;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp_4 != 0;
        _item.setFavorite(_tmpIsFavorite);
        final int _tmpStreakDays;
        _tmpStreakDays = _cursor.getInt(_cursorIndexOfStreakDays);
        _item.setStreakDays(_tmpStreakDays);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Task> searchTasks(final String query) {
    final String _sql = "SELECT * FROM tasks WHERE title LIKE '%' || ? || '%' OR project LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    _argIndex = 2;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfProject = CursorUtil.getColumnIndexOrThrow(_cursor, "project");
      final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
      final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
      final int _cursorIndexOfHasReminder = CursorUtil.getColumnIndexOrThrow(_cursor, "hasReminder");
      final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
      final int _cursorIndexOfSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "subtasks");
      final int _cursorIndexOfCompletedSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "completedSubtasks");
      final int _cursorIndexOfRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatType");
      final int _cursorIndexOfRepeatEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatEndDate");
      final int _cursorIndexOfPomodoroCount = CursorUtil.getColumnIndexOrThrow(_cursor, "pomodoroCount");
      final int _cursorIndexOfEstimatedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedTime");
      final int _cursorIndexOfActualTime = CursorUtil.getColumnIndexOrThrow(_cursor, "actualTime");
      final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
      final int _cursorIndexOfStreakDays = CursorUtil.getColumnIndexOrThrow(_cursor, "streakDays");
      final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Task _item;
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpProject;
        if (_cursor.isNull(_cursorIndexOfProject)) {
          _tmpProject = null;
        } else {
          _tmpProject = _cursor.getString(_cursorIndexOfProject);
        }
        final String _tmpPriority;
        if (_cursor.isNull(_cursorIndexOfPriority)) {
          _tmpPriority = null;
        } else {
          _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
        }
        _item = new Task(_tmpTitle,_tmpProject,_tmpPriority);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final boolean _tmpCompleted;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfCompleted);
        _tmpCompleted = _tmp != 0;
        _item.setCompleted(_tmpCompleted);
        final long _tmpCreatedAt;
        _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _item.setCreatedAt(_tmpCreatedAt);
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        _item.setCategory(_tmpCategory);
        final long _tmpDeadline;
        _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
        _item.setDeadline(_tmpDeadline);
        final boolean _tmpHasReminder;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfHasReminder);
        _tmpHasReminder = _tmp_1 != 0;
        _item.setHasReminder(_tmpHasReminder);
        final int _tmpSortOrder;
        _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
        _item.setSortOrder(_tmpSortOrder);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        final List<String> _tmpTags;
        final String _tmp_2;
        if (_cursor.isNull(_cursorIndexOfTags)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getString(_cursorIndexOfTags);
        }
        _tmpTags = StringListConverter.toList(_tmp_2);
        _item.setTags(_tmpTags);
        final List<String> _tmpSubtasks;
        final String _tmp_3;
        if (_cursor.isNull(_cursorIndexOfSubtasks)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getString(_cursorIndexOfSubtasks);
        }
        _tmpSubtasks = StringListConverter.toList(_tmp_3);
        _item.setSubtasks(_tmpSubtasks);
        final int _tmpCompletedSubtasks;
        _tmpCompletedSubtasks = _cursor.getInt(_cursorIndexOfCompletedSubtasks);
        _item.setCompletedSubtasks(_tmpCompletedSubtasks);
        final String _tmpRepeatType;
        if (_cursor.isNull(_cursorIndexOfRepeatType)) {
          _tmpRepeatType = null;
        } else {
          _tmpRepeatType = _cursor.getString(_cursorIndexOfRepeatType);
        }
        _item.setRepeatType(_tmpRepeatType);
        final long _tmpRepeatEndDate;
        _tmpRepeatEndDate = _cursor.getLong(_cursorIndexOfRepeatEndDate);
        _item.setRepeatEndDate(_tmpRepeatEndDate);
        final int _tmpPomodoroCount;
        _tmpPomodoroCount = _cursor.getInt(_cursorIndexOfPomodoroCount);
        _item.setPomodoroCount(_tmpPomodoroCount);
        final long _tmpEstimatedTime;
        _tmpEstimatedTime = _cursor.getLong(_cursorIndexOfEstimatedTime);
        _item.setEstimatedTime(_tmpEstimatedTime);
        final long _tmpActualTime;
        _tmpActualTime = _cursor.getLong(_cursorIndexOfActualTime);
        _item.setActualTime(_tmpActualTime);
        final String _tmpColor;
        if (_cursor.isNull(_cursorIndexOfColor)) {
          _tmpColor = null;
        } else {
          _tmpColor = _cursor.getString(_cursorIndexOfColor);
        }
        _item.setColor(_tmpColor);
        final boolean _tmpIsFavorite;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp_4 != 0;
        _item.setFavorite(_tmpIsFavorite);
        final int _tmpStreakDays;
        _tmpStreakDays = _cursor.getInt(_cursorIndexOfStreakDays);
        _item.setStreakDays(_tmpStreakDays);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getCompletedTaskCount() {
    final String _sql = "SELECT COUNT(*) FROM tasks WHERE completed = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getActiveTaskCount() {
    final String _sql = "SELECT COUNT(*) FROM tasks WHERE completed = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getOverdueTaskCount(final long currentTime) {
    final String _sql = "SELECT COUNT(*) FROM tasks WHERE deadline > 0 AND deadline < ? AND completed = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, currentTime);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Task getTaskById(final int taskId) {
    final String _sql = "SELECT * FROM tasks WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, taskId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfProject = CursorUtil.getColumnIndexOrThrow(_cursor, "project");
      final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
      final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
      final int _cursorIndexOfHasReminder = CursorUtil.getColumnIndexOrThrow(_cursor, "hasReminder");
      final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
      final int _cursorIndexOfSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "subtasks");
      final int _cursorIndexOfCompletedSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "completedSubtasks");
      final int _cursorIndexOfRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatType");
      final int _cursorIndexOfRepeatEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatEndDate");
      final int _cursorIndexOfPomodoroCount = CursorUtil.getColumnIndexOrThrow(_cursor, "pomodoroCount");
      final int _cursorIndexOfEstimatedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedTime");
      final int _cursorIndexOfActualTime = CursorUtil.getColumnIndexOrThrow(_cursor, "actualTime");
      final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
      final int _cursorIndexOfStreakDays = CursorUtil.getColumnIndexOrThrow(_cursor, "streakDays");
      final Task _result;
      if (_cursor.moveToFirst()) {
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpProject;
        if (_cursor.isNull(_cursorIndexOfProject)) {
          _tmpProject = null;
        } else {
          _tmpProject = _cursor.getString(_cursorIndexOfProject);
        }
        final String _tmpPriority;
        if (_cursor.isNull(_cursorIndexOfPriority)) {
          _tmpPriority = null;
        } else {
          _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
        }
        _result = new Task(_tmpTitle,_tmpProject,_tmpPriority);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final boolean _tmpCompleted;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfCompleted);
        _tmpCompleted = _tmp != 0;
        _result.setCompleted(_tmpCompleted);
        final long _tmpCreatedAt;
        _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _result.setCreatedAt(_tmpCreatedAt);
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        _result.setCategory(_tmpCategory);
        final long _tmpDeadline;
        _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
        _result.setDeadline(_tmpDeadline);
        final boolean _tmpHasReminder;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfHasReminder);
        _tmpHasReminder = _tmp_1 != 0;
        _result.setHasReminder(_tmpHasReminder);
        final int _tmpSortOrder;
        _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
        _result.setSortOrder(_tmpSortOrder);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _result.setDescription(_tmpDescription);
        final List<String> _tmpTags;
        final String _tmp_2;
        if (_cursor.isNull(_cursorIndexOfTags)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getString(_cursorIndexOfTags);
        }
        _tmpTags = StringListConverter.toList(_tmp_2);
        _result.setTags(_tmpTags);
        final List<String> _tmpSubtasks;
        final String _tmp_3;
        if (_cursor.isNull(_cursorIndexOfSubtasks)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getString(_cursorIndexOfSubtasks);
        }
        _tmpSubtasks = StringListConverter.toList(_tmp_3);
        _result.setSubtasks(_tmpSubtasks);
        final int _tmpCompletedSubtasks;
        _tmpCompletedSubtasks = _cursor.getInt(_cursorIndexOfCompletedSubtasks);
        _result.setCompletedSubtasks(_tmpCompletedSubtasks);
        final String _tmpRepeatType;
        if (_cursor.isNull(_cursorIndexOfRepeatType)) {
          _tmpRepeatType = null;
        } else {
          _tmpRepeatType = _cursor.getString(_cursorIndexOfRepeatType);
        }
        _result.setRepeatType(_tmpRepeatType);
        final long _tmpRepeatEndDate;
        _tmpRepeatEndDate = _cursor.getLong(_cursorIndexOfRepeatEndDate);
        _result.setRepeatEndDate(_tmpRepeatEndDate);
        final int _tmpPomodoroCount;
        _tmpPomodoroCount = _cursor.getInt(_cursorIndexOfPomodoroCount);
        _result.setPomodoroCount(_tmpPomodoroCount);
        final long _tmpEstimatedTime;
        _tmpEstimatedTime = _cursor.getLong(_cursorIndexOfEstimatedTime);
        _result.setEstimatedTime(_tmpEstimatedTime);
        final long _tmpActualTime;
        _tmpActualTime = _cursor.getLong(_cursorIndexOfActualTime);
        _result.setActualTime(_tmpActualTime);
        final String _tmpColor;
        if (_cursor.isNull(_cursorIndexOfColor)) {
          _tmpColor = null;
        } else {
          _tmpColor = _cursor.getString(_cursorIndexOfColor);
        }
        _result.setColor(_tmpColor);
        final boolean _tmpIsFavorite;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp_4 != 0;
        _result.setFavorite(_tmpIsFavorite);
        final int _tmpStreakDays;
        _tmpStreakDays = _cursor.getInt(_cursorIndexOfStreakDays);
        _result.setStreakDays(_tmpStreakDays);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Task> getTasksForDateRange(final long startTime, final long endTime) {
    final String _sql = "SELECT * FROM tasks WHERE deadline >= ? AND deadline <= ? ORDER BY deadline ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startTime);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endTime);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfProject = CursorUtil.getColumnIndexOrThrow(_cursor, "project");
      final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
      final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
      final int _cursorIndexOfHasReminder = CursorUtil.getColumnIndexOrThrow(_cursor, "hasReminder");
      final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
      final int _cursorIndexOfSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "subtasks");
      final int _cursorIndexOfCompletedSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "completedSubtasks");
      final int _cursorIndexOfRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatType");
      final int _cursorIndexOfRepeatEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatEndDate");
      final int _cursorIndexOfPomodoroCount = CursorUtil.getColumnIndexOrThrow(_cursor, "pomodoroCount");
      final int _cursorIndexOfEstimatedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedTime");
      final int _cursorIndexOfActualTime = CursorUtil.getColumnIndexOrThrow(_cursor, "actualTime");
      final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
      final int _cursorIndexOfStreakDays = CursorUtil.getColumnIndexOrThrow(_cursor, "streakDays");
      final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Task _item;
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpProject;
        if (_cursor.isNull(_cursorIndexOfProject)) {
          _tmpProject = null;
        } else {
          _tmpProject = _cursor.getString(_cursorIndexOfProject);
        }
        final String _tmpPriority;
        if (_cursor.isNull(_cursorIndexOfPriority)) {
          _tmpPriority = null;
        } else {
          _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
        }
        _item = new Task(_tmpTitle,_tmpProject,_tmpPriority);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final boolean _tmpCompleted;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfCompleted);
        _tmpCompleted = _tmp != 0;
        _item.setCompleted(_tmpCompleted);
        final long _tmpCreatedAt;
        _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _item.setCreatedAt(_tmpCreatedAt);
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        _item.setCategory(_tmpCategory);
        final long _tmpDeadline;
        _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
        _item.setDeadline(_tmpDeadline);
        final boolean _tmpHasReminder;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfHasReminder);
        _tmpHasReminder = _tmp_1 != 0;
        _item.setHasReminder(_tmpHasReminder);
        final int _tmpSortOrder;
        _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
        _item.setSortOrder(_tmpSortOrder);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        final List<String> _tmpTags;
        final String _tmp_2;
        if (_cursor.isNull(_cursorIndexOfTags)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getString(_cursorIndexOfTags);
        }
        _tmpTags = StringListConverter.toList(_tmp_2);
        _item.setTags(_tmpTags);
        final List<String> _tmpSubtasks;
        final String _tmp_3;
        if (_cursor.isNull(_cursorIndexOfSubtasks)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getString(_cursorIndexOfSubtasks);
        }
        _tmpSubtasks = StringListConverter.toList(_tmp_3);
        _item.setSubtasks(_tmpSubtasks);
        final int _tmpCompletedSubtasks;
        _tmpCompletedSubtasks = _cursor.getInt(_cursorIndexOfCompletedSubtasks);
        _item.setCompletedSubtasks(_tmpCompletedSubtasks);
        final String _tmpRepeatType;
        if (_cursor.isNull(_cursorIndexOfRepeatType)) {
          _tmpRepeatType = null;
        } else {
          _tmpRepeatType = _cursor.getString(_cursorIndexOfRepeatType);
        }
        _item.setRepeatType(_tmpRepeatType);
        final long _tmpRepeatEndDate;
        _tmpRepeatEndDate = _cursor.getLong(_cursorIndexOfRepeatEndDate);
        _item.setRepeatEndDate(_tmpRepeatEndDate);
        final int _tmpPomodoroCount;
        _tmpPomodoroCount = _cursor.getInt(_cursorIndexOfPomodoroCount);
        _item.setPomodoroCount(_tmpPomodoroCount);
        final long _tmpEstimatedTime;
        _tmpEstimatedTime = _cursor.getLong(_cursorIndexOfEstimatedTime);
        _item.setEstimatedTime(_tmpEstimatedTime);
        final long _tmpActualTime;
        _tmpActualTime = _cursor.getLong(_cursorIndexOfActualTime);
        _item.setActualTime(_tmpActualTime);
        final String _tmpColor;
        if (_cursor.isNull(_cursorIndexOfColor)) {
          _tmpColor = null;
        } else {
          _tmpColor = _cursor.getString(_cursorIndexOfColor);
        }
        _item.setColor(_tmpColor);
        final boolean _tmpIsFavorite;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp_4 != 0;
        _item.setFavorite(_tmpIsFavorite);
        final int _tmpStreakDays;
        _tmpStreakDays = _cursor.getInt(_cursorIndexOfStreakDays);
        _item.setStreakDays(_tmpStreakDays);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getTaskCountForDate(final long startTime, final long endTime) {
    final String _sql = "SELECT COUNT(*) FROM tasks WHERE deadline >= ? AND deadline <= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startTime);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endTime);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getCompletedTaskCountForDate(final long startTime, final long endTime) {
    final String _sql = "SELECT COUNT(*) FROM tasks WHERE completed = 1 AND deadline >= ? AND deadline <= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startTime);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endTime);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getDistinctCategoriesCount() {
    final String _sql = "SELECT COUNT(DISTINCT category) FROM tasks";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Task> getFavoriteTasks() {
    final String _sql = "SELECT * FROM tasks WHERE isFavorite = 1 ORDER BY sortOrder ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfProject = CursorUtil.getColumnIndexOrThrow(_cursor, "project");
      final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
      final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
      final int _cursorIndexOfHasReminder = CursorUtil.getColumnIndexOrThrow(_cursor, "hasReminder");
      final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
      final int _cursorIndexOfSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "subtasks");
      final int _cursorIndexOfCompletedSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "completedSubtasks");
      final int _cursorIndexOfRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatType");
      final int _cursorIndexOfRepeatEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatEndDate");
      final int _cursorIndexOfPomodoroCount = CursorUtil.getColumnIndexOrThrow(_cursor, "pomodoroCount");
      final int _cursorIndexOfEstimatedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedTime");
      final int _cursorIndexOfActualTime = CursorUtil.getColumnIndexOrThrow(_cursor, "actualTime");
      final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
      final int _cursorIndexOfStreakDays = CursorUtil.getColumnIndexOrThrow(_cursor, "streakDays");
      final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Task _item;
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpProject;
        if (_cursor.isNull(_cursorIndexOfProject)) {
          _tmpProject = null;
        } else {
          _tmpProject = _cursor.getString(_cursorIndexOfProject);
        }
        final String _tmpPriority;
        if (_cursor.isNull(_cursorIndexOfPriority)) {
          _tmpPriority = null;
        } else {
          _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
        }
        _item = new Task(_tmpTitle,_tmpProject,_tmpPriority);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final boolean _tmpCompleted;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfCompleted);
        _tmpCompleted = _tmp != 0;
        _item.setCompleted(_tmpCompleted);
        final long _tmpCreatedAt;
        _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _item.setCreatedAt(_tmpCreatedAt);
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        _item.setCategory(_tmpCategory);
        final long _tmpDeadline;
        _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
        _item.setDeadline(_tmpDeadline);
        final boolean _tmpHasReminder;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfHasReminder);
        _tmpHasReminder = _tmp_1 != 0;
        _item.setHasReminder(_tmpHasReminder);
        final int _tmpSortOrder;
        _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
        _item.setSortOrder(_tmpSortOrder);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        final List<String> _tmpTags;
        final String _tmp_2;
        if (_cursor.isNull(_cursorIndexOfTags)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getString(_cursorIndexOfTags);
        }
        _tmpTags = StringListConverter.toList(_tmp_2);
        _item.setTags(_tmpTags);
        final List<String> _tmpSubtasks;
        final String _tmp_3;
        if (_cursor.isNull(_cursorIndexOfSubtasks)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getString(_cursorIndexOfSubtasks);
        }
        _tmpSubtasks = StringListConverter.toList(_tmp_3);
        _item.setSubtasks(_tmpSubtasks);
        final int _tmpCompletedSubtasks;
        _tmpCompletedSubtasks = _cursor.getInt(_cursorIndexOfCompletedSubtasks);
        _item.setCompletedSubtasks(_tmpCompletedSubtasks);
        final String _tmpRepeatType;
        if (_cursor.isNull(_cursorIndexOfRepeatType)) {
          _tmpRepeatType = null;
        } else {
          _tmpRepeatType = _cursor.getString(_cursorIndexOfRepeatType);
        }
        _item.setRepeatType(_tmpRepeatType);
        final long _tmpRepeatEndDate;
        _tmpRepeatEndDate = _cursor.getLong(_cursorIndexOfRepeatEndDate);
        _item.setRepeatEndDate(_tmpRepeatEndDate);
        final int _tmpPomodoroCount;
        _tmpPomodoroCount = _cursor.getInt(_cursorIndexOfPomodoroCount);
        _item.setPomodoroCount(_tmpPomodoroCount);
        final long _tmpEstimatedTime;
        _tmpEstimatedTime = _cursor.getLong(_cursorIndexOfEstimatedTime);
        _item.setEstimatedTime(_tmpEstimatedTime);
        final long _tmpActualTime;
        _tmpActualTime = _cursor.getLong(_cursorIndexOfActualTime);
        _item.setActualTime(_tmpActualTime);
        final String _tmpColor;
        if (_cursor.isNull(_cursorIndexOfColor)) {
          _tmpColor = null;
        } else {
          _tmpColor = _cursor.getString(_cursorIndexOfColor);
        }
        _item.setColor(_tmpColor);
        final boolean _tmpIsFavorite;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp_4 != 0;
        _item.setFavorite(_tmpIsFavorite);
        final int _tmpStreakDays;
        _tmpStreakDays = _cursor.getInt(_cursorIndexOfStreakDays);
        _item.setStreakDays(_tmpStreakDays);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Task> getRepeatingTasks() {
    final String _sql = "SELECT * FROM tasks WHERE repeatType != 'none' ORDER BY sortOrder ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfProject = CursorUtil.getColumnIndexOrThrow(_cursor, "project");
      final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
      final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
      final int _cursorIndexOfHasReminder = CursorUtil.getColumnIndexOrThrow(_cursor, "hasReminder");
      final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
      final int _cursorIndexOfSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "subtasks");
      final int _cursorIndexOfCompletedSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "completedSubtasks");
      final int _cursorIndexOfRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatType");
      final int _cursorIndexOfRepeatEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatEndDate");
      final int _cursorIndexOfPomodoroCount = CursorUtil.getColumnIndexOrThrow(_cursor, "pomodoroCount");
      final int _cursorIndexOfEstimatedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedTime");
      final int _cursorIndexOfActualTime = CursorUtil.getColumnIndexOrThrow(_cursor, "actualTime");
      final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
      final int _cursorIndexOfStreakDays = CursorUtil.getColumnIndexOrThrow(_cursor, "streakDays");
      final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Task _item;
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpProject;
        if (_cursor.isNull(_cursorIndexOfProject)) {
          _tmpProject = null;
        } else {
          _tmpProject = _cursor.getString(_cursorIndexOfProject);
        }
        final String _tmpPriority;
        if (_cursor.isNull(_cursorIndexOfPriority)) {
          _tmpPriority = null;
        } else {
          _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
        }
        _item = new Task(_tmpTitle,_tmpProject,_tmpPriority);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final boolean _tmpCompleted;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfCompleted);
        _tmpCompleted = _tmp != 0;
        _item.setCompleted(_tmpCompleted);
        final long _tmpCreatedAt;
        _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _item.setCreatedAt(_tmpCreatedAt);
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        _item.setCategory(_tmpCategory);
        final long _tmpDeadline;
        _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
        _item.setDeadline(_tmpDeadline);
        final boolean _tmpHasReminder;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfHasReminder);
        _tmpHasReminder = _tmp_1 != 0;
        _item.setHasReminder(_tmpHasReminder);
        final int _tmpSortOrder;
        _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
        _item.setSortOrder(_tmpSortOrder);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        final List<String> _tmpTags;
        final String _tmp_2;
        if (_cursor.isNull(_cursorIndexOfTags)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getString(_cursorIndexOfTags);
        }
        _tmpTags = StringListConverter.toList(_tmp_2);
        _item.setTags(_tmpTags);
        final List<String> _tmpSubtasks;
        final String _tmp_3;
        if (_cursor.isNull(_cursorIndexOfSubtasks)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getString(_cursorIndexOfSubtasks);
        }
        _tmpSubtasks = StringListConverter.toList(_tmp_3);
        _item.setSubtasks(_tmpSubtasks);
        final int _tmpCompletedSubtasks;
        _tmpCompletedSubtasks = _cursor.getInt(_cursorIndexOfCompletedSubtasks);
        _item.setCompletedSubtasks(_tmpCompletedSubtasks);
        final String _tmpRepeatType;
        if (_cursor.isNull(_cursorIndexOfRepeatType)) {
          _tmpRepeatType = null;
        } else {
          _tmpRepeatType = _cursor.getString(_cursorIndexOfRepeatType);
        }
        _item.setRepeatType(_tmpRepeatType);
        final long _tmpRepeatEndDate;
        _tmpRepeatEndDate = _cursor.getLong(_cursorIndexOfRepeatEndDate);
        _item.setRepeatEndDate(_tmpRepeatEndDate);
        final int _tmpPomodoroCount;
        _tmpPomodoroCount = _cursor.getInt(_cursorIndexOfPomodoroCount);
        _item.setPomodoroCount(_tmpPomodoroCount);
        final long _tmpEstimatedTime;
        _tmpEstimatedTime = _cursor.getLong(_cursorIndexOfEstimatedTime);
        _item.setEstimatedTime(_tmpEstimatedTime);
        final long _tmpActualTime;
        _tmpActualTime = _cursor.getLong(_cursorIndexOfActualTime);
        _item.setActualTime(_tmpActualTime);
        final String _tmpColor;
        if (_cursor.isNull(_cursorIndexOfColor)) {
          _tmpColor = null;
        } else {
          _tmpColor = _cursor.getString(_cursorIndexOfColor);
        }
        _item.setColor(_tmpColor);
        final boolean _tmpIsFavorite;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp_4 != 0;
        _item.setFavorite(_tmpIsFavorite);
        final int _tmpStreakDays;
        _tmpStreakDays = _cursor.getInt(_cursorIndexOfStreakDays);
        _item.setStreakDays(_tmpStreakDays);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Task> getTasksWithDeadlineInRange(final long startTime, final long endTime) {
    final String _sql = "SELECT * FROM tasks WHERE deadline > 0 AND deadline >= ? AND deadline <= ? ORDER BY deadline ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startTime);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endTime);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfProject = CursorUtil.getColumnIndexOrThrow(_cursor, "project");
      final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
      final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
      final int _cursorIndexOfHasReminder = CursorUtil.getColumnIndexOrThrow(_cursor, "hasReminder");
      final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
      final int _cursorIndexOfSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "subtasks");
      final int _cursorIndexOfCompletedSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "completedSubtasks");
      final int _cursorIndexOfRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatType");
      final int _cursorIndexOfRepeatEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatEndDate");
      final int _cursorIndexOfPomodoroCount = CursorUtil.getColumnIndexOrThrow(_cursor, "pomodoroCount");
      final int _cursorIndexOfEstimatedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedTime");
      final int _cursorIndexOfActualTime = CursorUtil.getColumnIndexOrThrow(_cursor, "actualTime");
      final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
      final int _cursorIndexOfStreakDays = CursorUtil.getColumnIndexOrThrow(_cursor, "streakDays");
      final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Task _item;
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpProject;
        if (_cursor.isNull(_cursorIndexOfProject)) {
          _tmpProject = null;
        } else {
          _tmpProject = _cursor.getString(_cursorIndexOfProject);
        }
        final String _tmpPriority;
        if (_cursor.isNull(_cursorIndexOfPriority)) {
          _tmpPriority = null;
        } else {
          _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
        }
        _item = new Task(_tmpTitle,_tmpProject,_tmpPriority);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final boolean _tmpCompleted;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfCompleted);
        _tmpCompleted = _tmp != 0;
        _item.setCompleted(_tmpCompleted);
        final long _tmpCreatedAt;
        _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _item.setCreatedAt(_tmpCreatedAt);
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        _item.setCategory(_tmpCategory);
        final long _tmpDeadline;
        _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
        _item.setDeadline(_tmpDeadline);
        final boolean _tmpHasReminder;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfHasReminder);
        _tmpHasReminder = _tmp_1 != 0;
        _item.setHasReminder(_tmpHasReminder);
        final int _tmpSortOrder;
        _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
        _item.setSortOrder(_tmpSortOrder);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        final List<String> _tmpTags;
        final String _tmp_2;
        if (_cursor.isNull(_cursorIndexOfTags)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getString(_cursorIndexOfTags);
        }
        _tmpTags = StringListConverter.toList(_tmp_2);
        _item.setTags(_tmpTags);
        final List<String> _tmpSubtasks;
        final String _tmp_3;
        if (_cursor.isNull(_cursorIndexOfSubtasks)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getString(_cursorIndexOfSubtasks);
        }
        _tmpSubtasks = StringListConverter.toList(_tmp_3);
        _item.setSubtasks(_tmpSubtasks);
        final int _tmpCompletedSubtasks;
        _tmpCompletedSubtasks = _cursor.getInt(_cursorIndexOfCompletedSubtasks);
        _item.setCompletedSubtasks(_tmpCompletedSubtasks);
        final String _tmpRepeatType;
        if (_cursor.isNull(_cursorIndexOfRepeatType)) {
          _tmpRepeatType = null;
        } else {
          _tmpRepeatType = _cursor.getString(_cursorIndexOfRepeatType);
        }
        _item.setRepeatType(_tmpRepeatType);
        final long _tmpRepeatEndDate;
        _tmpRepeatEndDate = _cursor.getLong(_cursorIndexOfRepeatEndDate);
        _item.setRepeatEndDate(_tmpRepeatEndDate);
        final int _tmpPomodoroCount;
        _tmpPomodoroCount = _cursor.getInt(_cursorIndexOfPomodoroCount);
        _item.setPomodoroCount(_tmpPomodoroCount);
        final long _tmpEstimatedTime;
        _tmpEstimatedTime = _cursor.getLong(_cursorIndexOfEstimatedTime);
        _item.setEstimatedTime(_tmpEstimatedTime);
        final long _tmpActualTime;
        _tmpActualTime = _cursor.getLong(_cursorIndexOfActualTime);
        _item.setActualTime(_tmpActualTime);
        final String _tmpColor;
        if (_cursor.isNull(_cursorIndexOfColor)) {
          _tmpColor = null;
        } else {
          _tmpColor = _cursor.getString(_cursorIndexOfColor);
        }
        _item.setColor(_tmpColor);
        final boolean _tmpIsFavorite;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp_4 != 0;
        _item.setFavorite(_tmpIsFavorite);
        final int _tmpStreakDays;
        _tmpStreakDays = _cursor.getInt(_cursorIndexOfStreakDays);
        _item.setStreakDays(_tmpStreakDays);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Task> getRecentTasks(final int limit) {
    final String _sql = "SELECT * FROM tasks ORDER BY createdAt DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfProject = CursorUtil.getColumnIndexOrThrow(_cursor, "project");
      final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
      final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
      final int _cursorIndexOfHasReminder = CursorUtil.getColumnIndexOrThrow(_cursor, "hasReminder");
      final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
      final int _cursorIndexOfSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "subtasks");
      final int _cursorIndexOfCompletedSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "completedSubtasks");
      final int _cursorIndexOfRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatType");
      final int _cursorIndexOfRepeatEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatEndDate");
      final int _cursorIndexOfPomodoroCount = CursorUtil.getColumnIndexOrThrow(_cursor, "pomodoroCount");
      final int _cursorIndexOfEstimatedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedTime");
      final int _cursorIndexOfActualTime = CursorUtil.getColumnIndexOrThrow(_cursor, "actualTime");
      final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
      final int _cursorIndexOfStreakDays = CursorUtil.getColumnIndexOrThrow(_cursor, "streakDays");
      final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Task _item;
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpProject;
        if (_cursor.isNull(_cursorIndexOfProject)) {
          _tmpProject = null;
        } else {
          _tmpProject = _cursor.getString(_cursorIndexOfProject);
        }
        final String _tmpPriority;
        if (_cursor.isNull(_cursorIndexOfPriority)) {
          _tmpPriority = null;
        } else {
          _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
        }
        _item = new Task(_tmpTitle,_tmpProject,_tmpPriority);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final boolean _tmpCompleted;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfCompleted);
        _tmpCompleted = _tmp != 0;
        _item.setCompleted(_tmpCompleted);
        final long _tmpCreatedAt;
        _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _item.setCreatedAt(_tmpCreatedAt);
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        _item.setCategory(_tmpCategory);
        final long _tmpDeadline;
        _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
        _item.setDeadline(_tmpDeadline);
        final boolean _tmpHasReminder;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfHasReminder);
        _tmpHasReminder = _tmp_1 != 0;
        _item.setHasReminder(_tmpHasReminder);
        final int _tmpSortOrder;
        _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
        _item.setSortOrder(_tmpSortOrder);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        final List<String> _tmpTags;
        final String _tmp_2;
        if (_cursor.isNull(_cursorIndexOfTags)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getString(_cursorIndexOfTags);
        }
        _tmpTags = StringListConverter.toList(_tmp_2);
        _item.setTags(_tmpTags);
        final List<String> _tmpSubtasks;
        final String _tmp_3;
        if (_cursor.isNull(_cursorIndexOfSubtasks)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getString(_cursorIndexOfSubtasks);
        }
        _tmpSubtasks = StringListConverter.toList(_tmp_3);
        _item.setSubtasks(_tmpSubtasks);
        final int _tmpCompletedSubtasks;
        _tmpCompletedSubtasks = _cursor.getInt(_cursorIndexOfCompletedSubtasks);
        _item.setCompletedSubtasks(_tmpCompletedSubtasks);
        final String _tmpRepeatType;
        if (_cursor.isNull(_cursorIndexOfRepeatType)) {
          _tmpRepeatType = null;
        } else {
          _tmpRepeatType = _cursor.getString(_cursorIndexOfRepeatType);
        }
        _item.setRepeatType(_tmpRepeatType);
        final long _tmpRepeatEndDate;
        _tmpRepeatEndDate = _cursor.getLong(_cursorIndexOfRepeatEndDate);
        _item.setRepeatEndDate(_tmpRepeatEndDate);
        final int _tmpPomodoroCount;
        _tmpPomodoroCount = _cursor.getInt(_cursorIndexOfPomodoroCount);
        _item.setPomodoroCount(_tmpPomodoroCount);
        final long _tmpEstimatedTime;
        _tmpEstimatedTime = _cursor.getLong(_cursorIndexOfEstimatedTime);
        _item.setEstimatedTime(_tmpEstimatedTime);
        final long _tmpActualTime;
        _tmpActualTime = _cursor.getLong(_cursorIndexOfActualTime);
        _item.setActualTime(_tmpActualTime);
        final String _tmpColor;
        if (_cursor.isNull(_cursorIndexOfColor)) {
          _tmpColor = null;
        } else {
          _tmpColor = _cursor.getString(_cursorIndexOfColor);
        }
        _item.setColor(_tmpColor);
        final boolean _tmpIsFavorite;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp_4 != 0;
        _item.setFavorite(_tmpIsFavorite);
        final int _tmpStreakDays;
        _tmpStreakDays = _cursor.getInt(_cursorIndexOfStreakDays);
        _item.setStreakDays(_tmpStreakDays);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Task> getTasksByProject(final String projectName) {
    final String _sql = "SELECT * FROM tasks WHERE project = ? ORDER BY sortOrder ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (projectName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, projectName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfProject = CursorUtil.getColumnIndexOrThrow(_cursor, "project");
      final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
      final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
      final int _cursorIndexOfHasReminder = CursorUtil.getColumnIndexOrThrow(_cursor, "hasReminder");
      final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
      final int _cursorIndexOfSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "subtasks");
      final int _cursorIndexOfCompletedSubtasks = CursorUtil.getColumnIndexOrThrow(_cursor, "completedSubtasks");
      final int _cursorIndexOfRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatType");
      final int _cursorIndexOfRepeatEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatEndDate");
      final int _cursorIndexOfPomodoroCount = CursorUtil.getColumnIndexOrThrow(_cursor, "pomodoroCount");
      final int _cursorIndexOfEstimatedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedTime");
      final int _cursorIndexOfActualTime = CursorUtil.getColumnIndexOrThrow(_cursor, "actualTime");
      final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
      final int _cursorIndexOfStreakDays = CursorUtil.getColumnIndexOrThrow(_cursor, "streakDays");
      final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Task _item;
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpProject;
        if (_cursor.isNull(_cursorIndexOfProject)) {
          _tmpProject = null;
        } else {
          _tmpProject = _cursor.getString(_cursorIndexOfProject);
        }
        final String _tmpPriority;
        if (_cursor.isNull(_cursorIndexOfPriority)) {
          _tmpPriority = null;
        } else {
          _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
        }
        _item = new Task(_tmpTitle,_tmpProject,_tmpPriority);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final boolean _tmpCompleted;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfCompleted);
        _tmpCompleted = _tmp != 0;
        _item.setCompleted(_tmpCompleted);
        final long _tmpCreatedAt;
        _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _item.setCreatedAt(_tmpCreatedAt);
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        _item.setCategory(_tmpCategory);
        final long _tmpDeadline;
        _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
        _item.setDeadline(_tmpDeadline);
        final boolean _tmpHasReminder;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfHasReminder);
        _tmpHasReminder = _tmp_1 != 0;
        _item.setHasReminder(_tmpHasReminder);
        final int _tmpSortOrder;
        _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
        _item.setSortOrder(_tmpSortOrder);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        final List<String> _tmpTags;
        final String _tmp_2;
        if (_cursor.isNull(_cursorIndexOfTags)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getString(_cursorIndexOfTags);
        }
        _tmpTags = StringListConverter.toList(_tmp_2);
        _item.setTags(_tmpTags);
        final List<String> _tmpSubtasks;
        final String _tmp_3;
        if (_cursor.isNull(_cursorIndexOfSubtasks)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getString(_cursorIndexOfSubtasks);
        }
        _tmpSubtasks = StringListConverter.toList(_tmp_3);
        _item.setSubtasks(_tmpSubtasks);
        final int _tmpCompletedSubtasks;
        _tmpCompletedSubtasks = _cursor.getInt(_cursorIndexOfCompletedSubtasks);
        _item.setCompletedSubtasks(_tmpCompletedSubtasks);
        final String _tmpRepeatType;
        if (_cursor.isNull(_cursorIndexOfRepeatType)) {
          _tmpRepeatType = null;
        } else {
          _tmpRepeatType = _cursor.getString(_cursorIndexOfRepeatType);
        }
        _item.setRepeatType(_tmpRepeatType);
        final long _tmpRepeatEndDate;
        _tmpRepeatEndDate = _cursor.getLong(_cursorIndexOfRepeatEndDate);
        _item.setRepeatEndDate(_tmpRepeatEndDate);
        final int _tmpPomodoroCount;
        _tmpPomodoroCount = _cursor.getInt(_cursorIndexOfPomodoroCount);
        _item.setPomodoroCount(_tmpPomodoroCount);
        final long _tmpEstimatedTime;
        _tmpEstimatedTime = _cursor.getLong(_cursorIndexOfEstimatedTime);
        _item.setEstimatedTime(_tmpEstimatedTime);
        final long _tmpActualTime;
        _tmpActualTime = _cursor.getLong(_cursorIndexOfActualTime);
        _item.setActualTime(_tmpActualTime);
        final String _tmpColor;
        if (_cursor.isNull(_cursorIndexOfColor)) {
          _tmpColor = null;
        } else {
          _tmpColor = _cursor.getString(_cursorIndexOfColor);
        }
        _item.setColor(_tmpColor);
        final boolean _tmpIsFavorite;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp_4 != 0;
        _item.setFavorite(_tmpIsFavorite);
        final int _tmpStreakDays;
        _tmpStreakDays = _cursor.getInt(_cursorIndexOfStreakDays);
        _item.setStreakDays(_tmpStreakDays);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getAllProjects() {
    final String _sql = "SELECT DISTINCT project FROM tasks WHERE project != 'Без проекта'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final String _item;
        if (_cursor.isNull(0)) {
          _item = null;
        } else {
          _item = _cursor.getString(0);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
