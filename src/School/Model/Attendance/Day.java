package School.Model.Attendance;

import java.time.LocalDate;

public class Day {

    private int id;
    private LocalDate date;
    private Attendance.State state;
    private int attendanceId;

    public Day(int id, LocalDate date, Attendance.State state, int attendanceId) {
        this.id = id;
        this.date = date;
        this.state = state;
        this.attendanceId = attendanceId;
    }

    public int getId() { return id; }
    public LocalDate getDate() { return date; }
    public Attendance.State getState() { return state; }
    public int getAttendanceId() { return attendanceId; }

    public void setDate(LocalDate date) { this.date = date; }
    public void setState(Attendance.State state) { this.state = state; }
}