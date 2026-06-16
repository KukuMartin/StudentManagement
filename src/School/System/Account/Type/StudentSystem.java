package School.System.Account.Type;

import School.Management.Account.Type.StudentManagement;
import School.Model.Account.Type.Student;
import School.System.Account.AccountSystem;
import School.System.Subject.SubjectSystem;

import java.sql.Connection;
import java.util.List;

public class StudentSystem {

    private StudentManagement management;
    private SubjectSystem subjectSystem;
    private AccountSystem accountSystem;

    public StudentSystem(Connection sql) {

        this.accountSystem = new AccountSystem(sql);
        this.subjectSystem = new SubjectSystem(sql);

        this.management = new StudentManagement(
                sql,
                subjectSystem,
                accountSystem
        );
    }

    public boolean createStudent(Student student) {
        if (isStudentInvalid(student)) {
            return false;
        }

        int result = management.add(student);
        return result > 0;
    }

    public boolean deleteStudent(int id) {
        if (id <= 0) {
            return false;
        }
        
        int result = management.remove(id);
        return result > 0;
    }

    public boolean updateStudent(Student student) {
        if (isStudentInvalid(student)) {
            return false;
        }

        int result = management.update(student);
        return result > 0;
    }

    public Student getStudent(String username) {
        if (username == null) {
            return null;
        }

        return management.search(username);
    }

    public List<Student> getStudents(int sectionId) {
        if (sectionId <= 0) {
            return List.of();
        }

        return management.getStudents(sectionId);
    }

    public SubjectSystem getSubjectSystem() {
        return subjectSystem;
    }

    public boolean isStudentInvalid(Student student) {
        if (student == null) return true;

        if (student.getId() <= 0) return true;
        if (student.getStudentId() == null || student.getStudentId().isBlank()) return true;
        if (student.getCourse() == null || student.getCourse().isBlank()) return true;
        if (student.getAccountId() <= 0) return true;
        if (student.getSectionId() <= 0) return true;

        return false;
    }
}