package School.Model.Account.Type;

import School.Model.Subject.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Student extends Account{

    private int id;
    private String studentId;
    private String course;
    private int accountId;
    private int sectionId;

    private List<Subject> subjects;

    public Student(int id, String studentId, String course, int sectionId) {
        this.id = id;
        this.studentId = studentId;
        this.course = course;
        this.sectionId = sectionId;
        this.subjects = new ArrayList<>();
    }

    public Subject search(int subjectId) {
        for (Subject subject : subjects) {
            if (subject.getId() == subjectId) {
                return subject;
            }
        }
        return null;
    }

    public void add(Subject subject) {
        subjects.add(subject);
    }

    public void add(Subject subject, int index) {
        subjects.add(index, subject);
    }

    public void remove(Subject subject) {
        subjects.remove(subject);
    }

    public void remove(int index) {
        subjects.remove(index);
    }

    public int getSectionId() {
        return sectionId;
    }

    public String getStudentId() {
        return studentId;
    }
}