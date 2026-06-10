package School.Management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import School.Data.DatabaseTable;
import School.Model.Account.Section;
import java.util.ArrayList;
import java.util.List;

public class SectionManagement {
    private String table = DatabaseTable.SECTION;
    private Connection sql;
    public SectionManagement(Connection sql){
        this.sql = sql;
    }

    public void add(Section section) {
        String query = "INSERT INTO " + table + " (name, code) VALUES (?, ?)";
        try (PreparedStatement command = sql.prepareStatement(query)) {
            command.setString(1, section.getName());
            command.setString(2, section.getCode());
            command.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(int id) {
        String query = "DELETE FROM " + table + " WHERE id = ?";

        try (PreparedStatement stmt = sql.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Section search(int id) {
        String query = "SELECT * FROM " + table + " WHERE id = ?";

        try (PreparedStatement stmt = sql.prepareStatement(query)) {
            stmt.setInt(1, id);

            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return new Section(
                    result.getInt("id"), 
                    result.getString("name"), 
                    result.getString("code")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Section> getAllSection() {
        List<Section> list = new ArrayList<>();

        String query = "SELECT * FROM " + table;

        try (PreparedStatement stmt = sql.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Section(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("code")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}