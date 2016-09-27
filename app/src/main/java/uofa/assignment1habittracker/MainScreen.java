package uofa.assignment1habittracker;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainScreen extends Activity {

    private ListView habitListView;
    private ArrayList<Habit> habitMainList = new ArrayList<Habit>();
    private ArrayAdapter<Habit> habitArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);



        habitListView = (ListView) findViewById(android.R.id.list);

        ArrayList<Integer> dow = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7));
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
