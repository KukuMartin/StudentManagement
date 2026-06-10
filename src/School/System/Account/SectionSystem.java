package School.System.Account;

import School.Management.Account.SectionManagement;
import School.Model.Account.Section;
import School.System.Account.Type.StudentSystem;

import java.sql.Connection;
import java.util.List;

public class SectionSystem {

    private SectionManagement management;
    private StudentSystem studentSystem;

    public SectionSystem(Connection sql) {
        studentSystem = new StudentSystem(sql);
        management = new SectionManagement(sql, studentSystem);
    }

    public boolean createSection(Section section) {
        if (isSectionInvalid(section)) {
            return false;
        }

        management.add(section);
        return true;
    }

    public boolean deleteSection(int id) {
        if (id <= 0) {
            return false;
        }

        Section temp = new Section(id, null, null, null);
        int result = management.remove(temp);
        return result > 0;
    }

    public boolean updateSection(Section section) {
        if (isSectionInvalid(section)) {
            return false;
        }

        int result = management.update(section);
        return result > 0;
    }

    public Section getSection(int id) {
        if (id <= 0) {
            return null;
        }

        return management.search(id);
    }

    public List<Section> getAllSections(int advisorId) {
        if (advisorId <= 0) {
            return List.of();
        }

        return management.getSections(advisorId);
    }

    public StudentSystem getStudentSystem() {
        return studentSystem;
    }

    private boolean isSectionInvalid(Section section) {
        if (section == null) return true;
        if (section.getId() <= 0) return true;
        if (section.getName() == null || section.getName().isBlank()) return true;
        if (section.getCode() == null || section.getCode().isBlank()) return true;
        return false;
    }
}