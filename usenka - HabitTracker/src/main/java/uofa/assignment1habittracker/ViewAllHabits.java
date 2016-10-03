package uofa.assignment1habittracker;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/*
    ViewAllHabits is an activity to show all the habits currently running
    - simple activity that links to habit being able to be edited.
    - allows incrementing habits even on days that habit is not scheduled for.
 */
public class ViewAllHabits extends Activity {
    private ListView customHabitListView;
    private TextView headerTitleText;

    private Button addHabitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_habits);


        customHabitListView = (ListView) findViewById(android.R.id.list);

        configureHeader();
        HabitSingleton.getInstance().setMainContext(getApplicationContext());


        customHabitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AddHabitActivity.class);
                intent.putExtra("habit_index", position);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_listitem_view, HabitSingleton.getInstance().getHabitList());
        adapter.addAll(HabitSingleton.getInstance().getHabitList());
        customHabitListView.setAdapter(adapter);
    }

    public void configureHeader() {
        headerTitleText = (TextView) findViewById(R.id.header_title_text);
        headerTitleText.setText("Habit Tracker");

        addHabitButton = (Button) findViewById(R.id.add_habit_button);
        addHabitButton.setVisibility(View.GONE);
    }
}
