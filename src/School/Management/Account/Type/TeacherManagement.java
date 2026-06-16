package School.Management.Account.Type;

import School.Data.DatabaseTable;
import School.Model.Account.Type.Teacher;
import School.System.Account.AccountSystem;
import School.System.Subject.SubjectSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherManagement {

    private String table = DatabaseTable.TEACHER;
    private Connection sql;

    private SubjectSystem subjectSystem;
    private AccountSystem accountSystem;

    public TeacherManagement(
            Connection sql,
            SubjectSystem subjectSystem,
            AccountSystem accountSystem
    ) {
        this.sql = sql;
        this.subjectSystem = subjectSystem;
        this.accountSystem = accountSystem;
    }

    public Teacher search(String username) {

        String query = "SELECT * FROM " + table + " WHERE username = ?";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, username);

            ResultSet result = command.executeQuery();

            if (result.next()) {

                int id = result.getInt("id");
                int accountId = result.getInt("accountId");

                var account = accountSystem.getAccount(accountId);

                return new Teacher(
                        id,
                        result.getString("username"),
                        result.getString("password"),
                        accountId,
                        subjectSystem.getSubjects(id),

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

    public void add(Teacher teacher) {

        String query = "INSERT INTO " + table +
                " (username, password, accountId) VALUES (?, ?, ?)";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, teacher.getUsername());
            command.setString(2, teacher.getPassword());
            command.setInt(3, teacher.getAccountId());

            command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int remove(Teacher teacher) {

        String query = "DELETE FROM " + table + " WHERE id = ?";

        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, teacher.getId());

            result = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int update(Teacher teacher) {

        String query = "UPDATE " + table +
                " SET username = ?, password = ? WHERE id = ?";

        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, teacher.getUsername());
            command.setString(2, teacher.getPassword());
            command.setInt(3, teacher.getId());

            result = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Teacher> getTeachers() {

        List<Teacher> list = new ArrayList<>();

        String query = "SELECT * FROM " + table;

        try (PreparedStatement command = sql.prepareStatement(query);
             ResultSet result = command.executeQuery()) {

            while (result.next()) {

                int id = result.getInt("id");
                int accountId = result.getInt("accountId");

                var account = accountSystem.getAccount(accountId);

                list.add(new Teacher(
                        id,
                        result.getString("username"),
                        result.getString("password"),
                        accountId,
                        subjectSystem.getSubjects(id),

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