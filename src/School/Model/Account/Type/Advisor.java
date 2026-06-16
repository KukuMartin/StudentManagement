package School.Model.Account.Type;

import School.Model.Account.Account;
import School.Model.Account.Section;
import School.System.Account.SectionSystem;
import School.System.Account.Type.AdminSystem;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Advisor extends Account{
    private int id;
    private String username;
    private String password;
    private int accountId;
    private List<Section> sections;

    public Advisor(int id, String username, String password, int accountId, List<Section> section,
                String firstName, String middleName, String lastName, 
                String gender, java.time.LocalDate birthDate,
                String phoneNumber, String address) {

        super(id, firstName, middleName, lastName, gender, birthDate, phoneNumber, address);
        
        this.id = id;
        this.username = username;
        this.password = password;
        this.accountId = accountId;
        this.sections = sections;
    }
    public Section search(int sectionId) {
        for (Section section: sections) {
            if (section.getId() == sectionId) {
                return section;
            }
        }
        return null;
    }

    public void add(Section section) {
        sections.add(section);
    }
    
    public void remove(Section section) {
        sections.remove(section);
    }
    

    public void Update(Advisor advisor){
        username = advisor.getUsername();
        password = advisor.getPassword();
    }
    
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getId() { return id; }
    public int getAccountId() { return accountId; }
    public List<Section> getSubjects() { return sections; }
    
}
