package School.Model.Account.Type;

import School.Model.Account.Account;
import School.Model.Account.Credential;
import School.Model.Subject.Subject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Student extends Account{
    private UUID sectionId;
    private String studentId;
    private List<Subject> subjects = new ArrayList<>();
    
    
    private static int year = LocalDate.now().getYear();
    private static int index = 1;
    private static String city = "BN-0";
    
    public Student(UUID accountId, Credential credential, Type type) {
        super(accountId, type);
        String serial = "0".repeat(6 - String.valueOf(index).length());
        studentId = year + serial + index + city;
    }
    
    public Subject search(UUID subjectId){
        
    }
    
    public Subject search(int index){
        
    }
    
    public void add(Subject subject){
    }
    
    public void add(Subject subject, int index){
    }
    
    public void remove(Subject subject){
    }
    
    public void remove(int index){
    }
    
    public UUID getSectionId(){
        
    }
    
    public String getStudentId(){
        
    }
    
    public void getStudentId(Student){
        
    }
}
