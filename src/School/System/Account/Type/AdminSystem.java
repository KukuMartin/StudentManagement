package School.System.Account.Type;

import School.Management.Account.Type.AdminManagement;
import School.Model.Account.Type.Admin;
import School.System.Account.AccountSystem;
import School.System.Account.AddressSystem;

import java.sql.Connection;
import java.util.List;

public class AdminSystem {

    private AdminManagement management;
    private AddressSystem addressSystem;
    private AccountSystem accountSystem;

    public AdminSystem(Connection sql) {
        this.accountSystem = new AccountSystem(sql);

        management = new AdminManagement(sql, accountSystem);
    }

    public boolean createAdmin(Admin admin) {
        if (isAdminInvalid(admin)) {
            return false;
        }

        management.add(admin);
        return true;
    }

    public boolean deleteAdmin(int id) {
        if (id <= 0) {
            return false;
        }

        Admin temp = new Admin(id, null, null, 0, null, null, null, null, null, null, null);
        int result = management.remove(temp);
        return result > 0;
    }

    public boolean updateAdmin(Admin admin) {
        if (isAdminInvalid(admin)) {
            return false;
        }

        int result = management.update(admin);
        return result > 0;
    }

    public Admin getAdmin(String username) {
        if (username == null) {
            return null;
        }

        return management.search(username);
    }

    public List<Admin> getAllAdmins() {
        return management.getAdmins();
    }

    public AddressSystem getAddressSystem() {
        return addressSystem;
    }

    private boolean isAdminInvalid(Admin admin) {
        if (admin == null) return true;

        if (admin.getId() <= 0) return true;
        if (admin.getAccountId() <= 0) return true;
        if (admin.getUsername() == null || admin.getUsername().isBlank()) return true;
        if (admin.getPassword() == null || admin.getPassword().isBlank()) return true;

        return false;
    }
}