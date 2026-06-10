package School.System.Account;

import School.Management.Account.AccountManagement;
import School.Model.Account.Account;

import java.sql.Connection;
import java.util.List;

public class AccountSystem {

    private AccountManagement management;
    private AddressSystem addressSystem;

    public AccountSystem(Connection sql) {
        addressSystem = new AddressSystem(sql);
        management = new AccountManagement(sql, addressSystem);
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

        Account temp = new Account(id, null, null, null, null, null, null, null);
        int result = management.remove(temp);
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

    public AddressSystem getAddressSystem() {
        return addressSystem;
    }

    private boolean isAccountInvalid(Account account) {
        if (account == null) return true;

        if (account.getId() <= 0) return true;
        if (account.getFirstName() == null || account.getFirstName().isBlank()) return true;
        if (account.getMiddleName() == null || account.getMiddleName().isBlank()) return true;
        if (account.getLastName() == null || account.getLastName().isBlank()) return true;
        if (account.getGender() == null || account.getGender().isBlank()) return true;
        if (account.getBirthDate() == null) return true;
        if (account.getPhoneNumber() == null || account.getPhoneNumber().isBlank()) return true;

        return false;
    }
}