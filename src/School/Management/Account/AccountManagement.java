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

        try (PreparedStatement stmt = sql.prepareStatement(query)) {
            stmt.setInt(1, id);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return new Account(
                    result.getInt("id"),
                    result.getString("first_name"),
                    result.getString("middle_name"),
                    result.getString("last_name"),
                    result.getString("gender"),
                    result.getDate("birth_date").toLocalDate(),
                    result.getString("phone_number"),
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
            " (first_name, middle_name, last_name, gender, birth_date, phone_number, address) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, account.getFirstName());
            command.setString(2, account.getMiddleName());
            command.setString(3, account.getLastName());
            command.setString(4, account.getGender());
            command.setDate(5, Date.valueOf(account.getBirthDate()));
            command.setString(6, account.getPhoneNumber());
            command.setString(7, account.getAddress().toString());

            command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int update(Account account) {
        String query = "UPDATE " + table +
            " SET first_name=?, middle_name=?, last_name=?, gender=?, " +
            "birth_date=?, phone_number=?, address=? WHERE id=?";

        try (PreparedStatement stmt = sql.prepareStatement(query)) {

            stmt.setString(1, account.getFirstName());
            stmt.setString(2, account.getMiddleName());
            stmt.setString(3, account.getLastName());
            stmt.setString(4, account.getGender());
            stmt.setDate(5, Date.valueOf(account.getBirthDate()));
            stmt.setString(6, account.getPhoneNumber());
            stmt.setString(7, account.getAddress());
            stmt.setInt(8, account.getId());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int remove(int id) {
        String query = "DELETE FROM " + table + " WHERE id = ?";

        try (PreparedStatement command=  sql.prepareStatement(query)) {
            command.setInt(1, id);
            return command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();

        String query = "SELECT * FROM " + table;

        try (PreparedStatement stmt = sql.prepareStatement(query);
             ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                list.add(new Account(
                    result.getInt("id"),
                    result.getString("first_name"),
                    result.getString("middle_name"),
                    result.getString("last_name"),
                    result.getString("gender"),
                    result.getDate("birth_date").toLocalDate(),
                    result.getString("phone_number"),
                    result.getString("address")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}