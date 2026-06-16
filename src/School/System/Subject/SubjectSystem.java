package School.System.Subject;

import School.Management.Subject.SubjectManagement;
import School.Model.Subject.Subject;
import School.System.Subject.RecordSystem;

import java.sql.Connection;
import java.time.LocalTime;
import java.util.List;

public class SubjectSystem {

    private SubjectManagement management;
    private RecordSystem recordSystem;

    public SubjectSystem(Connection sql) {
        recordSystem = new RecordSystem(sql);
        management = new SubjectManagement(sql, recordSystem);
    }

    public boolean createSubject(Subject subject) {
        if (this.isSubjectInvalid(subject)) {
            return false;
        }

        int result = management.add(subject);
        return result > 0;
    }

    public boolean deleteSubject(int id) {
        if (id <= 0) {
            return false;
        }

        int result = management.remove(id);
        return result > 0;
    }

    public boolean updateSubject(Subject subject) {
        if (this.isSubjectInvalid(subject)) {
            return false;
        }

        int result = management.update(subject);
        return result > 0;
    }

    public Subject getSubject(int id) {
        if (id <= 0) {
            return null;
        }

        return management.search(id);
    }

    public List<Subject> getSubjects(int teacherId) {
        if (teacherId <= 0) {
            return List.of();
        }

        return management.getSubjects(teacherId);
    }

    public RecordSystem getRecordSystem() {
        return recordSystem;
    }

    private boolean isSubjectInvalid(Subject subject) {
        if (subject == null) return true;

        if (subject.getId() <= 0) return true;
        else if (subject.getTeacherId() <= 0) return true;
        else if (subject.getName() == null || subject.getName().isBlank()) return true;
        else if (subject.getCode() == null || subject.getCode().isBlank()) return true;
        else if (subject.getScheduleStart() == null) return true;
        else if (subject.getScheduleEnd() == null) return true;
        else if (!isTimeValid(subject.getScheduleStart(), subject.getScheduleEnd())) return true;

        return false;
    }

    private boolean isTimeValid(LocalTime start, LocalTime end) {
        return start.isBefore(end);
    }
}