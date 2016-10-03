package uofa.assignment1habittracker;

//import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class HabitTests extends TestCase {
    public void testHabit() {
        String habitName = "New Habit";
        Habit newHabit = new Habit(habitName);
        assertTrue("Habit is not equal", habitName.equals(newHabit.getName()));
    }

    public void testIncrement() {
        Habit newHabit = new Habit("Habit");
        Date today = new Date();
        assertEquals(0, newHabit.getCount(today));
        newHabit.incrementDayHabit(today);
        assertEquals(1, newHabit.getCount(today));
    }

    public void testDecrement() {
        Habit newHabit = new Habit("Habit");
        Date today = new Date();
        newHabit.incrementDayHabit(today);
        newHabit.incrementDayHabit(today);

        newHabit.decrementDayHabit(today);
        assertEquals(1, newHabit.getCount(today));

        newHabit.decrementDayHabit(today);
        assertEquals(0, newHabit.getCount(today));
    }

    public void testDatesStored() {
        Habit newHabit = new Habit("Habit");
        Date today = new Date();

        assertEquals(0, newHabit.getDateCount());
        newHabit.incrementDayHabit(today);

        assertEquals(1, newHabit.getDateCount());
    }

    public void testInitWeeklyCompletion() {
        Habit newHabit = new Habit("Habit");
        Date today = new Date();

        Iterator iter = newHabit.getWeeklyCompletionIterator();
        while (iter.hasNext()) {
            assertFalse((Boolean) iter.next());
        }
    }

    public void testSettingUnsettingWeeklyCompletion() {
        Habit newHabit = new Habit("Habit");
        Date today = new Date();
        ArrayList<DaysOfWeek> days = new ArrayList<DaysOfWeek>();
        days.add(DaysOfWeek.Friday);
        days.add(DaysOfWeek.Sunday);
        newHabit.setWeeklyCompletion(days);

        assertTrue(newHabit.getWeeklyDay(DaysOfWeek.Friday));
        assertTrue(newHabit.getWeeklyDay(DaysOfWeek.Sunday));

        newHabit.setDayOfWeek(DaysOfWeek.Thursday);
        assertTrue(newHabit.getWeeklyDay(DaysOfWeek.Thursday));

        newHabit.unsetDayOfWeek(DaysOfWeek.Thursday);
        assertFalse(newHabit.getWeeklyDay(DaysOfWeek.Thursday));

    }


}
