package School.Model.Attendance;

import School.Model.Subject.Period;
import java.time.LocalDate;

public class Attendance {

    public enum State {
        PRESENT(0),
        EXCUSED(1),
        LATE(2),
        ABSENT(3);

        private int index;

        State(int index) { this.index = index; }

        public int getIndex() { return index; }
    }

    private int id;
    private int subjectId;
    private Period period;

    public Attendance(int id, int subjectId, Period period) {
        this.id = id;
        this.subjectId = subjectId;
        this.period = period;
    }

    public Day search(LocalDate date) { return null; }
    public void add(Day day) { }
    public void remove(Day day) { }

    public int getId() { return id; }
    public int getSubjectId() { return subjectId; }
    public Period getPeriod() { return period; }

    public void setId(int id) { this.id = id; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }
    public void setPeriod(Period period) { this.period = period; }

    public int getSize() { return 0; }
    public int getStateSize(Attendance.State type) { return 0; }
}