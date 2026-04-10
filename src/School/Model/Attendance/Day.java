package School.Model.Attendance;

import java.time.LocalDate;

public class Day {
    private LocalDate date;
    private Attendance.State state;
    
    Day(LocalDate date, Attendance.State state){
        this.date = date;
        this.state = state;
    }
    
    public LocalDate getDate(){
        return date;
    }
    
    public Attendance.State getState(){
        return state;
    }
    
    public void setDate(LocalDate date){
        this.date = date;
    }
    
    public void setState(Attendance.State state){
        this.state = state;
    }
}
