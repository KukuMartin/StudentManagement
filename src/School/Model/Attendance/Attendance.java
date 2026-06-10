package School.Model.Attendance;

import School.Model.Subject.Period;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Attendance {

    public enum State {
        PRESENT(0),
        EXCUSED(1),
        LATE(2),
        ABSENT(3);

        private int index;

        State(int index) {
            this.index = index;
        }

        public int getIndex() { return index; }
    }

    private int id;
    private int subjectId;
    private Period period;
    private List<Day> days;

    public Attendance(int id, int subjectId, Period period, List<Day> days) {
        this.id = id;
        this.subjectId = subjectId;
        this.period = period;
        this.days = (days != null) ? days : new ArrayList<>();
    }

    public Day search(LocalDate date) {
        for (Day day: days) {
            if (day.getDate().equals(date)) {
                return day;
            }
        }
        return null;
    }

    public void add(Day day) {
        days.add(day);
    }

    public void remove(Day day) {
        days.remove(day);
    }

    public int getStateSize(State type) {
        int count = 0;

        for (Day day : days) {
            if (day.getState() == type) {
                count++;
            }
        }

        return count;
    }

    public int getId() { return id; }
    public int getSubjectId() { return subjectId; }
    public Period getPeriod() { return period; }
    public List<Day> getDays() { return days; }

    public void setId(int id) { this.id = id; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }
    public void setPeriod(Period period) { this.period = period; }
}