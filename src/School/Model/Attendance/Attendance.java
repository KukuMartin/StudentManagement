package School.Model.Attendance;

import java.time.LocalDate;
import java.time.Period;
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
    
    private int id;
    private int subjectId;
    private Period period;
    
    public Attendance(int id, int subjectId, Period period) {
        this.id = id;
        this.subjectId = subjectId;
        this.period = period;
    }
    
    public Day search(LocalDate date) {
        return null;
    }

    public void add(Day day) {
    }

    public void remove(Day day) {
    }

    public int getId(){
        return id;
    }
    
    public int getSubjectId(){
        return subjectId;
    }
    
    public Period getPeriod(){
        return period;
    }
    
    public int getSize() {
        return 0;
    }
    
    public int getStateSize(Attendance.State type){
        return 0;
        
    }
}
