package uofa.assignment1habittracker;

import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Habit {
    private String name;
    private Date startDate;
    private Map<Integer, Boolean> weeklyCompletionList = new HashMap<Integer, Boolean>();
    private Map<Date, Integer> habitList = new HashMap<Date, Integer>();

    private void daysOfWeekInitFalse() {
        for (DaysOfWeek day: DaysOfWeek.values()) {
            weeklyCompletionList.put(day.getDay(), false);
        }
    }

    // ==========       Initialization      ==========
    public Habit(String name) {
        this.name = name;
        this.daysOfWeekInitFalse();
        this.startDate = new Date();
    }

    public Habit(String name, ArrayList<DaysOfWeek> wantedCompletionList) {
        this.name = name;
        this.startDate = new Date();
        setWeeklyCompletion(wantedCompletionList);
    }

    public Habit(String name, ArrayList<DaysOfWeek> wantedCompletionList, DatePicker datePicker){
        this.name = name;
        this.startDate = getDateFromPicker(datePicker);
        setWeeklyCompletion(wantedCompletionList);
    }

    // ===============================================


    // ==========       Getters/Setters     ==========
    public void setDayOfWeek(DaysOfWeek day) {
        weeklyCompletionList.put(day.getDay(), true);
    }

    public void unsetDayOfWeek(DaysOfWeek day) {
        weeklyCompletionList.put(day.getDay(), false);

    }

    public void setWeeklyCompletion(ArrayList<DaysOfWeek> wantedCompletionList) {
        Iterator iter = wantedCompletionList.iterator();
        daysOfWeekInitFalse();
        while (iter.hasNext()) {
            DaysOfWeek day = (DaysOfWeek) iter.next();
            setDayOfWeek(day);
        }
    }

    public int getCount(Date date) {
        if (this.habitList.get(date) != null) {
            return this.habitList.get(date);
        } else {
            return 0;
        }

    }

    public int getDateCount() {
        return  this.habitList.size();
    }

    public Iterator getWeeklyCompletionIterator() {
        return this.weeklyCompletionList.values().iterator();
    }

    public boolean getWeeklyDay(DaysOfWeek day) {
        return weeklyCompletionList.get(day.getDay());
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // ===============================================


    // ==========       Helper Funcitons    ==========

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

    public Map<Date, Integer> getHabitList() {
        return habitList;
    }

    public String toString() {
        return this.name;
    }

    private Date getDateFromPicker(DatePicker datePicker) {
        Calendar cal = Calendar.getInstance();
        cal.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        return cal.getTime();
    }

    public String getTotalCount() {
        return Integer.toString(10);
    }
    // ===============================================

}
