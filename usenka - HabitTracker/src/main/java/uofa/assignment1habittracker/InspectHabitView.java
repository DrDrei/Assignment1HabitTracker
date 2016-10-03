package uofa.assignment1habittracker;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/*
    InspectHabitView is an activity for inspecting the completions of a habit.
    - simply shows the dates and completions for habits that has any completions

 */
public class InspectHabitView extends Activity {

    private ListView completionListView;
    private TextView headerTitleText;

    private Button addHabitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_habits);

        completionListView = (ListView) findViewById(android.R.id.list);

        configureHeader();
        HabitSingleton.getInstance().setMainContext(getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<Completion> comp = HabitSingleton.getInstance().getCurrentHabit().getCompletionList();

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

}
