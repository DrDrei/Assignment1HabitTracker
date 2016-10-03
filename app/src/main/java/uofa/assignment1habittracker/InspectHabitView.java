package uofa.assignment1habittracker;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class InspectHabitView extends Activity {

    private ListView completionListView;
    private TextView headerTitleText;

    private Button addHabitButton;
    private Habit thisHabit;
    private ArrayList<Completion> completions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_habits);

//        loadHabitFromIntent();
        completionListView = (ListView) findViewById(android.R.id.list);

        configureHeader();
        HabitSingleton.getInstance().setMainContext(getApplicationContext());

    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<Completion> comp = HabitSingleton.getInstance().getCurrentHabit().getCompletionList();
//        comp.add(new  Completion("Date Here", 1));

        CompletionAdapter adapter = new CompletionAdapter(this, R.layout.custom_listitem_view, comp);
        adapter.addAll(comp);
        completionListView.setAdapter(adapter);
    }

    public void configureHeader() {
        headerTitleText = (TextView) findViewById(R.id.header_title_text);
        headerTitleText.setText("Habit Tracker");

        addHabitButton = (Button) findViewById(R.id.add_habit_button);
        addHabitButton.setVisibility(View.GONE);
    }

    private void loadHabitFromIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("habit")) {
            thisHabit = (Habit) intent.getSerializableExtra("habit");
            completions = thisHabit.getCompletionList();
        }
    }

}
