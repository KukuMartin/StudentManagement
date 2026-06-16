package School.Management.Subject;

import School.Data.DatabaseTable;
import School.Model.Subject.Activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityManagement {

    private final String table = DatabaseTable.ACTIVITY;
    private final Connection sql;

    private List<Activity> activities;

    public ActivityManagement(Connection sql) {
        this.sql = sql;
    }

    public Activity search(int id) {
        String query = "SELECT * FROM " + table + " WHERE id = ?";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, id);

            try (ResultSet result = command.executeQuery()) {

                if (result.next()) {

                    Activity activity = new Activity(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getInt("totalScore")
                    );

                    int currentScore = result.getInt("currentScore");

                    for (int i = 0; i < currentScore; i++) {
                        activity.update(new Activity(
                                activity.getId(),
                                activity.getName(),
                                activity.getTotalScore()
                        ));
                    }

                    return activity;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int add(int assessmentId, Activity activity) {
        int result = 0;
        String query = "INSERT INTO " + table + " (assessmentId, name, totalScore, currentScore) VALUES (?, ?, ?, ?)";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, assessmentId);
            command.setString(2, activity.getName());
            command.setInt(3, activity.getTotalScore());
            command.setInt(4, activity.getCurrentScore());

            command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
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

    public int update(Activity activity) {
        String query = "UPDATE " + table +
                " SET name = ?, totalScore = ?, currentScore = ? WHERE id = ?";

        int resultValue = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, activity.getName());
            command.setInt(2, activity.getTotalScore());
            command.setInt(3, activity.getCurrentScore());
            command.setInt(4, activity.getId());

            resultValue = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultValue;
    }

    public List<Activity> getActivities(int assessmentId) {
        String query = "SELECT * FROM " + table + " WHERE assessmentId = ?";
        activities = new ArrayList<>();

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, assessmentId);

            try (ResultSet result = command.executeQuery()) {

                while (result.next()) {

                    Activity activity = new Activity(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getInt("totalScore")
                    );

                    int currentScore = result.getInt("currentScore");

                    for (int i = 0; i < currentScore; i++) {
                        activity.update(new Activity(
                                activity.getId(),
                                activity.getName(),
                                activity.getTotalScore()
                        ));
                    }

                    activities.add(activity);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activities;
    }
}