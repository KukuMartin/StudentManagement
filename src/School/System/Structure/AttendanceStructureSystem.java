package School.System.Structure;

import School.Management.AttendanceStructureManagement;
import School.Model.Subject.Activity;
import School.Model.Subject.Assessment;
import java.time.LocalDate;
import javax.security.auth.Subject;

public class AttendanceStructureSystem {
    AttendanceStructureManagement manager;
    
    public AttendanceStructureSystem(){
        
    }
    
    public Subject search(String studentId){
        return null;
    }
    
    public Subject search(int index){
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
