package School.System.Subject;

import School.Management.Subject.ActivityManagement;
import School.Model.Subject.Activity;

import java.sql.Connection;
import java.util.List;

public class ActivitySystem {

    private ActivityManagement management;

    public ActivitySystem(Connection sql) {
        management = new ActivityManagement(sql);
    }

    public boolean createActivity(int assessmentId, Activity activity) {
        
        if (assessmentId <= 0) {
            return false;
        }

        if (this.isActivityInvalid(activity)) {
            return false;
        }

        int result = management.add(assessmentId, activity);
        return result > 0;
    }

    public boolean deleteActivity(int id) {
        if (id <= 0) {
           return false;
        }
        
        int result = management.remove(id);
        return result > 0;
    }

    public boolean updateActivity(Activity activity) {
        if (this.isActivityInvalid(activity)) {
            return false;
        }

        int result = management.update(activity);
        return result > 0;
    }

    public Activity getActivity(int id) {
        if (id <= 0) {
            return null;
        }

        return management.search(id);
    }

    public List<Activity> getActivities(int assessmentId) {
        if (assessmentId <= 0) {
            return List.of();
        }

        return management.getActivities(assessmentId);
    }

    private boolean isActivityInvalid(Activity activity) {
        if (activity == null) return true;

        if (activity.getId() <= 0) return true;
        else if (activity.getName() == null || activity.getName().isBlank()) return true;
        else if (activity.getTotalScore() <= 0) return true;
        else if (activity.getCurrentScore() < 0) return true;

        return false;
    }
}