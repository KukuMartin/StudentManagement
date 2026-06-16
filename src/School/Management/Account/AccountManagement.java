package School.Management.Account;

import School.Model.Account.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import School.Data.DatabaseTable;

public class AccountManagement {

    private final Connection sql;
    private final String table = DatabaseTable.ACCOUNT;

    public AccountManagement(Connection sql) {
        this.sql = sql;
    }

    public Account search(int id) {

        String query = "SELECT * FROM " + table + " WHERE id = ?";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, id);

            ResultSet result = command.executeQuery();

            if (result.next()) {

                return new Account(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("gender"),
                    result.getDate("birthDate").toLocalDate(),
                    result.getString("phoneNumber"),
                    result.getString("address")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void add(Account account) {

        String query = "INSERT INTO " + table +
                " (name, gender, birthDate, phoneNumber, address) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, account.getName());
            command.setString(2, account.getGender());
            command.setDate(3, Date.valueOf(account.getBirthDate()));
            command.setString(4, account.getPhoneNumber());
            command.setString(5, account.getAddress());

            command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int remove(Account account) {

        String query = "DELETE FROM " + table + " WHERE id = ?";

        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, account.getId());

            result = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int update(Account account) {

        String query = "UPDATE " + table +
                " SET firstName = ?, middleName = ?, lastName = ?, gender = ?, birthDate = ?, phoneNumber = ? WHERE id = ?";

        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, account.getName());
            command.setString(2, account.getGender());
            command.setDate(3, Date.valueOf(account.getBirthDate()));
            command.setString(4, account.getPhoneNumber());
            command.setInt(5, account.getId());

            result = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Account> getAccounts() {

        List<Account> list = new ArrayList<>();

        String query = "SELECT * FROM " + table;

        try (PreparedStatement command = sql.prepareStatement(query);
             ResultSet result = command.executeQuery()) {

            while (result.next()) {

                int id = result.getInt("id");

                list.add(new Account(
                    id,
                    result.getString("name"),
                    result.getString("gender"),
                    result.getDate("birthDate").toLocalDate(),
                    result.getString("phoneNumber"),
                    result.getString("address")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}