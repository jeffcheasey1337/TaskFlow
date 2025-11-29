package com.taskflow.app.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private SearchView searchView;
    private String currentFilter = "all";
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Применяем тему
        prefs = getSharedPreferences("TaskFlowPrefs", MODE_PRIVATE);
        boolean isDarkMode = prefs.getBoolean("dark_mode", false);
        AppCompatDelegate.setDefaultNightMode(
                isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = AppDatabase.getInstance(this);

        recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton fab = findViewById(R.id.fab);

        setupAdapter();
        setupRecyclerView();
        setupCategoryChips();

        fab.setOnClickListener(v -> showAddDialog());

        setupBottomNav();
        loadTasks();
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
        if (item.getItemId() == R.id.action_sort_priority) {
            showPriorityFilter();
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
                Toast.makeText(MainActivity.this, "Готово! 🎉", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTaskDelete(Task task) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Удалить задачу?")
                        .setMessage("Вы уверены, что хотите удалить эту задачу?")
                        .setPositiveButton("Удалить", (d, w) -> {
                            database.taskDao().deleteTask(task);
                            loadTasks();
                            Toast.makeText(MainActivity.this, "Удалено", Toast.LENGTH_SHORT).show();
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

        // Drag & Drop для изменения порядка
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {

            @Override
            public boolean onMove(RecyclerView rv, RecyclerView.ViewHolder vh, RecyclerView.ViewHolder target) {
                int fromPos = vh.getAdapterPosition();
                int toPos = target.getAdapterPosition();

                List<Task> tasks = adapter.getTasks();
                Collections.swap(tasks, fromPos, toPos);

                // Обновляем sortOrder
                for (int i = 0; i < tasks.size(); i++) {
                    tasks.get(i).setSortOrder(i);
                    database.taskDao().updateTask(tasks.get(i));
                }

                adapter.notifyItemMoved(fromPos, toPos);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder vh, int direction) {
                // Не используется
            }
        });

        helper.attachToRecyclerView(recyclerView);
    }

    private void setupCategoryChips() {
        ChipGroup chipGroup = findViewById(R.id.categoryChipGroup);

        String[] categories = {"Все", "Работа", "Личное", "Покупки", "Здоровье"};
        String[] categoryValues = {"all", "work", "personal", "shopping", "health"};

        for (int i = 0; i < categories.length; i++) {
            Chip chip = new Chip(this);
            chip.setText(categories[i]);
            chip.setCheckable(true);
            chip.setChecked(i == 0);

            final String category = categoryValues[i];
            chip.setOnClickListener(v -> {
                currentFilter = category;
                loadTasks();

                // Снимаем выделение с других чипов
                for (int j = 0; j < chipGroup.getChildCount(); j++) {
                    Chip c = (Chip) chipGroup.getChildAt(j);
                    c.setChecked(c == chip);
                }
            });

            chipGroup.addView(chip);
        }
    }

    private void loadTasks() {
        List<Task> tasks;

        if (currentFilter.equals("all")) {
            tasks = database.taskDao().getAllTasks();
        } else {
            tasks = database.taskDao().getTasksByCategory(currentFilter);
        }

        adapter.setTasks(tasks);
    }

    private void searchTasks(String query) {
        adapter.setTasks(database.taskDao().searchTasks(query));
    }

    private void showAddDialog() {
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

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

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

        new AlertDialog.Builder(this)
                .setTitle("Новая задача")
                .setView(dialogView)
                .setPositiveButton("Добавить", (d, w) -> {
                    String title = titleInput.getText().toString().trim();
                    String project = projectInput.getText().toString().trim();

                    if (!title.isEmpty()) {
                        if (project.isEmpty()) project = "Без проекта";

                        String priority = prioritySpinner.getSelectedItem().toString().toLowerCase();
                        String category = getCategoryValue(categorySpinner.getSelectedItemPosition());

                        Task task = new Task(title, project, priority);
                        task.setCategory(category);
                        task.setDeadline(selectedDeadline[0]);
                        task.setHasReminder(reminderCheckBox.isChecked());

                        database.taskDao().insertTask(task);
                        loadTasks();
                        Toast.makeText(this, "Задача добавлена! ✓", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Отмена", null)
                .show();
    }

    private void showEditDialog(Task task) {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_task, null);
        EditText titleInput = dialogView.findViewById(R.id.titleInput);
        EditText projectInput = dialogView.findViewById(R.id.projectInput);
        Spinner prioritySpinner = dialogView.findViewById(R.id.prioritySpinner);
        Spinner categorySpinner = dialogView.findViewById(R.id.categorySpinner);
        TextView deadlineText = dialogView.findViewById(R.id.deadlineText);
        Button setDeadlineBtn = dialogView.findViewById(R.id.setDeadlineBtn);
        CheckBox reminderCheckBox = dialogView.findViewById(R.id.reminderCheckBox);

        // Заполняем текущими значениями
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

        if (task.getDeadline() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
            deadlineText.setText("Дедлайн: " + sdf.format(new Date(task.getDeadline())));
            deadlineText.setVisibility(View.VISIBLE);
        }

        final long[] selectedDeadline = {task.getDeadline()};

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

        new AlertDialog.Builder(this)
                .setTitle("Редактировать задачу")
                .setView(dialogView)
                .setPositiveButton("Сохранить", (d, w) -> {
                    String title = titleInput.getText().toString().trim();
                    String project = projectInput.getText().toString().trim();

                    if (!title.isEmpty()) {
                        if (project.isEmpty()) project = "Без проекта";

                        task.setTitle(title);
                        task.setProject(project);
                        task.setPriority(prioritySpinner.getSelectedItem().toString().toLowerCase());
                        task.setCategory(getCategoryValue(categorySpinner.getSelectedItemPosition()));
                        task.setDeadline(selectedDeadline[0]);
                        task.setHasReminder(reminderCheckBox.isChecked());

                        database.taskDao().updateTask(task);
                        loadTasks();
                        Toast.makeText(this, "Задача обновлена! ✓", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Отмена", null)
                .show();
    }

    private void showPriorityFilter() {
        String[] priorities = {"Все", "Высокий", "Средний", "Низкий"};

        new AlertDialog.Builder(this)
                .setTitle("Фильтр по приоритету")
                .setItems(priorities, (dialog, which) -> {
                    if (which == 0) {
                        loadTasks();
                    } else {
                        String priority = priorities[which].toLowerCase();
                        if (priority.equals("высокий")) priority = "high";
                        else if (priority.equals("средний")) priority = "medium";
                        else priority = "low";

                        adapter.setTasks(database.taskDao().getTasksByPriority(priority));
                    }
                })
                .show();
    }

    private void setupBottomNav() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_stats) {
                showStats();
            } else if (item.getItemId() == R.id.nav_settings) {
                showSettings();
            } else if (item.getItemId() == R.id.nav_tasks) {
                loadTasks();
            }
            return true;
        });
    }

    private void showStats() {
        int completed = database.taskDao().getCompletedTaskCount();
        int active = database.taskDao().getActiveTaskCount();
        int overdue = database.taskDao().getOverdueTaskCount(System.currentTimeMillis());

        String message = "📊 Статистика\n\n" +
                "✅ Выполнено: " + completed + "\n" +
                "⏳ Активных: " + active + "\n" +
                "⚠️ Просрочено: " + overdue;

        new AlertDialog.Builder(this)
                .setTitle("Статистика")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private void showSettings() {
        View settingsView = getLayoutInflater().inflate(R.layout.dialog_settings, null);
        CheckBox darkModeCheckBox = settingsView.findViewById(R.id.darkModeCheckBox);

        darkModeCheckBox.setChecked(prefs.getBoolean("dark_mode", false));

        new AlertDialog.Builder(this)
                .setTitle("⚙️ Настройки")
                .setView(settingsView)
                .setPositiveButton("Сохранить", (d, w) -> {
                    boolean isDarkMode = darkModeCheckBox.isChecked();
                    prefs.edit().putBoolean("dark_mode", isDarkMode).apply();

                    AppCompatDelegate.setDefaultNightMode(
                            isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
                    );

                    recreate();
                })
                .setNegativeButton("Отмена", null)
                .show();
    }

    private String getCategoryValue(int position) {
        String[] values = {"work", "personal", "shopping", "health", "other"};
        return values[position];
    }

    private int getCategoryPosition(String category) {
        String[] values = {"work", "personal", "shopping", "health", "other"};
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(category)) return i;
        }
        return 1; // default personal
    }

    private int getPriorityPosition(String priority) {
        String[] values = {"high", "medium", "low"};
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(priority)) return i;
        }
        return 1; // default medium
    }
}