package School.Model.Subject;

public class Subject {
    public enum State {
        PENDING,
        PASSED,
        FAILED;
    }
    

    private int id;
    private int teacherId;
    private int studentId;

    public Subject(int id, int studentId) {
        this.id = id;
        this.studentId = studentId;
    }

    public Assessment search(Period period, String name) {
        return null;
    }

    public void add(Period period, Assessment assessment) {
    }

    public void remove(Period period, Assessment assessment) {
    }

    public int getId() {
        return id;
    }

    public Subject.State getState() {
        return null;
    }
    
    public int getStudentId() {
        return studentId;
    }


    public int getTeacherId() {
        return teacherId;
    }

    public double getGrade(Period period) {
        return 0;
    }

    public double getTotalGrade() {
        return 0;
    }

    public int getSize(boolean midterm) {
        return 0;
    }
}