package School.Model.Subject;

import School.Model.Subject.Period;
import java.util.ArrayList;
import java.util.List;

public class Assessment {

    private int id;
    private String name;
    private double percent;
    private int recordId;
    private Period period;
    private List<Activity> activities;

    public Assessment(int id, String name, double percent, int recordId, Period period, List<Activity> activities) {
        this.id = id;
        this.name = name;
        this.percent = percent;
        this.recordId = recordId;
        this.period = period;
        this.activities = (activities != null) ? activities : new ArrayList<>();
    }

    public Activity search(int activityId) {
        for (Activity a : activities) {
            if (a.getId() == activityId) {
                return a;
            }
        }
        return null;
    }

    public void add(Activity activity) {
        activities.add(activity);
    }

    public void remove(Activity activity) {
        activities.remove(activity);
    }

    public double getGrade() {
        if (activities.isEmpty()) return 0.0;

        double total = 0;

        for (Activity a : activities) {
            total += a.getPercent();
        }

        return total * percent;
    }

    public int getSize() { return activities.size(); }

    public void update(Assessment assessment) {
        this.name = assessment.name;
        this.percent = assessment.percent;
        this.period = assessment.period;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPercent() { return percent; }
    public int getRecordId() { return recordId; }
    public Period getPeriod() { return period; }
    public List<Activity> getAllActivities() { return activities; }
}