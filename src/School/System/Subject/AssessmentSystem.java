package School.System.Subject;

import School.Management.Subject.AssessmentManagement;
import School.Model.Subject.Assessment;
import School.System.Subject.ActivitySystem;

import java.sql.Connection;
import java.util.List;

public class AssessmentSystem {

    private AssessmentManagement management;
    private ActivitySystem activitySystem;

    public AssessmentSystem(Connection sql) {
        activitySystem = new ActivitySystem(sql);
        management = new AssessmentManagement(sql, activitySystem);
    }

    public boolean createAssessment(Assessment assessment) {
        if (this.isAssessmentInvalid(assessment)) {
            return false;
        }

        management.add(assessment);
        return true;
    }

    public boolean deleteAssessment(Assessment assessment) {
        if (assessment == null || assessment.getId() <= 0) {
            return false;
        }

        int result = management.remove(assessment);
        return result > 0;
    }

    public boolean updateAssessment(Assessment assessment) {
        if (this.isAssessmentInvalid(assessment)) {
            return false;
        }

        int result = management.update(assessment);
        return result > 0;
    }

    public Assessment getAssessment(int id) {
        if (id <= 0) {
            return null;
        }

        return management.search(id);
    }

    public List<Assessment> getAssessments(int recordId) {
        if (recordId <= 0) {
            return List.of();
        }

        return management.getAssessments(recordId);
    }

    public boolean deleteActivity(int id) {
        if (id <= 0) {
            return false;
        }

        int result = management.removeActivity(id);
        return result > 0;
    }

    public ActivitySystem getActivitySystem() {
        return activitySystem;
    }

    private boolean isAssessmentInvalid(Assessment assessment) {
        if (assessment == null) return true;

        if (assessment.getId() <= 0) return true;
        else if (assessment.getName() == null || assessment.getName().isBlank()) return true;
        else if (assessment.getPercent() <= 0) return true;
        else if (assessment.getRecordId() <= 0) return true;
        else if (assessment.getPeriod() == null) return true;

        return false;
    }
}