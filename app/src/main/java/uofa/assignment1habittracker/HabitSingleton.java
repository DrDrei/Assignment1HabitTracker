package uofa.assignment1habittracker;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by drei on 2016-09-30.
 */

/*
    References:
    - https://www.mkyong.com/java/how-do-convert-java-object-to-from-json-format-gson-api/
    - In class LonelyTwitter app.
 */

public class HabitSingleton {
    private static String FILENAME = "habits.sav";
    private static HabitSingleton ourInstance = new HabitSingleton();
    private Context mainContext;
    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    public static HabitSingleton getInstance() {
        return ourInstance;
    }

    private HabitSingleton() {}

    public void addHabit(Habit habit, Context context) {
        this.habitList.add(habit);
        saveHabits(context);
    }

    public void removeHabit(Habit habit, Context context) {
        this.habitList.remove(habit);
        saveHabits(context);
    }

    public void setMainContext(Context context) {
        this.mainContext = context;
    }

    public void saveHabits(){
        if (mainContext != null) {
            this.saveHabits(mainContext);
        }
    }

    public ArrayList<Habit> getHabitList() {
        return this.habitList;
    }

    public ArrayList<Habit> getHabitListOnDay(Integer day) {
        ArrayList<Habit> dayHabitList = new ArrayList<Habit>();
        Iterator iter = habitList.iterator();
        while (iter.hasNext()) {
            Habit habit = (Habit) iter.next();
            if (habit.getWeeklyDay(day)) {
                dayHabitList.add(habit);
            }
        }
        return dayHabitList;
    }

    public ArrayList<Habit> getTodaysHabits() {
        Calendar cal = Calendar.getInstance();
        int today = cal.getInstance().get(Calendar.DAY_OF_WEEK);
        return getHabitListOnDay(today);
    }

    public Habit getTodaysHabitAtIndex(int position) {
        return getTodaysHabits().get(position);
    }

    public Habit getHabitAtIndex(int position) {
        return habitList.get(position);
    }

    public void loadHabits(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            Gson gson = new Gson();
            habitList = gson.fromJson(bufferedReader,listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            habitList = new ArrayList<Habit>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    public void saveHabits(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            new Gson().toJson(habitList, out);

            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }


}
