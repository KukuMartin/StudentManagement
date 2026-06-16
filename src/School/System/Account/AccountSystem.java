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

    public boolean createAccount(Account account) {
        if (isAccountInvalid(account)) {
            return false;
        }

        management.add(account);
        return true;
    }

    public boolean deleteAccount(int id) {
        if (id <= 0) {
            return false;
        }

        Account temp = new Account(id, null, null, null, null, null);
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

        if (account.getId() <= 0) return true;
        if (account.getName()== null || account.getName().isBlank()) return true;
        if (account.getGender() == null || account.getGender().isBlank()) return true;
        if (account.getBirthDate() == null) return true;
        if (account.getPhoneNumber() == null || account.getPhoneNumber().isBlank()) return true;
        if (account.getAddress() == null || account.getAddress().isBlank()) return true;

        return false;
    }
}