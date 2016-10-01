package uofa.assignment1habittracker;

/**
 * Created by drei on 2016-09-30.
 */
public class SingletonHabits {
    private static SingletonHabits ourInstance = new SingletonHabits();

    public static SingletonHabits getInstance() {
        return ourInstance;
    }

    private SingletonHabits() {
    }


}
