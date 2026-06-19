package School.System.Account;

import School.Management.Account.AccountManagement;
import School.Model.Account.Account;

import java.sql.Connection;
import java.util.List;

public class AccountSystem {

    private AccountManagement management;

    public AccountSystem(Connection sql) {
        management = new AccountManagement(sql);
    }

    public int createAccount(Account account) {
        if (isAccountInvalid(account)) {
            return -1;
        }

        int result = management.add(account);
        return result;
    }

    public boolean deleteAccount(int id) {
        if (id <= 0) {
            return false;
        }
        int result = management.remove(id);
        return result > 0;
    }

    public boolean updateAccount(Account account) {
        if (isAccountInvalid(account)) {
            return false;
        }

        int result = management.update(account);
        return result > 0;
    }

    public Account getAccount(int id) {
        if (id <= 0) {
            return null;
        }

        return management.search(id);
    }

    public List<Account> getAllAccounts() {
        return management.getAccounts();
    }

    public boolean isAccountInvalid(Account account) {
        if (account == null) return true;

        // Fixed the first line to check firstName instead of lastName
        if (account.getFirstName() == null || account.getFirstName().isBlank()) return true;
        if (account.getMiddleName() == null || account.getMiddleName().isBlank()) return true;
        if (account.getLastName() == null || account.getLastName().isBlank()) return true;
        if (account.getGender() == null || account.getGender().isBlank()) return true;
        if (account.getBirthDate() == null) return true;
        if (account.getPhoneNumber() == null || account.getPhoneNumber().isBlank()) return true;
        if (account.getAddress() == null || account.getAddress().isBlank()) return true;

        return false;
    }
}