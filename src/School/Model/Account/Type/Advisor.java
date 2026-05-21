package School.Model.Account.Type;

import School.Model.Account.Account;
import School.Model.Account.Credential;
import School.System.Account.Type.SectionSystem;
import School.System.Structure.StructureSystem;
import java.util.List;
import java.util.UUID;

public class Advisor extends Account{
    String username;
    String password;
    List<SectionSystem> advisory;
    
    public Advisor(String username, String password, UUID accountId, Credential credential) {
        Account.Type type = Account.Type.ADVISOR;
        super(accountId, type);
        
        this.username = username;
        this.password = password;
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
