package School.System.Account.Type;

import School.Management.Account.Type.TeacherManagement;
import School.Model.Account.Type.Teacher;
import School.System.Account.AccountSystem;
import School.System.Subject.SubjectSystem;

import java.sql.Connection;
import java.util.List;

public class TeacherSystem {

    private TeacherManagement management;
    private SubjectSystem subjectSystem;
    private AccountSystem accountSystem;

    public TeacherSystem(Connection sql) {

        this.accountSystem = new AccountSystem(sql);
        this.subjectSystem = new SubjectSystem(sql);

        this.management = new TeacherManagement(
                sql,
                subjectSystem,
                accountSystem
        );
    }

    public boolean createTeacher(Teacher teacher) {
        if (isTeacherInvalid(teacher)) {
            return false;
        }

        int result = management.add(teacher);
        return result > 0;
    }

    public boolean deleteTeacher(int id) {
        if (id <= 0) {
            return false;
        }

        Teacher temp = new Teacher(id, null, null, 0, null, null, null, null, null, null);
        int result = management.remove(id);
        return result > 0;
    }

    public boolean updateTeacher(Teacher teacher) {
        if (isTeacherInvalid(teacher)) {
            return false;
        }

        int result = management.update(teacher);
        return result > 0;
    }

    public Teacher getTeacher(String username) {
        if (username == null) {
            return null;
        }

        return management.search(username);
    }

    public List<Teacher> getAllTeachers() {
        return management.getTeachers();
    }

    public SubjectSystem getSubjectSystem() {
        return subjectSystem;
    }

    public boolean isTeacherInvalid(Teacher teacher) {
        if (teacher == null) return true;

        if (teacher.getId() <= 0) return true;
        if (teacher.getAccountId() <= 0) return true;
        if (teacher.getUsername() == null || teacher.getUsername().isBlank()) return true;
        if (teacher.getPassword() == null || teacher.getPassword().isBlank()) return true;

        return false;
    }
}