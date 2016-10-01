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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class AddHabitActivity extends Activity {


    private CheckBox checkMon, checkTue, checkWed, checkThu, checkFri, checkSat, checkSun;
    private DatePicker datePicker;
    private EditText habitNameText;
    private TextView headerTitleText;

    private Button saveHabitButton;
    private Button deleteButton;

    private Habit newHabit;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_habit_view);

        this.configureHeader();
        deleteButton = (Button) findViewById(R.id.delete_habit_button);

        this.initializeCheckboxes();
        datePicker = (DatePicker) findViewById(R.id.habit_date_picker);
        habitNameText = (EditText) findViewById(R.id.habit_name_editText);

        loadHabitFromIntent();

        saveHabitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!habitNameText.getText().toString().equals("")) {
                    newHabit = new Habit(habitNameText.getText().toString(), getWeekArray(), getDate());
                    HabitSingleton.getInstance().addHabit(newHabit, getApplicationContext());
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
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

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
        if (checkMon.isActivated()) {
            dow.add(DaysOfWeek.Monday);
        } else if (checkTue.isActivated()) {
            dow.add(DaysOfWeek.Tuesday);
        } else if (checkWed.isActivated()) {
            dow.add(DaysOfWeek.Wednesday);
        } else if (checkThu.isActivated()) {
            dow.add(DaysOfWeek.Thursday);
        } else if (checkFri.isActivated()) {
            dow.add(DaysOfWeek.Friday);
        } else if (checkSat.isActivated()) {
            dow.add(DaysOfWeek.Saturday);
        } else if (checkSun.isActivated()) {
            dow.add(DaysOfWeek.Sunday);
        }
        return dow;
    }

    private Date getDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        return cal.getTime();
    }

    private void loadHabitFromIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("habit_index")) {
            habitNameText = (EditText) findViewById(R.id.habit_name_editText);
            int position = (int) intent.getSerializableExtra("habit_index");
            newHabit = HabitSingleton.getInstance().getHabitAtIndex(position);
            habitNameText.setText(newHabit.getName());
            deleteButton.setVisibility(View.VISIBLE);
            // do something else
        }
    }

}
