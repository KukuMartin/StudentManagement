package School.Management;

import School.Model.Account.Type.Student;
import School.Model.Account.Type.Teacher;
import School.System.Structure.StructureSystem;
import School.System.Structure.SubjectStructureSystem;
import java.util.List;
import java.util.UUID;

public class TeacherManagement {
    List<Teacher> teachers;
    
    public TeacherManagement(List<Teacher> teachers){
        this.teachers = teachers;
    }
    
    public Teacher searchTeacher(String username){
        
    }
    
    //subjects management because a teacher cant remove a class it should be an admin right?
}
