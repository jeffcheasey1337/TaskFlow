package com.taskflow.app.data.database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.taskflow.app.data.dao.TaskDao;
import com.taskflow.app.data.model.Task;

@Database(entities = {Task.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract TaskDao taskDao();

    // Миграция с версии 1 на версию 2
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Добавляем новые колонки
            database.execSQL("ALTER TABLE tasks ADD COLUMN category TEXT DEFAULT 'personal' NOT NULL");
            database.execSQL("ALTER TABLE tasks ADD COLUMN deadline INTEGER DEFAULT 0 NOT NULL");
            database.execSQL("ALTER TABLE tasks ADD COLUMN hasReminder INTEGER DEFAULT 0 NOT NULL");
            database.execSQL("ALTER TABLE tasks ADD COLUMN sortOrder INTEGER DEFAULT 0 NOT NULL");
        }
    };

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "taskflow_database"
                    )
                    .allowMainThreadQueries() // Для простоты. В продакшене используйте AsyncTask/Coroutines
                    .addMigrations(MIGRATION_1_2) // Добавляем миграцию
                    .fallbackToDestructiveMigration() // На случай если миграция не сработает - удалит и пересоздаст БД
                    .build();
        }
        return instance;
    }
}