package School.Management;

import School.Model.Subject.Activity;
import School.Model.Subject.Assessment;
import java.util.List;
import javax.security.auth.Subject;

public class SubjectStructureManagement {
    List<Subject> subjects;
    
    public SubjectStructureManagement(List<Subject> subjects){
        this.subjects = subjects;
    }
    
    public Subject search(String studentId){
        return null;
    }
    
    public Subject search(int index){
        return null;
    }
    //FIXME: change this they shouldnt be able to add or remove students
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
    
    public void setAssessmentName(Assessment assessment, String name){
    }
    
    public void setAssessmentPercent(Assessment assessment, double percent){
    }
    
    public void setActivityName(Activity Activity, String name){
    }
    
    public void setActivityTotalScore(Activity Activity, int totalScore){
    }
    
}
