package uofa.assignment1habittracker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by drei on 2016-09-30.
 */

/*
    References:
    - https://www.mkyong.com/java/how-do-convert-java-object-to-from-json-format-gson-api/

 */

public class HabitSingleton {
    private static HabitSingleton ourInstance = new HabitSingleton();

    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    public static HabitSingleton getInstance() {
        return ourInstance;
    }

    private HabitSingleton() {
        loadHabits();
    }

    public void addHabit(Habit habit) {
        this.habitList.add(habit);
    }

    public void removeHabit(Habit habit) {
        this.habitList.remove(habit);
    }

    public ArrayList<Habit> getHabitList() {
        return this.habitList;
    }

    public Habit getHabitAtIndex(int position) {
        return habitList.get(position);
    }

    private void loadHabits() {
        Type typeList = new TypeToken<ArrayList<Habit>>(){}.getType();
        String jsonString = new Gson().toJson(habitList, typeList);
        habitList = new Gson().fromJson(jsonString, typeList);
    }

    private void pushHabit(Habit habit) throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("D://habits.json");
        gson.toJson(habit, fileWriter);
    }

    private void pullHabit() {

    }
}
