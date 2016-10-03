package uofa.assignment1habittracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;

/*
    References:
    - http://stacktips.com/tutorials/android/listview-with-section-header-in-android
 */

public class CustomListViewAdapter extends BaseAdapter {

    private static final int ITEM         = 0;
    private static final int SEPARATOR    = 1;

    private ArrayList<Habit> habitBuilderList = new ArrayList<Habit>();
    private TreeSet<String> sectionHeader = new TreeSet<String>();

    private LayoutInflater layoutInflater;

    public CustomListViewAdapter(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addHabit(Habit habit) {
        habitBuilderList.add(habit);
        notifyDataSetChanged();
    }

    public void addSectionHeader(String day) {
        sectionHeader.add(day);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? SEPARATOR : ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return habitBuilderList.size();
    }

    @Override
    public Object getItem(int position) {
        return habitBuilderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int rowType = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();
            switch (rowType) {
                case ITEM:
                    convertView = layoutInflater.inflate(R.layout.section_item, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.text);
                    break;
                case SEPARATOR:
                    convertView = layoutInflater.inflate(R.layout.text_separator, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.textSeparator);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(habitBuilderList.get(position).toString());

        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
    }
}
