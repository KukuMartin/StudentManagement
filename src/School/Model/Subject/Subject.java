package School.Model.Subject;

import School.Model.Attendance.Attendance;
import java.util.List;
import java.util.UUID;

public class Subject {
    enum State{
        PENDING,
        PASSED,
        FAILED;
    }
    
    String studentId;
    UUID subjectId;
    
    List<Assessment> midtermsWork;
    Attendance midtermsAttendance;
    
    List<Assessment> finalsWork;
    Attendance finalsAttendance;
    
    public Subject(String studentId, UUID subjectId, List<Assessment> midtermsWork, 
                   List<Attendance> midtermsAttendance, List<Assessment> finalsWork, 
                   List<Attendance> finalsAttendance){
    }
    
    //if midterms = true operat on the midterm version nad if false on finals
    public Assessment search(boolean midterm, String name){
    }
    
    public Assessment search(boolean midterm, int index){
    }
    
    public void add(boolean midterm, Assessment assessment){
    }
    
    public void add(boolean midterm, Assessment assessment, int index){
    }
    
    public void remove(boolean midterm, Assessment assessment){
    }
    
    public void remove(boolean midterm, int index){
    }
    
    public String getStudentId(){
    }
    
    public UUID getSubjectId(){
    }
    
    public double getGrade(boolean midterm){
    }
    
    public double getTotalGrade(){
    }
    
    public int getSize(boolean midterm){
        
    }
}
