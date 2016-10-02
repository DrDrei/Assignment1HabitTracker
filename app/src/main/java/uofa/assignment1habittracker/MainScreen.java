package uofa.assignment1habittracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
    References:
    - http://jsharkey.org/blog/2008/08/18/separating-lists-with-headers-in-android-09/
    - LonelyTwitter
 */
public class MainScreen extends Activity {

    private ListView habitListView;
    private TextView rowTextView;

    private TextView headerTitleText;

    private Button incRowButton;
    private Button decRowButton;

    private Button addHabitButton;
    private ArrayAdapter<Habit> habitArrayAdapter;

    public final static String ITEM_TITLE = "title";
    public final static String ITEM_CAPTION = "caption";

    public Map<String,?> createItem(String title, String caption) {
        Map<String,String> item = new HashMap<String,String>();
        item.put(ITEM_TITLE, title);
        item.put(ITEM_CAPTION, caption);
        return item;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_screen);
//        incRowButton = (Button) findViewById(R.id.incRowButton);
//        decRowButton = (Button) findViewById(R.id.decRowButton);
//        rowTextView = (TextView) findViewById(R.id.rowTextView);
//        habitListView = (ListView) findViewById(android.R.id.list);


//        configureHeader();
        HabitSingleton.getInstance().loadHabits(getApplicationContext());


        ////////////////////////////////
        List<Map<String,?>> security = new LinkedList<Map<String,?>>();
        security.add(createItem("Remember passwords", "Save usernames and passwords for Web sites"));
        security.add(createItem("Clear passwords", "Save usernames and passwords for Web sites"));
        security.add(createItem("Show security warnings", "Show warning if there is a problem with a site's security"));

        // create our list and custom adapter
        SeparatedListAdapter adapter = new SeparatedListAdapter(this);
        adapter.addSection("Array test", new ArrayAdapter<String>(this,
                R.layout.list_item, new String[] { "First item", "Item two" }));
        adapter.addSection("Security", new SimpleAdapter(this, security, R.layout.list_complex,
                new String[] { ITEM_TITLE, ITEM_CAPTION }, new int[] { R.id.list_complex_title, R.id.list_complex_caption }));

        ListView list = new ListView(this);
        list.setAdapter(adapter);
        this.setContentView(list);
        /////////////////////////////////////////////



//        addHabitButton.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), AddHabitActivity.class);
//                startActivityForResult(intent, 0);
//            }
//        });
//
//        habitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getApplicationContext(), AddHabitActivity.class);
//                intent.putExtra("habit_index", position);
//                startActivityForResult(intent, 0);
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        habitArrayAdapter = new ArrayAdapter<Habit>(this, R.layout.list_textview, HabitSingleton.getInstance().getHabitList());
//        habitListView.setAdapter(habitArrayAdapter);
//        habitListView.setAdapter(customListViewAdapter);
    }

//    public void configureHeader() {
//        headerTitleText = (TextView) findViewById(R.id.header_title_text);
//        headerTitleText.setText("Habit Tracker");
//
//        addHabitButton = (Button) findViewById(R.id.add_habit_button);
//        addHabitButton.setText("Add Habit");
//    }
}
