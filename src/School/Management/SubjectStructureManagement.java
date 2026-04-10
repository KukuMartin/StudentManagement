package School.Management;

import School.Model.Subject.Activity;
import School.Model.Subject.Assessment;
import School.Model.Subject.Subject;
import java.util.List;

public class SubjectStructureManagement {
    List<Subject> subjects;
    
    public SubjectStructureManagement(List<Subject> subjects){
        this.subjects = subjects;
    }
    
    public Subject search(String studentId){
    }
    
    public Subject search(int index){
        
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
    
    public void setAssessmentName(Assessment assessment, String name){
    }
    
    public void setAssessmentPercent(Assessment assessment, double percent){
    }
    
    public void setActivityName(Activity Activity, String name){
    }
    
    public void setActivityTotalScore(Activity Activity, int totalScore){
    }
    
}
