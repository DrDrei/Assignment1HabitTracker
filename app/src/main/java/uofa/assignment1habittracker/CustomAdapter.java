package uofa.assignment1habittracker;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by drei on 2016-10-02.
 */

/*
https://looksok.wordpress.com/tag/listview-item-with-button/
 */
public class CustomAdapter extends ArrayAdapter<Habit> {
    private int layoutResourceId;
    private Context context;

    public CustomAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.layoutResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
//        AtomPaymentHolder holder = null;
        HabitItemHolder holder = null;

        Habit habit = null;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);

        holder = new HabitItemHolder();
//        holder.atomPayment = items.get(position);
        holder.habit = HabitSingleton.getInstance().getTodaysHabits().get(position);
        holder.titleText = (TextView) row.findViewById(R.id.main_title);
        holder.captionText = (TextView) row.findViewById(R.id.caption_text);
        holder.incButton = (Button) row.findViewById(R.id.increment_list_button);
        holder.decButton = (Button) row.findViewById(R.id.decrement_list_button);
//        holder.removePaymentButton = (ImageButton)row.findViewById(R.id.atomPay_removePay);
//        holder.removePaymentButton.setTag(holder.atomPayment);
//
//        holder.name = (TextView)row.findViewById(R.id.atomPay_name);
//        holder.value = (TextView)row.findViewById(R.id.atomPay_value);

        row.setTag(holder);

        setupHabitItem(holder);
        return row;
    }

    private void setupHabitItem(HabitItemHolder holder) {
        holder.titleText.setText(holder.habit.getName());
        holder.captionText.setText(holder.habit.getTodaysCount());
    }

//    private void setupItem(AtomPaymentHolder holder) {
//        holder.name.setText(holder.atomPayment.getName());
//        holder.value.setText(String.valueOf(holder.atomPayment.getValue()));
//    }
//
//    public static class AtomPaymentHolder {
//        AtomPayment atomPayment;
//        TextView name;
//        TextView value;
//        ImageButton removePaymentButton;
//    }

    public static class HabitItemHolder {
        Habit       habit;
        TextView    titleText;
        TextView    captionText;
        Button      incButton;
        Button      decButton;

    }
}
