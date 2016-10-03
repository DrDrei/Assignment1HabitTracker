package uofa.assignment1habittracker;

public enum DaysOfWeek {
    Sunday      (1, R.id.add_sunday),
    Monday      (2, R.id.add_monday),
    Tuesday     (3, R.id.add_tuesday),
    Wednesday   (4, R.id.add_wednesday),
    Thursday    (5, R.id.add_thursday),
    Friday      (6, R.id.add_friday),
    Saturday    (7, R.id.add_saturday);

    private final Integer day;
    private final int id;

    DaysOfWeek(Integer day, int id) {
        this.day = day;
        this.id = id;
    }

    public Integer getDay() {
        return day;
    }

    public int getID() {
        return this.id;
    }

}
