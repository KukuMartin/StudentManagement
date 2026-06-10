package School.Model.Subject;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Subject {

    public enum State {
        PENDING,
        PASSED,
        FAILED
    }

    private int id;
    private String name;
    private String code;
    private LocalTime scheduleStart;
    private LocalTime scheduleEnd;

    private int teacherId;
    private List<Record> records;

    public Subject(int id, String name, String code,
                   LocalTime scheduleStart, LocalTime scheduleEnd,
                   int teacherId,
                   List<Record> records) {

        this.id = id;
        this.name = name;
        this.code = code;
        this.scheduleStart = scheduleStart;
        this.scheduleEnd = scheduleEnd;
        this.teacherId = teacherId;
        this.records = (records != null) ? records : new ArrayList<>();
    }

    public Record search(int studentId) {
        for (Record r : records) {
            if (r.getStudentId() == studentId) {
                return r;
            }
        }
        return null;
    }

    public void add(Record record) {
        if (record == null) return;
        records.add(record);
    }

    public void remove(Record record) {
        records.remove(record);
    }

    public void update(Subject subject) {
        if (subject == null) return;

        this.name = subject.name;
        this.code = subject.code;
        this.scheduleStart = subject.scheduleStart;
        this.scheduleEnd = subject.scheduleEnd;
        this.teacherId = subject.teacherId;
    }

    public double getTotalGrade() {
        if (records.isEmpty()) return 0.0;

        double total = 0;

        for (Record r : records) {
            total += r.getGrade();
        }

        return total / records.size();
    }

    public State getState() {
        if (records.isEmpty()) return State.PENDING;

        double grade = getTotalGrade();

        if (grade >= 75) return State.PASSED;
        if (grade > 0) return State.FAILED;

        return State.PENDING;
    }

    public int getSize() {
        return records.size();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCode() { return code; }
    public LocalTime getScheduleStart() { return scheduleStart; }
    public LocalTime getScheduleEnd() { return scheduleEnd; }

    public int getTeacherId() { return teacherId; }
    public List<Record> getRecords() { return records; }
}