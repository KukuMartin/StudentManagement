package School.Management;

import School.Model.Attendance.Attendance;
import School.Model.Subject.Activity;
import School.Model.Subject.Assessment;
import java.time.LocalDate;
import java.util.List;
import javax.security.auth.Subject;

public class AttendanceStructureManagement {
    List<Attendance> attendance;
    
    public Attendance search(String studentId){
        return null;
    }
    
    public Attendance search(int index){
        return null;
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
        return null;
    }
    
    public Assessment copyAssessment(){
        return null;
    }
    
    public Activity copyActivity(){
        return null;
    }
    
    public int getSize() {
        return 0;
    }
    
    public void setDate(Activity Activity, LocalDate date){
    }
}
