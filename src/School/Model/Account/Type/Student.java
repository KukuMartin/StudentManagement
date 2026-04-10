package School.Model.Account.Type;

import School.Model.Account.Account;
import School.Model.Account.Credential;
import java.util.UUID;

public class Student extends Account{
    UUID sectionId;
    String studentId;
    
    public Student(UUID accountId, Credential credential, Type type) {
        super(accountId, credential, type);
    }
    
    public UUID getSectionId(){
        
    }
    
    public String getStudentId(){
        
    }
}
