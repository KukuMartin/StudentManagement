package School.System.Attendance;

import School.Management.Attendance.DayManagement;
import School.Model.Attendance.Day;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class DaySystem {

    private DayManagement management;

    public DaySystem(Connection sql) {
        management = new DayManagement(sql);
    }

    public boolean createDay(Day day) {
        if (isDayInvalid(day)) {
            return false;
        }

        management.add(day);
        return true;
    }

    public boolean deleteDay(int id) {
        if (id <= 0) {
            return false;
        }

        int result = management.remove(new Day(id, null, null, 0));
        return result > 0;
    }

    public boolean updateDay(Day day) {
        if (isDayInvalid(day)) {
            return false;
        }

        int result = management.update(day);
        return result > 0;
    }

    public Day getDay(int id) {
        if (id <= 0) {
            return null;
        }

        return management.search(id);
    }

    public List<Day> getAllDays(int attendanceId) {
        if (attendanceId <= 0) {
            return List.of();
        }

        return management.getDays(attendanceId);
    }

    private boolean isDayInvalid(Day day) {
        if (day == null) return true;

        if (day.getId() <= 0) return true;
        if (day.getAttendanceId() <= 0) return true;
        if (day.getDate() == null) return true;
        if (day.getDate().isAfter(LocalDate.now())) return true;
        if (day.getState() == null) return true;

        return false;
    }
}