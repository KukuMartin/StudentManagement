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
        super(accountId, credential, type);
        
        this.username = username;
        this.password = password;
    }
    
    public String getUsername(){
        
    }
    
    public String getPassword(){
        
    }
    
    public void setUsername(String username){
        
    }
    
    public void setPassword(String password){
        
    }
}
