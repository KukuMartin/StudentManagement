package School.Model.Account;

import School.Model.Account.Type.Student;
import java.util.ArrayList;
import java.util.List;

public class Section {
    private int id;
    private String name;
    private String code;
    private List<Student> students = new ArrayList<>();

    public Section(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Student search(String studentId) {
        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                return s;
            }
        }
        return null;
    }

    public void add(Student student) {
        students.add(student);
    }

    public void remove(Student student) {
        students.remove(student);
    }

    public int getSize() {
        return students.size();
    }
    
    public int getId() { return id; }
    public String getName() { return name; }
    public String getCode() { return code; }
    
    public void setName(String name) { this.name = name; }
    public void setCode(String code) { this.code = code; }
}