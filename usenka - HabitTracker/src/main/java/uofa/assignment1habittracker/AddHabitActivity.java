package uofa.assignment1habittracker;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;

/*
    AddHabitActivity is an activity for the adding of Habits
    - Sets up a new habit, give the habit a new title, weeklycompletions, and date
    - Adds the new habit to the Singleton if saved.
    - Also provides the option of deleting a habit if called and has intent.
    - Also provides the option of checking Completions for habit if called and has intent.
 */

public class AddHabitActivity extends Activity {
    private CheckBox checkMon, checkTue, checkWed, checkThu, checkFri, checkSat, checkSun;
    private DatePicker datePicker;
    private EditText habitNameText;
    private TextView headerTitleText;

    private Button saveHabitButton;
    private Button deleteButton;
    private Button inspectHabitButton;

    private Habit newHabit;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_habit_view);

        this.configureHeader();
        deleteButton = (Button) findViewById(R.id.delete_habit_button);
        inspectHabitButton = (Button) findViewById(R.id.inspect_habit_button);

        this.initializeCheckboxes();
        datePicker = (DatePicker) findViewById(R.id.habit_date_picker);
        habitNameText = (EditText) findViewById(R.id.habit_name_editText);

        loadHabitFromIntent();

        saveHabitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!habitNameText.getText().toString().equals("") && (newHabit == null)) {
                    newHabit = new Habit(habitNameText.getText().toString(), getWeekArray(), datePicker);
                    HabitSingleton.getInstance().addHabit(newHabit, getApplicationContext());
                    finish();
                } else if (!habitNameText.getText().toString().equals("")) {
                    newHabit.updateHabit(habitNameText.getText().toString(), getWeekArray(), datePicker);
                    finish();
                } else {
                    habitNameText.setError("Give the habit a name.");
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HabitSingleton.getInstance().removeHabit(newHabit, getApplicationContext());
                finish();
            }
        });

        inspectHabitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InspectHabitView.class);
                HabitSingleton.getInstance().setCurrentHabit(newHabit);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    // Configures the app header.
    public void configureHeader() {
        headerTitleText = (TextView) findViewById(R.id.header_title_text);
        headerTitleText.setText("Add a Habit");

        saveHabitButton = (Button) findViewById(R.id.add_habit_button);
        saveHabitButton.setText("Save");
    }

    private void initializeCheckboxes() {
        checkMon = (CheckBox) findViewById(DaysOfWeek.Monday.getID());
        checkTue = (CheckBox) findViewById(DaysOfWeek.Tuesday.getID());
        checkWed = (CheckBox) findViewById(DaysOfWeek.Wednesday.getID());
        checkThu = (CheckBox) findViewById(DaysOfWeek.Thursday.getID());
        checkFri = (CheckBox) findViewById(DaysOfWeek.Friday.getID());
        checkSat = (CheckBox) findViewById(DaysOfWeek.Saturday.getID());
        checkSun = (CheckBox) findViewById(DaysOfWeek.Sunday.getID());
    }

    private ArrayList<DaysOfWeek> getWeekArray() {
        ArrayList<DaysOfWeek> dow = new ArrayList<>();
        if (checkMon.isChecked()) {
            dow.add(DaysOfWeek.Monday);
        }
        if (checkTue.isChecked()) {
            dow.add(DaysOfWeek.Tuesday);
        }
        if (checkWed.isChecked()) {
            dow.add(DaysOfWeek.Wednesday);
        }
        if (checkThu.isChecked()) {
            dow.add(DaysOfWeek.Thursday);
        }
        if (checkFri.isChecked()) {
            dow.add(DaysOfWeek.Friday);
        }
        if (checkSat.isChecked()) {
            dow.add(DaysOfWeek.Saturday);
        }
        if (checkSun.isChecked()) {
            dow.add(DaysOfWeek.Sunday);
        }
        return dow;
    }

    private void loadHabitFromIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("habit_index")) {
            habitNameText = (EditText) findViewById(R.id.habit_name_editText);
            int position = (int) intent.getSerializableExtra("habit_index");
            newHabit = HabitSingleton.getInstance().getTodaysHabitAtIndex(position);
            habitNameText.setText(newHabit.getName());
            deleteButton.setVisibility(View.VISIBLE);
            inspectHabitButton.setVisibility(View.VISIBLE);

            // load date load checkboxes.
            checkSun.setChecked(newHabit.getWeeklyDay(DaysOfWeek.Sunday.getDay()));
            checkMon.setChecked(newHabit.getWeeklyDay(DaysOfWeek.Monday.getDay()));
            checkTue.setChecked(newHabit.getWeeklyDay(DaysOfWeek.Tuesday.getDay()));
            checkWed.setChecked(newHabit.getWeeklyDay(DaysOfWeek.Wednesday.getDay()));
            checkThu.setChecked(newHabit.getWeeklyDay(DaysOfWeek.Thursday.getDay()));
            checkFri.setChecked(newHabit.getWeeklyDay(DaysOfWeek.Friday.getDay()));
            checkSat.setChecked(newHabit.getWeeklyDay(DaysOfWeek.Saturday.getDay()));

            Calendar cal = Calendar.getInstance();
            cal.setTime(newHabit.getStartDate());

            datePicker.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        }
    }

}
