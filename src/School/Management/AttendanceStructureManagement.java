package School.Management;

import School.Model.Attendance.Attendance;
import School.Model.Subject.Activity;
import School.Model.Subject.Assessment;
import School.Model.Subject.Subject;
import java.time.LocalDate;
import java.util.List;

public class AttendanceStructureManagement {
    List<Attendance> attendance;
    
    public Attendance search(String studentId){
    }
    
    public Attendance search(int index){
        
    }
    //TODO: make sure to use copy when you add
    public void addAllAssessment(Assessment assessment){
        
    }
    
    public void addAllAssessment(Assessment assessment, int index){
        
    }
    
    public void addAllActivity(Assessment assessment, Activity Activity){
    }
    
    public void addAllActivity(Assessment assessment, Activity Activity, int index){
    }
    
    public void removeAllAssessment(Assessment assessment){
        
    }
    
    public void removeAllAssessment(int index){
        
    }
    
    public void removeAllActivity(Assessment assessment, Activity Activity){
        
    }
    
    public void removeAllActivity(Assessment assessment, int index){
        
    }
    
    public Subject copySubject(){
    }
    
    public Assessment copyAssessment(){
    }
    
    public Activity copyActivity(){
    }
    
    public int getSize() {
    }
    
    public void setDate(Activity Activity, LocalDate date){
    }
}
