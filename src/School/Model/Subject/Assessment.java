package School.Model.Subject;

import School.Model.Subject.Period;
import java.util.ArrayList;
import java.util.List;

public class Assessment {
    private int id;
    private String name;
    private double percent;
    private int subjectId;
    private Period period;

    public Assessment(int id, String name, double percent, int subjectId, Period period) {
        this.id = id;
        this.name = name;
        this.percent = percent;
        this.subjectId = subjectId;
        this.period = period;
    }
    
    public Activity search(int activityId) {
        return null;
    }
    
    public void add(Activity activity) {
    }

    public void remove(Activity activity) {
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public double getPercent() {
        return percent;
    }
    
    public int getSubjectId() {
        return subjectId;
    }
    
    public int getPeriodId() {
        return 0;
    }

    public double getGrade() {
//        return (total / activities.size()) * percent;
        return 0;
    }
    
    public int getSize() {
        return 0;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}