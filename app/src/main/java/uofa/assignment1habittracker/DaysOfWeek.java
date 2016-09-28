package uofa.assignment1habittracker;

/**
 * Created by drei on 2016-09-26.
 */

public enum DaysOfWeek {
    Sunday      (1),
    Monday      (2),
    Tuesday     (3),
    Wednesday   (4),
    Thursday    (5),
    Friday      (6),
    Saturday    (7);

    private Integer day;

    DaysOfWeek(Integer day) {
        this.day = day;
    }

    public Integer getDay() {
        return day;
    }

}
