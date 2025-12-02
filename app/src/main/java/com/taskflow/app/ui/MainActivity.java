package com.taskflow.app.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.taskflow.app.R;
import com.taskflow.app.data.database.AppDatabase;
import com.taskflow.app.data.model.Task;
import com.taskflow.app.ui.adapter.TaskAdapter;
import java.text.SimpleDateFormat;
import java.util.*;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private AppDatabase database;
    private String currentFilter = "all";
    private SharedPreferences prefs;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        prefs = getSharedPreferences("TaskFlowPrefs", MODE_PRIVATE);
        boolean isDarkMode = prefs.getBoolean("dark_mode", false);

        if (isDarkMode) {
            setTheme(R.style.AppTheme_Dark);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            setTheme(R.style.AppTheme);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Настройка Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = AppDatabase.getInstance(this);

        // Инициализация тестовых данных при первом запуске
        initializeTestData();

        recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton fab = findViewById(R.id.fab);

        setupAdapter();
        setupRecyclerView();
        setupCategoryChips();

        fab.setOnClickListener(v -> {
            animateFab(fab);
            showAddDialog();
        });

        setupBottomNav();
        loadTasks();
    }

    private void initializeTestData() {
        // Проверяем, были ли уже добавлены тестовые данные
        boolean isFirstRun = prefs.getBoolean("first_run", true);
        
        if (isFirstRun) {
            new Thread(() -> {
                List<Task> testTasks = createTestTasks();
                for (Task task : testTasks) {
                    database.taskDao().insertTask(task);
                }
                
                runOnUiThread(() -> {
                    prefs.edit().putBoolean("first_run", false).apply();
                    loadTasks();
                    Toast.makeText(this, "Добро пожаловать! Добавлены тестовые задачи", Toast.LENGTH_LONG).show();
                });
            }).start();
        }
    }

    private List<Task> createTestTasks() {
        List<Task> tasks = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        
        // Задача 1: Работа - высокий приоритет
        Task task1 = new Task("Подготовить презентацию для клиента", "Работа", "high");
        task1.setCategory("work");
        task1.setDescription("Создать впечатляющую презентацию с графиками и цифрами");
        cal.add(Calendar.HOUR, 4);
        task1.setDeadline(cal.getTimeInMillis());
        task1.setHasReminder(true);
        task1.setFavorite(true);
        tasks.add(task1);

        // Задача 2: Личное - средний приоритет
        Task task2 = new Task("Позвонить маме", "Семья", "medium");
        task2.setCategory("personal");
        task2.setDescription("Не забыть поздравить с днем рождения");
        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        task2.setDeadline(cal.getTimeInMillis());
        task2.setHasReminder(true);
        tasks.add(task2);

        // Задача 3: Покупки - низкий приоритет
        Task task3 = new Task("Купить продукты на неделю", "Дом", "low");
        task3.setCategory("shopping");
        task3.setDescription("Молоко, хлеб, овощи, фрукты");
        List<String> subtasks = new ArrayList<>();
        subtasks.add("Молоко 2л");
        subtasks.add("Хлеб");
        subtasks.add("Овощи");
        subtasks.add("Фрукты");
        task3.setSubtasks(subtasks);
        tasks.add(task3);

        // Задача 4: Здоровье - высокий приоритет
        Task task4 = new Task("Записаться к стоматологу", "Здоровье", "high");
        task4.setCategory("health");
        task4.setDescription("Плановый осмотр раз в полгода");
        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 2);
        task4.setDeadline(cal.getTimeInMillis());
        tasks.add(task4);

        // Задача 5: Работа - завершенная задача
        Task task5 = new Task("Отправить отчет руководителю", "Работа", "medium");
        task5.setCategory("work");
        task5.setCompleted(true);
        task5.setDescription("Ежемесячный отчет о проделанной работе");
        tasks.add(task5);

        // Задача 6: Личное - хобби
        Task task6 = new Task("Прочитать главу из книги", "Саморазвитие", "low");
        task6.setCategory("personal");
        task6.setDescription("Продолжить чтение 'Атомные привычки'");
        task6.setRepeatType("daily");
        tasks.add(task6);

        // Задача 7: Работа - встреча
        Task task7 = new Task("Встреча с командой", "Работа", "high");
        task7.setCategory("work");
        task7.setDescription("Обсуждение нового проекта");
        cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 2);
        task7.setDeadline(cal.getTimeInMillis());
        task7.setHasReminder(true);
        task7.setFavorite(true);
        tasks.add(task7);

        // Задача 8: Здоровье - тренировка
        Task task8 = new Task("Тренировка в зале", "Спорт", "medium");
        task8.setCategory("health");
        task8.setDescription("Кардио + силовые упражнения");
        task8.setRepeatType("daily");
        cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 6);
        task8.setDeadline(cal.getTimeInMillis());
        tasks.add(task8);

        // Задача 9: Личное
        Task task9 = new Task("Организовать рабочий стол", "Дом", "low");
        task9.setCategory("personal");
        task9.setDescription("Навести порядок и выбросить ненужное");
        tasks.add(task9);

        // Задача 10: Работа - обучение
        Task task10 = new Task("Пройти курс по Android", "Обучение", "medium");
        task10.setCategory("work");
        task10.setDescription("Изучить Material Design и Jetpack Compose");
        task10.setFavorite(true);
        cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        task10.setDeadline(cal.getTimeInMillis());
        tasks.add(task10);

        return tasks;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Поиск задач...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchTasks(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    loadTasks();
                } else {
                    searchTasks(newText);
                }
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_calendar) {
            startActivity(new Intent(this, CalendarActivity.class));
            return true;
        } else if (id == R.id.action_statistics) {
            startActivity(new Intent(this, StatisticsActivity.class));
            return true;
        } else if (id == R.id.action_achievements) {
            startActivity(new Intent(this, AchievementsActivity.class));
            return true;
        } else if (id == R.id.action_pomodoro) {
            startActivity(new Intent(this, PomodoroActivity.class));
            return true;
        } else if (id == R.id.action_filter) {
            showFilterDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupAdapter() {
        adapter = new TaskAdapter(new TaskAdapter.OnTaskClickListener() {
            @Override
            public void onTaskCheck(Task task) {
                task.setCompleted(!task.isCompleted());
                database.taskDao().updateTask(task);
                loadTasks();
                showToast(task.isCompleted() ? "Готово! 🎉" : "Задача открыта");
            }

            @Override
            public void onTaskDelete(Task task) {
                new MaterialAlertDialogBuilder(MainActivity.this, R.style.AlertDialogTheme)
                        .setTitle("Удалить задачу?")
                        .setMessage("Вы уверены, что хотите удалить эту задачу?")
                        .setPositiveButton("Удалить", (d, w) -> {
                            database.taskDao().deleteTask(task);
                            loadTasks();
                            showToast("Удалено");
                        })
                        .setNegativeButton("Отмена", null)
                        .show();
            }

            @Override
            public void onTaskEdit(Task task) {
                showEditDialog(task);
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    private void setupCategoryChips() {
        ChipGroup chipGroup = findViewById(R.id.categoryChipGroup);

        String[] categories = {"Все", "Работа", "Личное", "Покупки", "Здоровье", "⭐ Избранное"};
        String[] categoryValues = {"all", "work", "personal", "shopping", "health", "favorite"};
        String[] categoryEmojis = {"📋", "💼", "👤", "🛒", "💪", "⭐"};

        for (int i = 0; i < categories.length; i++) {
            Chip chip = new Chip(this);
            chip.setText(categoryEmojis[i] + " " + categories[i]);
            chip.setCheckable(true);
            chip.setChecked(i == 0);
            chip.setChipBackgroundColorResource(R.color.chip_background_color);
            chip.setTextColor(getColor(R.color.chip_text_color));
            chip.setChipStrokeWidth(1f);
            chip.setChipStrokeColorResource(R.color.chip_stroke_color);
            chip.setChipCornerRadius(48f);

            final String category = categoryValues[i];

            chip.setOnClickListener(v -> {
                currentFilter = category;
                loadTasks();

                for (int j = 0; j < chipGroup.getChildCount(); j++) {
                    Chip c = (Chip) chipGroup.getChildAt(j);
                    c.setChecked(c == chip);
                }

                animateChip(chip);
            });

            chipGroup.addView(chip);
        }
    }

    private void loadTasks() {
        List<Task> tasks;

        if (currentFilter.equals("all")) {
            tasks = database.taskDao().getAllTasks();
        } else if (currentFilter.equals("favorite")) {
            tasks = database.taskDao().getFavoriteTasks();
        } else {
            tasks = database.taskDao().getTasksByCategory(currentFilter);
        }

        adapter.setTasks(tasks);
    }

    private void searchTasks(String query) {
        adapter.setTasks(database.taskDao().searchTasks(query));
    }

    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_task, null);

        EditText titleInput = dialogView.findViewById(R.id.titleInput);
        EditText projectInput = dialogView.findViewById(R.id.projectInput);
        Spinner prioritySpinner = dialogView.findViewById(R.id.prioritySpinner);
        Spinner categorySpinner = dialogView.findViewById(R.id.categorySpinner);
        TextView deadlineText = dialogView.findViewById(R.id.deadlineText);
        Button setDeadlineBtn = dialogView.findViewById(R.id.setDeadlineBtn);
        CheckBox reminderCheckBox = dialogView.findViewById(R.id.reminderCheckBox);

        // Настройка спиннеров
        ArrayAdapter<CharSequence> priorityAdapter = ArrayAdapter.createFromResource(this,
                R.array.priority_array, android.R.layout.simple_spinner_item);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(priorityAdapter);
        prioritySpinner.setSelection(1);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setSelection(1);

        final long[] selectedDeadline = {0};

        setDeadlineBtn.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();

            new DatePickerDialog(this, (view, year, month, day) -> {
                new TimePickerDialog(this, (timeView, hour, minute) -> {
                    calendar.set(year, month, day, hour, minute);
                    selectedDeadline[0] = calendar.getTimeInMillis();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
                    deadlineText.setText("Дедлайн: " + sdf.format(calendar.getTime()));
                    deadlineText.setVisibility(View.VISIBLE);
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        builder.setTitle("Новая задача")
                .setView(dialogView)
                .setPositiveButton("Добавить", (d, w) -> {
                    String title = titleInput.getText().toString().trim();
                    String project = projectInput.getText().toString().trim();

                    if (!title.isEmpty()) {
                        if (project.isEmpty()) project = "Без проекта";

                        String priority = prioritySpinner.getSelectedItem().toString().toLowerCase();
                        if (priority.equals("высокий")) priority = "high";
                        else if (priority.equals("средний")) priority = "medium";
                        else if (priority.equals("низкий")) priority = "low";

                        String category = getCategoryValue(categorySpinner.getSelectedItemPosition());

                        Task task = new Task(title, project, priority);
                        task.setCategory(category);
                        task.setDeadline(selectedDeadline[0]);
                        task.setHasReminder(reminderCheckBox.isChecked());

                        database.taskDao().insertTask(task);
                        loadTasks();
                        Toast.makeText(this, "Задача добавлена! ✓", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Введите название задачи", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Отмена", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showEditDialog(Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_task, null);

        EditText titleInput = dialogView.findViewById(R.id.titleInput);
        EditText projectInput = dialogView.findViewById(R.id.projectInput);
        Spinner prioritySpinner = dialogView.findViewById(R.id.prioritySpinner);
        Spinner categorySpinner = dialogView.findViewById(R.id.categorySpinner);
        TextView deadlineText = dialogView.findViewById(R.id.deadlineText);
        Button setDeadlineBtn = dialogView.findViewById(R.id.setDeadlineBtn);
        CheckBox reminderCheckBox = dialogView.findViewById(R.id.reminderCheckBox);

        // Заполняем существующими данными
        titleInput.setText(task.getTitle());
        projectInput.setText(task.getProject());
        reminderCheckBox.setChecked(task.hasReminder());

        // Настройка спиннеров
        ArrayAdapter<CharSequence> priorityAdapter = ArrayAdapter.createFromResource(this,
                R.array.priority_array, android.R.layout.simple_spinner_item);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(priorityAdapter);
        prioritySpinner.setSelection(getPriorityPosition(task.getPriority()));

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setSelection(getCategoryPosition(task.getCategory()));

        final long[] selectedDeadline = {task.getDeadline()};

        // Показываем текущий дедлайн если есть
        if (task.getDeadline() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
            deadlineText.setText("Дедлайн: " + sdf.format(new Date(task.getDeadline())));
            deadlineText.setVisibility(View.VISIBLE);
        }

        setDeadlineBtn.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            if (selectedDeadline[0] > 0) {
                calendar.setTimeInMillis(selectedDeadline[0]);
            }

            new DatePickerDialog(this, (view, year, month, day) -> {
                new TimePickerDialog(this, (timeView, hour, minute) -> {
                    calendar.set(year, month, day, hour, minute);
                    selectedDeadline[0] = calendar.getTimeInMillis();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
                    deadlineText.setText("Дедлайн: " + sdf.format(calendar.getTime()));
                    deadlineText.setVisibility(View.VISIBLE);
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        builder.setTitle("Редактировать задачу")
                .setView(dialogView)
                .setPositiveButton("Сохранить", (d, w) -> {
                    String title = titleInput.getText().toString().trim();
                    String project = projectInput.getText().toString().trim();

                    if (!title.isEmpty()) {
                        if (project.isEmpty()) project = "Без проекта";

                        String priority = prioritySpinner.getSelectedItem().toString().toLowerCase();
                        if (priority.equals("высокий")) priority = "high";
                        else if (priority.equals("средний")) priority = "medium";
                        else if (priority.equals("низкий")) priority = "low";

                        String category = getCategoryValue(categorySpinner.getSelectedItemPosition());

                        task.setTitle(title);
                        task.setProject(project);
                        task.setPriority(priority);
                        task.setCategory(category);
                        task.setDeadline(selectedDeadline[0]);
                        task.setHasReminder(reminderCheckBox.isChecked());

                        database.taskDao().updateTask(task);
                        loadTasks();
                        Toast.makeText(this, "Задача обновлена! ✓", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Введите название задачи", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Отмена", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showFilterDialog() {
        String[] filters = {"Все задачи", "Только активные", "Только завершённые",
                "С дедлайном", "Просроченные", "Повторяющиеся"};

        new MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
                .setTitle("Фильтры")
                .setItems(filters, (dialog, which) -> {
                    // TODO: Применить фильтр
                    loadTasks();
                })
                .show();
    }

    private void setupBottomNav() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_tasks) {
                loadTasks();
            } else if (id == R.id.nav_stats) {
                startActivity(new Intent(this, StatisticsActivity.class));
            } else if (id == R.id.nav_settings) {
                showSettings();
            }
            return true;
        });
    }

    private void showSettings() {
        View settingsView = getLayoutInflater().inflate(R.layout.dialog_settings, null);
        SwitchMaterial darkModeSwitch = settingsView.findViewById(R.id.darkModeCheckBox);

        darkModeSwitch.setChecked(prefs.getBoolean("dark_mode", false));

        new MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
                .setTitle("⚙️ Настройки")
                .setView(settingsView)
                .setPositiveButton("Сохранить", (d, w) -> {
                    boolean isDarkMode = darkModeSwitch.isChecked();
                    prefs.edit().putBoolean("dark_mode", isDarkMode).apply();
                    recreate();
                })
                .setNegativeButton("Отмена", null)
                .show();
    }

    private void animateFab(View fab) {
        fab.animate()
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(100)
                .withEndAction(() -> {
                    fab.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(100)
                            .start();
                })
                .start();
    }

    private void animateChip(View chip) {
        chip.animate()
                .scaleX(1.05f)
                .scaleY(1.05f)
                .setDuration(100)
                .withEndAction(() -> {
                    chip.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(100)
                            .start();
                })
                .start();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private String getCategoryValue(int position) {
        String[] values = {"work", "personal", "shopping", "health", "other"};
        if (position >= 0 && position < values.length) {
            return values[position];
        }
        return "personal";
    }

    private int getCategoryPosition(String category) {
        String[] values = {"work", "personal", "shopping", "health", "other"};
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(category)) return i;
        }
        return 1;
    }

    private int getPriorityPosition(String priority) {
        String[] values = {"high", "medium", "low"};
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(priority)) return i;
        }
        return 1;
    }
}
