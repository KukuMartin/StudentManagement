package School.Model.Subject;

import School.Model.Subject.Record.Name;
import School.Model.Subject.Record.Percentage;
import java.util.ArrayList;
import java.util.List;

public class Assessment {
    private List<Activity> activities;
    private Name name;
    private Percentage percent;

    public Assessment(List<Activity> activities, Name name, Percentage percent) {
        this.activities = activities;
        this.name = name;
        this.percent = percent;
    }
    
    public Activity search(String name) {
        return null;
    }

    public Activity search(int index) {
        return null;
    }
    
    public void add(Activity activity) {
    }

    public void add(Activity activity, int index) {
    }

    public void remove(Activity activity) {
    }
    
    public void remove(int index) {
    }
    
    public String getName() {
        return null;
    }

    public double getPercent() {
        return 0;
    }

    public double getGrade() {
        return 0;
    }
    
    public int getSize() {
        return 0;
    }
    
    public void setName(String name) {
    }

    public void setPercent(double percent) {
    }
}
