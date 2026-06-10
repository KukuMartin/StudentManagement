package School.Management.Account.Type;

import School.Data.DatabaseTable;
import School.Model.Account.Type.Student;
import School.System.Account.AccountSystem;
import School.System.Account.AddressSystem;
import School.System.Subject.SubjectSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentManagement {

    private String table = DatabaseTable.STUDENT;
    private Connection sql;

    private SubjectSystem subjectSystem;
    private AccountSystem accountSystem;
    private AddressSystem addressSystem;

    public StudentManagement(
            Connection sql,
            SubjectSystem subjectSystem,
            AccountSystem accountSystem,
            AddressSystem addressSystem
    ) {
        this.sql = sql;
        this.subjectSystem = subjectSystem;
        this.accountSystem = accountSystem;
        this.addressSystem = addressSystem;
    }

    public Student search(String username) {

        String query = "SELECT * FROM " + table + " WHERE username = ?";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, username);

            ResultSet result = command.executeQuery();

            if (result.next()) {

                int id = result.getInt("id");
                int accountId = result.getInt("accountId");
                int sectionId = result.getInt("sectionId");

                var account = accountSystem.getAccount(accountId);

                return new Student(
                        id,
                        result.getString("studentId"),
                        result.getString("course"),
                        accountId,
                        sectionId,
                        subjectSystem.getSubjects(id),
                        account.getFirstName(),
                        account.getMiddleName(),
                        account.getLastName(),
                        account.getGender(),
                        account.getBirthDate(),
                        account.getPhoneNumber(),
                        addressSystem.getAddress(accountId)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void add(Student student) {

        String query = "INSERT INTO " + table +
                " (studentId, course, accountId, sectionId) VALUES (?, ?, ?, ?)";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, student.getStudentId());
            command.setString(2, student.getCourse());
            command.setInt(3, student.getAccountId());
            command.setInt(4, student.getSectionId());

            command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int remove(Student student) {

        String query = "DELETE FROM " + table + " WHERE id = ?";

        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, student.getId());

            result = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int update(Student student) {

        String query = "UPDATE " + table +
                " SET studentId = ?, course = ?, accountId = ?, sectionId = ? WHERE id = ?";

        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, student.getStudentId());
            command.setString(2, student.getCourse());
            command.setInt(3, student.getAccountId());
            command.setInt(4, student.getSectionId());
            command.setInt(5, student.getId());

            result = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Student> getStudents(int sectionId) {

        List<Student> list = new ArrayList<>();

        String query = "SELECT * FROM " + table + " WHERE sectionId = ?";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, sectionId);

            ResultSet result = command.executeQuery();

            while (result.next()) {

                int id = result.getInt("id");
                int accountId = result.getInt("accountId");

                var account = accountSystem.getAccount(accountId);

                list.add(new Student(
                        id,
                        result.getString("studentId"),
                        result.getString("course"),
                        accountId,
                        sectionId,
                        subjectSystem.getSubjects(id),
                        account.getFirstName(),
                        account.getMiddleName(),
                        account.getLastName(),
                        account.getGender(),
                        account.getBirthDate(),
                        account.getPhoneNumber(),
                        addressSystem.getAddress(accountId)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}