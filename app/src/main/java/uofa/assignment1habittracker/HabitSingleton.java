package uofa.assignment1habittracker;

import java.util.ArrayList;

/**
 * Created by drei on 2016-09-30.
 */
public class HabitSingleton {
    private static HabitSingleton ourInstance = new HabitSingleton();
    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    public static HabitSingleton getInstance() {
        return ourInstance;
    }

    private HabitSingleton() {}

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
}
