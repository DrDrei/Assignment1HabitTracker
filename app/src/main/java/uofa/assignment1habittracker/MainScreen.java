package uofa.assignment1habittracker;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainScreen extends Activity {

    private ListView habitListView;
    private TextView rowTextView;
    private Button incRowButton;
    private Button decRowButton;
    private ArrayList<Habit> habitMainList = new ArrayList<Habit>();
    private ArrayAdapter<Habit> habitArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        incRowButton = (Button) findViewById(R.id.incRowButton);
        decRowButton = (Button) findViewById(R.id.decRowButton);
        rowTextView = (TextView) findViewById(R.id.rowTextView);
        habitListView = (ListView) findViewById(android.R.id.list);

        ArrayList<DaysOfWeek> dow = new ArrayList<DaysOfWeek>();
        for (DaysOfWeek day: DaysOfWeek.values()) {dow.add(day);}

        Habit newHabit = new Habit("test", dow);
        habitMainList.add(newHabit);
    }

    @Override
    protected void onStart() {
        super.onStart();
        habitArrayAdapter = new ArrayAdapter<Habit>(this, R.layout.list_textview, habitMainList);
        habitListView.setAdapter(habitArrayAdapter);
    }
}
