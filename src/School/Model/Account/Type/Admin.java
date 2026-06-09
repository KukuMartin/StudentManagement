package School.Model.Account.Type;

public class Admin {

    private int id;
    private String username;
    private String password;
    private int accountId;

    public Admin(int id, String username, int accountId) {
        this.id = id;
        this.username = username;
        this.accountId = accountId;
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