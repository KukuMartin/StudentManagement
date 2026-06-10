package School.System;

import School.Model.Attendance.Attendance;
import School.Model.Attendance.Day;
import java.time.LocalDate;

public class AttendanceSystem {
    Attendance attendance;
    
    public AttendanceSystem(Attendance attendance){
        
    }
    
    public void add(Day day) {
    }

    public void add(Day day, int index) {
    }

    public void remove(Day day) {
    }

    public void remove(int index) {
    }

    public Day search(LocalDate date) {
        return null;
    }

    public Day search(int index) {
        return null;
    }
    
    public LocalDate getDate(){
        return null;
        
    }
    
    public Attendance.State getState(){
        
        return null;
    }
        
    public void setDate(LocalDate date){
        
    }
    
    public void setState(Attendance.State state){
        
    }
}
