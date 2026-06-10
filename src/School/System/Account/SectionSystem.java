package School.System.Account;

import School.Data.DatabaseTable;
import School.Management.Account.SectionManagement;
import School.Model.Account.Section;
import java.lang.reflect.Field;
import java.util.List;

public class SectionSystem {

    private SectionManagement management;

    public SectionSystem(SectionManagement sectionManagement) {
        this.management = sectionManagement;
    }

    public boolean createSection(Section section) {
        if (this.isSectionValid(section)) {
            return false;
        }
        management.add(section);
        return true;
    }

    public boolean deleteSection(int id) {
        if(id <= 0){
            return false;
        }
        
        int result = management.remove(id);
        return result > 0;
    }
    
    public boolean updateSection(Section section){
        if(this.isSectionValid(section)){
            return false;
        }
        
        int result = management.update(section);
        return result > 0;
        
    }
    
    private boolean isSectionValid(Section section){
        if(section == null) return true;
        
        if(section.getId() <= 0) return true;
        else if(section.getName() == null || section.getName().isBlank()) return true;
        else if(section.getCode() == null || section.getCode().isBlank()) return true;
        
        return false;
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