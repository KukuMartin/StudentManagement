package School.System.Subject;

import School.Management.Subject.RecordManagement;
import School.Model.Subject.Record;
import School.System.Subject.AssessmentSystem;

import java.sql.Connection;
import java.util.List;

public class RecordSystem {

    private RecordManagement management;
    private AssessmentSystem assessmentSystem;

    public RecordSystem(Connection sql) {
        assessmentSystem = new AssessmentSystem(sql);
        management = new RecordManagement(sql, assessmentSystem);
    }

    public boolean createRecord(Record record) {
        if (this.isRecordInvalid(record)) {
            return false;
        }

        int result = management.add(record);
        return result > 0;
    }

    public boolean deleteRecord(int id) {
        if (id <= 0) {
            return false;
        }

        int result = management.remove(id);
        return result > 0;
    }

    public boolean updateRecord(Record record) {
        if (this.isRecordInvalid(record)) {
            return false;
        }

        int result = management.update(record);
        return result > 0;
    }

    public Record getRecord(int id) {
        if (id <= 0) {
            return null;
        }

        return management.search(id);
    }

    public List<Record> getRecords(int subjectId) {
        if (subjectId <= 0) {
            return List.of();
        }

        return management.getRecords(subjectId);
    }

    public AssessmentSystem getAssessmentSystem() {
        return assessmentSystem;
    }

    private boolean isRecordInvalid(Record record) {
        if (record == null) return true;

        if (record.getId() <= 0) return true;
        else if (record.getSubjectId() <= 0) return true;
        else if (record.getStudentId() <= 0) return true;

        return false;
    }
}