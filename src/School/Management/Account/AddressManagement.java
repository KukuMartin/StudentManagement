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

        try (PreparedStatement stmt = sql.prepareStatement(query)) {

            stmt.setInt(1, accountId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Address address = new Address(rs.getInt("accountId"));

                address.setHouseNumber(rs.getString("houseNumber"));
                address.setStreet(rs.getString("street"));
                address.setBarangay(rs.getString("barangay"));
                address.setCity(rs.getString("city"));
                address.setProvince(rs.getString("province"));
                address.setZipCode(rs.getString("zipCode"));

                return address;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void add(Address address) {

        String query = "INSERT INTO " + table +
                " (accountId, houseNumber, street, barangay, city, province, zipCode) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = sql.prepareStatement(query)) {

            stmt.setInt(1, address.getAccountId());
            stmt.setString(2, address.getHouseNumber());
            stmt.setString(3, address.getStreet());
            stmt.setString(4, address.getBarangay());
            stmt.setString(5, address.getCity());
            stmt.setString(6, address.getProvince());
            stmt.setString(7, address.getZipCode());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int update(Address address) {

        String query = "UPDATE " + table +
                " SET houseNumber = ?, street = ?, barangay = ?, city = ?, province = ?, zipCode = ? WHERE accountId = ?";

        int result = 0;

        try (PreparedStatement stmt = sql.prepareStatement(query)) {

            stmt.setString(1, address.getHouseNumber());
            stmt.setString(2, address.getStreet());
            stmt.setString(3, address.getBarangay());
            stmt.setString(4, address.getCity());
            stmt.setString(5, address.getProvince());
            stmt.setString(6, address.getZipCode());
            stmt.setInt(7, address.getAccountId());

            result = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int remove(int accountId) {

        String query = "DELETE FROM " + table + " WHERE accountId = ?";

        int result = 0;

        try (PreparedStatement stmt = sql.prepareStatement(query)) {

            stmt.setInt(1, accountId);
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}