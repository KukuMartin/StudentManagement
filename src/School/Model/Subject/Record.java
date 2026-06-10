package School.Model.Subject;

import java.util.ArrayList;
import java.util.List;

public class Record {

    public enum State {
        PENDING,
        PASSED,
        FAILED
    }

    private int id;
    private int subjectId;
    private int studentId;

    private List<Assessment> assessments;

    public Record(int id, int subjectId, int studentId, List<Assessment> assessments) {
        this.id = id;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.assessments = (assessments != null) ? assessments : new ArrayList<>();
    }

    public Assessment search(int assessmentId) {
        for (Assessment a : assessments) {
            if (a.getId() == assessmentId) {
                return a;
            }
        }
        return null;
    }

    public void add(Assessment assessment) {
        assessments.add(assessment);
    }

    public void remove(Assessment assessment) {
        assessments.remove(assessment);
    }

    public double getGrade() {
        if (assessments.isEmpty()) return 0.0;

        double total = 0;

        for (Assessment a : assessments) {
            total += a.getGrade();
        }

        return total / assessments.size();
    }

    public Record.State getState() {
        double grade = getGrade();

        if (grade <= 0) {
            return State.PENDING;
        }

        if (grade >= 75) {
            return State.PASSED;
        }

        return State.FAILED;
    }

    public int getSize() {
        return assessments.size();
    }

    public void update(Record record) {
        this.subjectId = record.subjectId;
        this.studentId = record.studentId;
        this.assessments = record.assessments;
    }

    public int getId() { return id; }
    public int getSubjectId() { return subjectId; }
    public int getStudentId() { return studentId; }
    public List<Assessment> getAssessments() { return assessments; }

    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
}