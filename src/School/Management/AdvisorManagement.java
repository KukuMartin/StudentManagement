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
    }
    
    //subjects management because a teacher cant remove a class it should be an admin right?
    public SectionSystem searchSection(String username){
        
    }
}
