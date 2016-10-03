package uofa.assignment1habittracker;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
    References:
    - https://looksok.wordpress.com/tag/listview-item-with-button/
    CompletionAdapter is an adapter to list the Date, # of completions, and inc/dec buttons
 */

public class CompletionAdapter extends ArrayAdapter<Completion> {
    private int layoutResourceId;
    private Context context;
    private ArrayList<Completion> completions;

    public CompletionAdapter(Context context, int resource, ArrayList<Completion> completions) {
        super(context, resource);
        this.context = context;
        this.layoutResourceId = resource;
        this.completions = completions;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);

        HabitItemHolder holder = new HabitItemHolder();
        holder.completion = this.completions.get(position);
        holder.titleText = (TextView) row.findViewById(R.id.main_title);
        holder.captionText = (TextView) row.findViewById(R.id.caption_text);
        holder.incButton = (Button) row.findViewById(R.id.increment_list_button);
        holder.decButton = (Button) row.findViewById(R.id.decrement_list_button);

        holder.incButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completions.get(position).increment();
                HabitSingleton.getInstance().updateCompletions(completions);
                notifyDataSetChanged();
            }
        });

        holder.decButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completions.get(position).decrement();
                HabitSingleton.getInstance().updateCompletions(completions);
                notifyDataSetChanged();
            }
        });
        row.setTag(holder);

        setupHabitItem(holder);
        return row;
    }

    private void setupHabitItem(HabitItemHolder holder) {
        holder.titleText.setText(holder.completion.getDateString());
        holder.captionText.setText("Completions:" + holder.completion.getCompletions());
    }

    public static class HabitItemHolder {
        Completion  completion;
        TextView    titleText;
        TextView    captionText;
        Button      incButton;
        Button      decButton;
    }
}
