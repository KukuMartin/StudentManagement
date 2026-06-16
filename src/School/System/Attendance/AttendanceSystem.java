package School.System.Attendance;

import School.Management.Attendance.AttendanceManagement;
import School.Model.Attendance.Attendance;

import java.sql.Connection;
import java.util.List;

public class AttendanceSystem {

    private AttendanceManagement management;
    private DaySystem daySystem;

    public AttendanceSystem(Connection sql) {
        daySystem = new DaySystem(sql);
        management = new AttendanceManagement(sql, daySystem);
    }

    public boolean createAttendance(Attendance attendance) {
        if (isAttendanceInvalid(attendance)) {
            return false;
        }

        management.add(attendance);
        return true;
    }

    public boolean deleteAttendance(int id) {
        if (id <= 0) {
            return false;
        }

        int result = management.remove(id);
        return result > 0;
    }

    public boolean updateAttendance(Attendance attendance) {
        if (isAttendanceInvalid(attendance)) {
            return false;
        }

        int result = management.update(attendance);
        return result > 0;
    }

    public Attendance getAttendance(int id) {
        if (id <= 0) {
            return null;
        }

        return management.search(id);
    }

    public List<Attendance> getAllAttendances(int subjectId) {
        if (subjectId <= 0) {
            return List.of();
        }

        return management.getAttendances(subjectId);
    }
    
    public DaySystem getDaySystem() {
        return daySystem;
    }

    private boolean isAttendanceInvalid(Attendance attendance) {
        if (attendance == null) return true;

        if (attendance.getId() <= 0) return true;
        if (attendance.getSubjectId() <= 0) return true;
        if (attendance.getPeriod() == null) return true;

        return false;
    }
}