package School.Management.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import School.Data.DatabaseTable;
import School.Model.Account.Address;

public class AddressManagement {

    private String table = DatabaseTable.ADDRESS;
    private Connection sql;

    public AddressManagement(Connection sql) {
        this.sql = sql;
    }

    public Address search(int accountId) {

        String query = "SELECT * FROM " + table + " WHERE accountId = ?";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, accountId);

            ResultSet result = command.executeQuery();

            if (result.next()) {
                return new Address(
                    result.getInt("accountId"),
                    result.getString("houseNumber"),
                    result.getString("street"),
                    result.getString("barangay"),
                    result.getString("city"),
                    result.getString("province"),
                    result.getString("zipCode")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void add(Address address) {

        String query = "INSERT INTO " + table +
                " (accountId, houseNumber, street, barangay, city, province, zipCode) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, address.getAccountId());
            command.setString(2, address.getHouseNumber());
            command.setString(3, address.getStreet());
            command.setString(4, address.getBarangay());
            command.setString(5, address.getCity());
            command.setString(6, address.getProvince());
            command.setString(7, address.getZipCode());

            command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int remove(int accountId) {

        String query = "DELETE FROM " + table + " WHERE accountId = ?";

        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, accountId);

            result = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int update(Address address) {

        String query = "UPDATE " + table +
                " SET houseNumber = ?, street = ?, barangay = ?, city = ?, province = ?, zipCode = ? WHERE accountId = ?";

        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, address.getHouseNumber());
            command.setString(2, address.getStreet());
            command.setString(3, address.getBarangay());
            command.setString(4, address.getCity());
            command.setString(5, address.getProvince());
            command.setString(6, address.getZipCode());
            command.setInt(7, address.getAccountId());

            result = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}