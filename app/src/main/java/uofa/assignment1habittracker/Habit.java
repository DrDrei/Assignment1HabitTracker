package uofa.assignment1habittracker;

import android.text.format.DateUtils;
import android.widget.DatePicker;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Habit implements Serializable {
    private String name;
    private String startDate;
    private Map<Integer, Boolean> weeklyCompletionList = new HashMap<Integer, Boolean>();
    private Map<String, Integer> habitList = new HashMap<String, Integer>();

    private void daysOfWeekInitFalse() {
        for (DaysOfWeek day: DaysOfWeek.values()) {
            weeklyCompletionList.put(day.getDay(), false);
        }
    }

    // ==========       Initialization      ==========
    public Habit(String name) {
        this.name = name;
        this.daysOfWeekInitFalse();
        this.startDate = getToday();
    }

    public Habit(String name, ArrayList<DaysOfWeek> wantedCompletionList) {
        this.name = name;
        this.startDate = getToday();
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

    public boolean getWeeklyDay(Integer day) {
        return weeklyCompletionList.get(day);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void updateHabit(String name, ArrayList<DaysOfWeek> wantedCompletionList, DatePicker datePicker) {
        this.name = name;
        this.startDate = getDateFromPicker(datePicker);
        setWeeklyCompletion(wantedCompletionList);
    }

    public Date getStartDate() {
        try {
            return getDateFromString(this.startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public String getTodaysCount() {
//        Calendar cal = Calendar.getInstance();
//        int weekday = cal.getInstance().get(Calendar.DAY_OF_WEEK);
        if (habitList.containsKey(getToday())) {
            return Integer.toString(habitList.get(getToday()));
        } else  {
            return "0";
        }
    }


    // ===============================================


    // ==========       Helper Funcitons    ==========

    public void incrementDayHabit(String date) {
        if (!habitList.containsKey(date)) {
            habitList.put(date, 1);
        } else {
            habitList.put(date, habitList.get(date) + 1);
        }
    }

    public void incrementTodaysHabit() {
        incrementDayHabit(getToday());
    }

    public void decrementDayHabit(String date) {
        if (habitList.containsKey(date)) {
            if (habitList.get(date) == 1) {
                habitList.remove(date);
            } else {
                habitList.put(date, habitList.get(date) - 1);
            }
        }
    }

    public void decrementTodaysHabit() {
        decrementDayHabit(getToday());
    }

    public Map<String, Integer> getCompletionMap() {
        return habitList;
    }

    public String toString() {
        return this.name;
    }

    private String getDateFromPicker(DatePicker datePicker) {
        Calendar cal = Calendar.getInstance();
        cal.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        cal.get(Calendar.DAY_OF_WEEK);
        return getStringFromDate(cal.getTime());
    }

    private String getToday() {
        return getStringFromDate(new Date());
    }

    private String getStringFromDate(Date date) {
        DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormatter.format(date);
    }

    private Date getDateFromString(String dateString) throws ParseException {
        DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = dateFormatter.parse(dateString);
        return date;
    }
    // ===============================================

}
