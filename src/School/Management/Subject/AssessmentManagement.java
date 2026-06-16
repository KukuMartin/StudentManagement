package School.Management.Subject;

import School.Model.Subject.Assessment;
import School.Model.Subject.Activity;
import School.Model.Subject.Period;
import School.System.Subject.ActivitySystem;
import School.Data.DatabaseTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class AssessmentManagement {

    private final String table = DatabaseTable.ASSESSMENT;
    private final Connection sql;
    private ActivitySystem activitySystem;

    private List<Assessment> assessments;

    public AssessmentManagement(Connection sql, ActivitySystem activitySystem) {
        this.sql = sql;
        this.activitySystem = activitySystem;
    }

    public Assessment search(int id) {
        String query = "SELECT * FROM " + table + " WHERE id = ?";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, id);

            try (ResultSet result = command.executeQuery()) {

                if (result.next()) {

                    int assessmentId = result.getInt("id");

                    return new Assessment(
                            assessmentId,
                            result.getString("name"),
                            result.getDouble("percent"),
                            result.getInt("recordId"),
                            Period.valueOf(result.getString("period")),
                            activitySystem.getActivities(assessmentId)
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void add(Assessment assessment) {
        String query = "INSERT INTO " + table +
                " (name, percent, recordId, period) VALUES (?, ?, ?, ?)";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, assessment.getName());
            command.setDouble(2, assessment.getPercent());
            command.setInt(3, assessment.getRecordId());
            command.setString(4, assessment.getPeriod().name());

            command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int remove(int id) {
        String query = "DELETE FROM " + table + " WHERE id = ?";
        int resultValue = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1,id);
            resultValue = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultValue;
    }

    public int update(Assessment assessment) {
        String query = "UPDATE " + table +
                " SET name = ?, percent = ?, period = ? WHERE id = ?";

        int resultValue = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, assessment.getName());
            command.setDouble(2, assessment.getPercent());
            command.setString(3, assessment.getPeriod().name());
            command.setInt(4, assessment.getId());

            resultValue = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultValue;
    }

    public List<Assessment> getAssessments(int recordId) {
        String query = "SELECT * FROM " + table + " WHERE recordId = ?";
        assessments = new ArrayList<>();

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, recordId);

            try (ResultSet result = command.executeQuery()) {

                while (result.next()) {

                    int assessmentId = result.getInt("id");

                    assessments.add(new Assessment(
                            assessmentId,
                            result.getString("name"),
                            result.getDouble("percent"),
                            result.getInt("recordId"),
                            Period.valueOf(result.getString("period")),
                            activitySystem.getActivities(assessmentId)
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return assessments;
    }

    public int removeActivity(int id) {
        String query = "DELETE FROM Activity WHERE id = ?";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, id);
            return command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}