package uofa.assignment1habittracker;

/*
    Completion Class:
    - Used to make a list of Completions
    - Used mostly to manage the completion Activity so that it is easier to set up an Adapter.
    - Contains:
        - String dateString
        - Integer completions

 */

public class Completion {
    private String dateString;
    private Integer completions;

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
