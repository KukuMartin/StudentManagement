package School.Model.Account.Type;

import School.Model.Account.Account;
import School.Model.Account.Credential;
import School.System.Structure.StructureSystem;
import java.util.List;
import java.util.UUID;

public class Teacher extends Account{
    String username;
    String password;
    List<StructureSystem> subjects;
    
    public Teacher(String username, String password, UUID accountId, Credential credential) {
        Account.Type type = Account.Type.TEACHER;
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
