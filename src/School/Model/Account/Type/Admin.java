package School.Model.Account.Type;

import School.Model.Account.Account;

public class Admin extends Account{

    private int id;
    private String username;
    private String password;
    private int accountId;

    public Admin(int adminId, int accountId) {
        super(accountId);
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}