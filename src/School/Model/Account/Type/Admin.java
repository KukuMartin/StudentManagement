package School.Model.Account.Type;

import School.Model.Account.Account;

public class Admin extends Account {

    private int id;
    private String username;
    private String password;
    private int accountId;

    public Admin(int id, String username, String password, int accountId,
                String firstName, String middleName, String lastName, 
                String gender, java.time.LocalDate birthDate,
                String phoneNumber, String address) {

        super(id, firstName, middleName, lastName, gender, birthDate, phoneNumber, address);

        this.id = id;
        this.username = username;
        this.password = password;
        this.accountId = accountId;
    }

    public void update(Admin admin) {
        this.username = admin.username;
        this.password = admin.password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getAccountId() { return accountId; }
}