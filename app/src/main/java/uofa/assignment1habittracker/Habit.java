package uofa.assignment1habittracker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by drei on 2016-09-26.
 */

public class Habit {
    private String title;
    private Map<Integer, Boolean> daysOfWeekCompletion = new HashMap<Integer, Boolean>();
    private Map<Date, Integer> habitList = new HashMap<Date, Integer>();
    private void daysOfWeekInit() {
        for (DaysOfWeek day: DaysOfWeek.values()) {
            daysOfWeekCompletion.put(day.getDay(), false);
        }
    }

    public Habit(String title) {
        this.title = title;
        this.daysOfWeekInit();
    }

    public Habit(String title, ArrayList<Integer> wantedCompletionList) {
        this.title = title;
        this.daysOfWeekInit();
        updateDaysOfWeek(wantedCompletionList);
    }

    public void updateDaysOfWeek(ArrayList<Integer> wantedCompletionList) {
        for (DaysOfWeek day: DaysOfWeek.values()) {
            if (wantedCompletionList.contains(day.getDay())) {
                daysOfWeekCompletion.put(day.getDay(), true);
            } else {
                daysOfWeekCompletion.put(day.getDay(), false);
            }
        }
    }

    public void incrementDayHabit(Date date) {
        if (!habitList.containsKey(date)) {
            habitList.put(date, 1);
        } else {
            habitList.put(date, habitList.get(date) + 1);
        }
    }

    public void decrementDayHabit(Date date) {
        if (habitList.containsKey(date)) {
            if (habitList.get(date) == 1) {
                habitList.remove(date);
            } else {
                Integer temp = habitList.get(date);
                habitList.put(date, habitList.get(date) - 1);
            }
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle(String title) {
        return this.title;
    }

    public Map<Date, Integer> getHabitList() {
        return habitList;
    }

    public String toString() {
        return this.title;
    }
}
