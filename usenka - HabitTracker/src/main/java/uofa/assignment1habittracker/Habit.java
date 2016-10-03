package uofa.assignment1habittracker;

import android.widget.DatePicker;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
    Habit Class
    -
 */


public class Habit {
    private String name;
    private String startDate;
    private Map<Integer, Boolean> weeklyCompletionList = new HashMap<Integer, Boolean>();
    private Map<String, Integer> habitList = new HashMap<String, Integer>();
    private ArrayList<Completion> completions = new ArrayList<Completion>();

    private void daysOfWeekInitFalse() {
        for (DaysOfWeek day: DaysOfWeek.values()) {
            weeklyCompletionList.put(day.getDay(), false);
        }
    }

    // ==========       Initialization      ==========

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

    public void setWeeklyCompletion(ArrayList<DaysOfWeek> wantedCompletionList) {
        Iterator iter = wantedCompletionList.iterator();
        daysOfWeekInitFalse();
        while (iter.hasNext()) {
            DaysOfWeek day = (DaysOfWeek) iter.next();
            setDayOfWeek(day);
        }
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
        if (habitList.containsKey(getToday())) {
            return Integer.toString(habitList.get(getToday()));
        } else  {
            return "0";
        }
    }

    private void updateCompletionList() {
        this.completions.clear();
        Iterator iter = habitList.keySet().iterator();
        while (iter.hasNext()) {
            String dateString = (String) iter.next();
            Integer completions = habitList.get(dateString);
            Completion completion = new Completion(dateString, completions);
            this.completions.add(completion);
        }
    }

    public void overwriteCompletionList(ArrayList<Completion> completions) {
        this.completions = completions;
    }


    // ===============================================


    // ==========       Helper Funcitons    ==========

    public void incrementDayHabit(String date) {
        if (!habitList.containsKey(date)) {
            habitList.put(date, 1);
        } else {
            habitList.put(date, habitList.get(date) + 1);
        }
        this.updateCompletionList();
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
        this.updateCompletionList();
    }

    public void decrementTodaysHabit() {
        decrementDayHabit(getToday());
    }

    public ArrayList<Completion> getCompletionList() {
        return completions;
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
