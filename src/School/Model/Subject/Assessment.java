package School.Model.Subject;

import Tool.DoubleHolder;
import Tool.StringHolder;
import java.util.ArrayList;
import java.util.List;

public class Assessment {
    private List<Activity> activities;
    private StringHolder name;
    private DoubleHolder percent;

    public Assessment(List<Activity> activities, StringHolder name, DoubleHolder percent) {
        this.activities = activities;
        this.name = name;
        this.percent = percent;
    }
    
    public Activity search(String name) {
    }

    public Activity search(int index) {
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
    }

    public double getPercent() {
    }

    public double getGrade() {
    }
    
    public int getSize() {
    }
    
    public void setName(String name) {
    }

    public void setPercent(double percent) {
    }
}
