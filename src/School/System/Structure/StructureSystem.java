package School.System.Structure;

import School.System.AttendanceSystem;
import School.System.SubjectSystem;
import java.util.UUID;

public class StructureSystem {
    UUID sectionId;
    SubjectStructureSystem subjectManager;
    AttendanceStructureSystem attendanceManager;
    
    public SubjectSystem getSubjectSystem(String subjectId){
        return null;
        
    }
    
    public AttendanceSystem getAttendanceSystem(String subjectId, boolean midterms){
        return null;
        
    }
}
