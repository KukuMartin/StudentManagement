package School.Management.Account;

import School.Model.Account.*;
import School.System.Account.Type.StudentSystem;
import School.Data.DatabaseTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SectionManagement {

    private String table = DatabaseTable.SECTION;
    private Connection sql;

    private StudentSystem studentSystem;

    public SectionManagement(Connection sql, StudentSystem studentSystem) {
        this.sql = sql;
        this.studentSystem = studentSystem;
    }

    public Section search(int id) {

        String query = "SELECT * FROM " + table + " WHERE id = ?";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, id);

            ResultSet result = command.executeQuery();

            if (result.next()) {

                return new Section(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("code"),
                    studentSystem.getStudents(id)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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

    public int update(Section section) {

        String query = "UPDATE " + table + " SET name = ?, code = ? WHERE id = ?";

        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, section.getName());
            command.setString(2, section.getCode());
            command.setInt(3, section.getId());

            result = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Section> getSections(int advisorId) {

        List<Section> list = new ArrayList<>();

        String query = "SELECT * FROM " + table + " WHERE id = ?";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, advisorId);

            try (ResultSet result = command.executeQuery()) {

                while (result.next()) {

                    int id = result.getInt("id");

                    list.add(new Section(
                        id,
                        result.getString("name"),
                        result.getString("code"),
                        studentSystem.getStudents(id)
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}