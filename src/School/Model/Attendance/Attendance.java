package School.Model.Attendance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Attendance {
    public enum State{
        PRESENT (0),
        EXCUSED (1),
        LATE (2),
        ABSENT (3);

        private int index;
        
        State(int index) {
            this.index = index;
        }
        
        public int getIndex(){
            return index;
        }
    }
    
    private UUID attendanceId;
    private UUID subjectId;
    private List<Day> attendance;
    private int[] stateCount = new int[State.values().length];
    
    public Attendance(List<Day> attendance) {
        this.attendance = attendance;
    }

    public void add(Day day) {
    }

    public void remove(Day day) {
    }

    public Day search(LocalDate date) {
        return null;
    }
    
    public UUID getAttendanceId(){
        return null;
        
    }
    
    public UUID getSubjectId(){
        return null;
        
    }
    
    public int getStateCount(Attendance.State type){
        return 0;
        
    }
    
    public int getSize() {
        return 0;
    }
    
    public int setStateCount(Attendance.State type, int value){
        return 0;
    }
}
