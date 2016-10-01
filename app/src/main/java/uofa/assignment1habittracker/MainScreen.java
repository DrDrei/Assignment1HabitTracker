package uofa.assignment1habittracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class MainScreen extends Activity {

    private ListView habitListView;
    private TextView rowTextView;

    private TextView headerTitleText;

    private Button incRowButton;
    private Button decRowButton;

    private Button addHabitButton;
//    private ArrayList<Habit> habitMainList = new ArrayList<Habit>();
    private ArrayAdapter<Habit> habitArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        incRowButton = (Button) findViewById(R.id.incRowButton);
        decRowButton = (Button) findViewById(R.id.decRowButton);
        rowTextView = (TextView) findViewById(R.id.rowTextView);

        habitListView = (ListView) findViewById(android.R.id.list);

        configureHeader();

        addHabitButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddHabitActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        habitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Habit habit = (Habit) habitListView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), AddHabitActivity.class);
                intent.putExtra("habit_index", position);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        habitArrayAdapter = new ArrayAdapter<Habit>(this, R.layout.list_textview, HabitSingleton.getInstance().getHabitList());
        habitListView.setAdapter(habitArrayAdapter);
    }

    public void configureHeader() {
        headerTitleText = (TextView) findViewById(R.id.header_title_text);
        headerTitleText.setText("Habit Tracker");

        addHabitButton = (Button) findViewById(R.id.add_habit_button);
        addHabitButton.setText("Add Habit");
    }
}
