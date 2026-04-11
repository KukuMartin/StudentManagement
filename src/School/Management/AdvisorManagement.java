package School.Management;

import School.Model.Account.Type.Advisor;
import School.Model.Account.Type.Teacher;
import School.System.Account.Type.SectionSystem;
import School.System.Structure.SubjectStructureSystem;
import java.util.List;

public class AdvisorManagement {
    List<Advisor> advisor;
    
    public AdvisorManagement(List<Advisor> advisor){
        this.advisor = advisor;
    }
    
    public Advisor searchAdvisor(String username){
        
    }
    
    public Advisor searchAdvisor(int index){
        
    }
    
    public void addAdvisor(Advisor advisor){
        
    }
    
    public void addAdvisor(Advisor advisor, int index){
        
    }
    
    public void removeAdvisor(Advisor advisor){
        
    }
    
    public void removeAdvisor(int index){
        
    }
    
    //subjects management because a teacher cant remove a class it should be an admin right?
    public SectionSystem searchSection(String username){
        
    }
    
    public SectionSystem searchSection(int index){
        
    }
    
    public void addSection(SubjectStructureSystem system){
        
    }
    
    public void addSection(SubjectStructureSystem system, int index){
        
    }
    
    public void removeSection(SubjectStructureSystem system){
        
    }
    
    public void removeSection(int index){
        
    }
}
