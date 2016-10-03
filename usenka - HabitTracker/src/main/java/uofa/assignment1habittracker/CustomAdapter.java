package uofa.assignment1habittracker;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by drei on 2016-10-02.
 */

/*
    References:
    - https://looksok.wordpress.com/tag/listview-item-with-button/
    CustomAdapter is an adapter to list the habit name, completions, and inc/dec buttons
    Simple adapter, not much to say
 */
public class CustomAdapter extends ArrayAdapter<Habit> {
    private int layoutResourceId;
    private Context context;
    private ArrayList<Habit> habits;

    public CustomAdapter(Context context, int resource, ArrayList<Habit> habits) {
        super(context, resource);
        this.context = context;
        this.layoutResourceId = resource;
        this.habits = habits;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);

        HabitItemHolder holder = new HabitItemHolder();
        holder.habit = this.habits.get(position);
        holder.titleText = (TextView) row.findViewById(R.id.main_title);
        holder.captionText = (TextView) row.findViewById(R.id.caption_text);
        holder.incButton = (Button) row.findViewById(R.id.increment_list_button);
        holder.decButton = (Button) row.findViewById(R.id.decrement_list_button);

        holder.incButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habits.get(position).incrementTodaysHabit();
                HabitSingleton.getInstance().saveHabits();
                notifyDataSetChanged();
            }
        });

        holder.decButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habits.get(position).decrementTodaysHabit();
                HabitSingleton.getInstance().saveHabits();
                notifyDataSetChanged();
            }
        });
        row.setTag(holder);

        setupHabitItem(holder);
        return row;
    }

    private void setupHabitItem(HabitItemHolder holder) {
        holder.titleText.setText(holder.habit.getName());
        holder.captionText.setText("Todays Completions:" + holder.habit.getTodaysCount());
    }

    public static class HabitItemHolder {
        Habit       habit;
        TextView    titleText;
        TextView    captionText;
        Button      incButton;
        Button      decButton;
    }
}
