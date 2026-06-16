package School.Management.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import School.Data.DatabaseTable;
import School.Model.Subject.Subject;
import School.System.Subject.RecordSystem;

public class SubjectManagement {

    private final String table = DatabaseTable.SUBJECT;
    private final Connection sql;
    private RecordSystem recordSystem;

    private List<Subject> subjects;

    public SubjectManagement(Connection sql, RecordSystem recordSystem) {
        this.sql = sql;
        this.recordSystem = recordSystem;
    }

    public Subject search(int id) {
        String query = "SELECT * FROM " + table + " WHERE id = ?";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, id);

            try (ResultSet result = command.executeQuery()) {

                if (result.next()) {

                    return new Subject(
                            id,
                            result.getString("name"),
                            result.getString("code"),
                            result.getTime("scheduleStart").toLocalTime(),
                            result.getTime("scheduleEnd").toLocalTime(),
                            result.getInt("teacherId"),
                            recordSystem.getRecords(id)
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int add(Subject subject) {
        int result = 0;
        String query = "INSERT INTO " + table +
                " (teacherId, name, code, scheduleStart, scheduleEnd) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, subject.getTeacherId());
            command.setString(2, subject.getName());
            command.setString(3, subject.getCode());
            command.setTime(4, Time.valueOf(subject.getScheduleStart()));
            command.setTime(5, Time.valueOf(subject.getScheduleEnd()));

            return command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public int remove(int id) {
        String query = "DELETE FROM " + table + " WHERE id = ?";
        int resultValue = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1,id);
            resultValue = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultValue;
    }

    public int update(Subject subject) {
        String query = "UPDATE " + table +
                " SET teacherId = ?, name = ?, code = ?, scheduleStart = ?, scheduleEnd = ? WHERE id = ?";

        int resultValue = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, subject.getTeacherId());
            command.setString(2, subject.getName());
            command.setString(3, subject.getCode());
            command.setTime(4, Time.valueOf(subject.getScheduleStart()));
            command.setTime(5, Time.valueOf(subject.getScheduleEnd()));
            command.setInt(6, subject.getId());

            resultValue = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultValue;
    }

    public List<Subject> getSubjects(int teacherId) {
        String query = "SELECT * FROM " + table + " WHERE teacherId = ?";
        subjects = new ArrayList<>();

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, teacherId);

            try (ResultSet result = command.executeQuery()) {

                while (result.next()) {

                    int id = result.getInt("id");

                    subjects.add(new Subject(
                            id,
                            result.getString("name"),
                            result.getString("code"),
                            result.getTime("scheduleStart").toLocalTime(),
                            result.getTime("scheduleEnd").toLocalTime(),
                            result.getInt("teacherId"),
                            recordSystem.getRecords(id)
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
    }
}