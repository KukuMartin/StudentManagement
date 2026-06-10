package School.Model.Account.Type;

import School.Model.Account.Account;
import School.System.Account.Type.SectionSystem;
import School.System.Structure.StructureSystem;
import java.util.List;
import java.util.UUID;

public class Advisor extends Account{
    private int id;
    private String username;
    private String password;
    private int accountId;

    public Advisor(int id) {
        super(id);
    }
    
    
    public StructureSystem search(UUID sectionId){
        return null;
        
    }
    
    public StructureSystem search(int index){
        return null;
        
    }
    
    public String getUsername(){
        return null;
        
    }
    
    public String getPassword(){
        return null;
        
    }
    
    public void setUsername(String username){
        
    }
    
    public void setPassword(String password){
        
    }
}
