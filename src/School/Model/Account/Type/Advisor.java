package School.Model.Account.Type;

import School.Model.Account.Account;
import School.Model.Account.Credential;
import School.System.Account.Type.SectionSystem;
import java.util.List;
import java.util.UUID;

public class Advisor extends Account{
    String username;
    String password;
    List<SectionSystem> advisory;
    
    public Advisor(String username, String password, UUID accountId, Credential credential) {
        Account.Type type = Account.Type.ADVISOR;
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
