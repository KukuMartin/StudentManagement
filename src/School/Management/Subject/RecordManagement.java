package School.Management.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import School.Data.DatabaseTable;
import School.Model.Subject.Record;
import School.System.Subject.AssessmentSystem;

public class RecordManagement {

    private final String table = DatabaseTable.RECORD;
    private final Connection sql;
    private final AssessmentSystem assessmentSystem;

    private List<Record> records;

    public RecordManagement(Connection sql, AssessmentSystem assessmentSystem) {
        this.sql = sql;
        this.assessmentSystem = assessmentSystem;
    }

    public Record search(int id) {
        String query = "SELECT * FROM " + table + " WHERE id = ?";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, id);

            try (ResultSet result = command.executeQuery()) {

                if (result.next()) {

                    return new Record(
                            result.getInt("id"),
                            result.getInt("subjectId"),
                            result.getInt("studentId"),
                            assessmentSystem.getAssessments(id)
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int add(Record record) {
        String query = "INSERT INTO " + table + " (subjectId, studentId) VALUES (?, ?)";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, record.getSubjectId());
            command.setInt(2, record.getStudentId());

            return command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int remove(Record record) {
        String query = "DELETE FROM " + table + " WHERE id = ?";
        int resultValue = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, record.getId());
            resultValue = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultValue;
    }

    public int update(Record record) {
        String query = "UPDATE " + table +
                " SET subjectId = ?, studentId = ? WHERE id = ?";

        int resultValue = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, record.getSubjectId());
            command.setInt(2, record.getStudentId());
            command.setInt(3, record.getId());

            resultValue = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultValue;
    }

    public List<Record> getRecords(int subjectId) {
        String query = "SELECT * FROM " + table + " WHERE subjectId = ?";
        records = new ArrayList<>();

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, subjectId);

            try (ResultSet result = command.executeQuery()) {

                while (result.next()) {

                    records.add(new Record(
                            result.getInt("id"),
                            result.getInt("subjectId"),
                            result.getInt("studentId"),
                            new ArrayList<>()
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }
}