package uofa.assignment1habittracker;

/**
 * Created by drei on 2016-10-03.
 */

public class Completion {
    private String dateString;
    private Integer completions;

    public Completion(String dateString) {
        this.dateString = dateString;
        this.completions = 0;
    }

    public Completion(String dateString, Integer completions) {
        this.dateString = dateString;
        this.completions = completions;
    }

    public Integer getCompletions() {
        return completions;
    }

    public String getDateString() {
        return dateString;
    }

    public void increment() {
        completions += 1;
    }

    public void decrement() {
        if (completions > 0) {
            completions -= 1;
        }
    }

}
