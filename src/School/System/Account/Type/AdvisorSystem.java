package School.System.Account.Type;

import School.Management.Account.Type.AdvisorManagement;
import School.Model.Account.Type.Advisor;
import School.System.Account.AccountSystem;
import School.System.Account.SectionSystem;

import java.sql.Connection;
import java.util.List;

public class AdvisorSystem {

    private AdvisorManagement management;
    private SectionSystem sectionSystem;
    private AccountSystem accountSystem;

    public AdvisorSystem(Connection sql) {

        this.accountSystem = new AccountSystem(sql);
        this.sectionSystem = new SectionSystem(sql);

        this.management = new AdvisorManagement(
                sql,
                sectionSystem,
                accountSystem
        );
    }

    public boolean createAdvisor(Advisor advisor) {
        if (isAdvisorInvalid(advisor)) {
            return false;
        }

        management.add(advisor);
        return true;
    }

    public boolean deleteAdvisor(int id) {
        if (id <= 0) {
            return false;
        }

        Advisor temp = new Advisor(id, null, null, 0, null, null, null, null, null, null);
        int result = management.remove(id);
        return result > 0;
    }

    public boolean updateAdvisor(Advisor advisor) {
        if (isAdvisorInvalid(advisor)) {
            return false;
        }

        int result = management.update(advisor);
        return result > 0;
    }

    public Advisor getAdvisor(String username) {
        if (username == null) {
            return null;
        }

        return management.search(username);
    }

    public List<Advisor> getAllAdvisors() {
        return management.getAdvisors();
    }

    public SectionSystem getSectionSystem() {
        return sectionSystem;
    }

    public boolean isAdvisorInvalid(Advisor advisor) {
        if (advisor == null) return true;

        if (advisor.getId() <= 0) return true;
        if (advisor.getAccountId() <= 0) return true;
        if (advisor.getUsername() == null || advisor.getUsername().isBlank()) return true;
        if (advisor.getPassword() == null || advisor.getPassword().isBlank()) return true;

        return false;
    }
}