package School.System;

import School.Model.Subject.Assessment;
import java.util.UUID;
import javax.security.auth.Subject;

public class SubjectSystem {
    Subject subject;
    public Assessment search(boolean midterm, String name){
        return null;
    }
    
    public Assessment search(boolean midterm, int index){
        return null;
    }
    
    void add(boolean midterm, Assessment assessment){
    }
    
    void add(boolean midterm, Assessment assessment, int index){
    }
    
    void remove(boolean midterm, Assessment assessment){
    }
    
    void remove(boolean midterm, int index){
    }
    
    public String getStudentId(){
        return null;
    }
    
    public UUID getSubjectId(){
        return null;
    }
    
    public double getGrade(boolean midterm){
        return 0;
    }
    
    public double getTotalGrade(){
        return 0;
    }
    
    public int getSize(boolean midterm){
        return 0;
        
    }
}
