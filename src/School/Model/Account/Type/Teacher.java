package School.Model.Account.Type;

import School.Model.Account.Account;
import School.Model.Account.Address;
import School.Model.Subject.Subject;
import java.time.LocalDate;
import java.util.List;

public class Teacher extends Account {

    private int id;
    private String username;
    private String password;
    private int accountId;
    private List<Subject> subjects;

    public Teacher(int id, String username, String password,
                   int accountId, List<Subject> subjects,
                   String firstName, String middleName,
                   String lastName, String gender,
                   LocalDate birthDate, String phoneNumber,
                   Address address) {

        super(id, firstName, middleName, lastName, gender,
              birthDate, phoneNumber, address);

        this.id = id;
        this.username = username;
        this.password = password;
        this.accountId = accountId;
        this.subjects = subjects;
    }

    public Subject search(int subjectId) {
        for (Subject subject : subjects) {
            if (subject.getId() == subjectId) {
                return subject;
            }
        }
        return null;
    }

    public void add(Subject subject) {
        subjects.add(subject);
    }

    public void remove(Subject subject) {
        subjects.remove(subject);
    }

    public void update(Teacher teacher) {
        this.username = teacher.username;
        this.password = teacher.password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getId() { return id; }
    public int getAccountId() { return accountId; }
    public List<Subject> getSubjects() { return subjects; }

}