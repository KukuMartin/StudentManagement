package School.System;

import School.Data.DatabaseTable;
import School.Management.SectionManagement;
import School.Model.Account.Section;
import java.lang.reflect.Field;
import java.util.List;

public class SectionSystem {

    private SectionManagement management;

    public SectionSystem(SectionManagement sectionManagement) {
        this.management = sectionManagement;
    }

    public void createSection(Section section) {
        if (DatabaseTable.hasNull(section)) {
            return;
        }
        management.add(section);
    }

    public void deleteSection(int id) {
        if (id <= 0 || management.search(id) == null) {
            return;
        }

        management.remove(id);
    }

    public Section getSection(int id) {
        if (id <= 0) {
            return null;
        }

        return management.search(id);
    }

    public List<Section> getAllSections() {
        return management.getAllSection();
    }
}