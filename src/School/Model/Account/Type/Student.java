package School.Model.Account.Type;

import School.Model.Account.Account;
import School.Model.Subject.Subject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Student extends Account{

    private int id;
    private String studentId;
    private String course;
    public int accountId;
    public int sectionId;

    private List<Subject> subjects;

    public Student(int id, String studentId, String course, int accountId, 
                int sectionId, List<Subject> subjects, 
                String firstName, String middleName, String lastName, 
                String gender, java.time.LocalDate birthDate,
                String phoneNumber, String address) {

        super(id, firstName, middleName, lastName, gender, birthDate, phoneNumber, address);
        
        this.id = id;
        this.studentId = studentId;
        this.course = course;
        this.accountId = accountId;
        this.sectionId = sectionId;
        this.subjects = subjects;
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
    
    public void remove(Subject subject) {
        subjects.remove(id);
    }
    

    public void updateCredentials(String course) {
        this.course = course;
    }
    
    public int getId(){ return id; }
    public String getStudentId(){ return studentId; }
    public String getCourse(){ return course; }
    public int getAccountId(){ return accountId; }
    public int getSectionId(){ return sectionId; }
    
    public void Update(Student student){
        this.studentId = student.studentId;
        this.course = student.course;
        this.sectionId = student.sectionId;
    }
}   