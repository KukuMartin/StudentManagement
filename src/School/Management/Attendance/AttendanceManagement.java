package School.Management.Attendance;

import School.Data.DatabaseTable;
import School.Model.Attendance.Attendance;
import School.Model.Subject.Period;
import School.System.Attendance.AttendanceSystem;
import School.System.Attendance.DaySystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceManagement {

    private String table = DatabaseTable.ATTENDANCE;
    private Connection sql;
    private DaySystem daySystem;

    public AttendanceManagement(Connection sql, DaySystem daySystem) {
        this.sql = sql;
        this.daySystem = daySystem;
    }

    public Attendance search(int id) {
        String query = "SELECT * FROM " + table + " WHERE id = ?";
        Attendance resultAttendance = null;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, id);

            ResultSet result = command.executeQuery();

            if (result.next()) {
                resultAttendance = new Attendance(
                        result.getInt("id"),
                        result.getInt("subjectId"),
                        Period.valueOf(result.getString("period")),
                        daySystem.getAllDays(id)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultAttendance;
    }

    public void add(Attendance attendance) {
        String query = "INSERT INTO " + table + " (subjectId, period) VALUES (?, ?)";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, attendance.getSubjectId());
            command.setString(2, attendance.getPeriod().name());

            command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int remove(Attendance attendance) {
        String query = "DELETE FROM " + table + " WHERE id = ?";
        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, attendance.getId());
            result = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int update(Attendance attendance) {
        String query = "UPDATE " + table + " SET period = ? WHERE id = ?";
        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, attendance.getPeriod().name());
            command.setInt(2, attendance.getId());

            result = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Attendance> getAttendances(int subjectId) {
        String query = "SELECT * FROM " + table + " WHERE subjectId = ?";
        List<Attendance> list = new ArrayList<>();

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, subjectId);

            ResultSet result = command.executeQuery();

            while (result.next()) {

                int id = result.getInt("id");

                list.add(new Attendance(
                        id,
                        result.getInt("subjectId"),
                        Period.valueOf(result.getString("period")),
                        daySystem.getAllDays(subjectId)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}