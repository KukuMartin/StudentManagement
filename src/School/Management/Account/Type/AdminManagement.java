package School.Management.Account.Type;

import School.Data.DatabaseTable;
import School.Model.Account.Type.Admin;
import School.System.Account.AccountSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminManagement {

    private String table = DatabaseTable.ADMIN;
    private Connection sql;

    private AccountSystem accountSystem;

    public AdminManagement(Connection sql, AccountSystem accountSystem) {
        this.sql = sql;
        this.accountSystem = accountSystem;
    }

    public Admin search(String username) {

        String query = "SELECT * FROM " + table + " WHERE username = ?";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, username);

            ResultSet result = command.executeQuery();

            if (result.next()) {

                int id = result.getInt("id");
                int accountId = result.getInt("accountId");

                var account = accountSystem.getAccount(accountId);

                return new Admin(
                        id,
                        result.getString("username"),
                        result.getString("password"),
                        accountId,
                        account.getName(),
                        account.getGender(),
                        account.getBirthDate(),
                        account.getPhoneNumber(),
                        account.getAddress()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void add(Admin admin) {

        String query = "INSERT INTO " + table +
                " (username, password, accountId) VALUES (?, ?, ?)";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, admin.getUsername());
            command.setString(2, admin.getPassword());
            command.setInt(3, admin.getAccountId());

            command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int remove(int id) {

        String query = "DELETE FROM " + table + " WHERE id = ?";

        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, id);

            result = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int update(Admin admin) {

        String query = "UPDATE " + table +
                " SET username = ?, password = ? WHERE id = ?";

        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, admin.getUsername());
            command.setString(2, admin.getPassword());
            command.setInt(3, admin.getId());

            result = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Admin> getAdmins() {

        List<Admin> list = new ArrayList<>();

        String query = "SELECT * FROM " + table;

        try (PreparedStatement command = sql.prepareStatement(query);
             ResultSet result = command.executeQuery()) {

            while (result.next()) {

                int id = result.getInt("id");
                int accountId = result.getInt("accountId");

                var account = accountSystem.getAccount(accountId);

                list.add(new Admin(
                        id,
                        result.getString("username"),
                        result.getString("password"),
                        accountId,
                        account.getName(),
                        account.getGender(),
                        account.getBirthDate(),
                        account.getPhoneNumber(),
                        account.getAddress()
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}