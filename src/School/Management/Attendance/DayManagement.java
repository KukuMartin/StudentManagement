package School.Management.Attendance;

import School.Data.DatabaseTable;
import School.Model.Attendance.Day;
import School.Model.Attendance.Attendance.State;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DayManagement {

    private String table = DatabaseTable.DAY;
    private Connection sql;

    public DayManagement(Connection sql) {
        this.sql = sql;
    }

    public Day search(int id) {
        String query = "SELECT * FROM " + table + " WHERE id = ?";
        Day resultDay = null;

        try (PreparedStatement command = sql.prepareStatement(query)) {
            command.setInt(1, id);

            ResultSet result = command.executeQuery();

            if (result.next()) {
                resultDay = new Day(
                        result.getInt("id"),
                        result.getDate("date").toLocalDate(),
                        State.valueOf(result.getString("state")),
                        result.getInt("attendanceId")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultDay;
    }

    public void add(Day day) {
        String query = "INSERT INTO " + table + " (date, state, attendanceId) VALUES (?, ?, ?)";

        try (PreparedStatement command = sql.prepareStatement(query)) {
            command.setDate(1, Date.valueOf(day.getDate()));
            command.setString(2, day.getState().name());
            command.setInt(3, day.getAttendanceId());

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

    public int update(Day day) {
        String query = "UPDATE " + table + " SET date = ?, state = ?, attendanceId = ? WHERE id = ?";
        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {
            command.setDate(1, Date.valueOf(day.getDate()));
            command.setString(2, day.getState().name());
            command.setInt(3, day.getAttendanceId());
            command.setInt(4, day.getId());

            result = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Day> getDays(int attendanceId) {
        List<Day> list = new ArrayList<>();

        String query = "SELECT * FROM " + table + " WHERE attendanceId = ?";

        try (PreparedStatement command = sql.prepareStatement(query)) {
            command.setInt(1, attendanceId);

            ResultSet result = command.executeQuery();

            while (result.next()) {
                list.add(new Day(
                        result.getInt("id"),
                        result.getDate("date").toLocalDate(),
                        State.valueOf(result.getString("state")),
                        result.getInt("attendanceId")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public int addAll(List<Day> days) {
        String query = "INSERT INTO " + table + " (date, state, attendanceId) VALUES (?, ?, ?)";
        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            for (Day day : days) {
                command.setDate(1, Date.valueOf(day.getDate()));
                command.setString(2, day.getState().name());
                command.setInt(3, day.getAttendanceId());
                result += command.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int updateAll(List<Day> days) {
        String query = "UPDATE " + table + " SET date = ?, state = ? WHERE id = ?";
        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            for (Day day : days) {
                command.setDate(1, Date.valueOf(day.getDate()));
                command.setString(2, day.getState().name());
                command.setInt(3, day.getId());
                result += command.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int removeAll() {
        String query = "DELETE FROM " + table;
        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {
            result = command.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}