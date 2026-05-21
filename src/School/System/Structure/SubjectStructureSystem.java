package School.System.Structure;

import School.Management.SubjectStructureManagement;
import School.Model.Subject.Activity;
import School.Model.Subject.Assessment;
import javax.security.auth.Subject;

public class SubjectStructureSystem {
    SubjectStructureManagement manager;
    
    public SubjectStructureSystem(){
        this.manager = manager;
    }
    
    public Subject search(String studentId){
        return null;
    }
    
    public Subject search(int index){
        return null;
        
    }
    
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
    
    public void setAssessmentName(Assessment assessment, String name){
    }
    
    public void setAssessmentPercent(Assessment assessment, double percent){
    }
    
    public void setActivityName(Activity Activity, String name){
    }
    
    public void setActivityTotalScore(Activity Activity, int totalScore){
    }
}
