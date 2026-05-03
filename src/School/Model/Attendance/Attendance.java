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
    }
    
    public UUID getAttendanceId(){
        
    }
    
    public UUID getSubjectId(){
        
    }
    
    public int getStateCount(Attendance.State type){
        
    }
    
    public int getSize() {
    }
    
    public int setStateCount(Attendance.State type, int value){
    }
}
