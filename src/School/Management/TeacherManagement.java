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
    
    public Teacher searchTeacher(int index){
        
    }
    
    public void addTeacher(Teacher teacher){
        
    }
    
    public void addTeacher(Teacher teacher, int index){
        
    }
    
    public void removeTeacher(Teacher teacher){
        
    }
    
    public void removeTeacher(int index){
        
    }
    
    //subjects management because a teacher cant remove a class it should be an admin right?
    public void addStructure(StructureSystem system){
        
    }
    
    public void addStructure(StructureSystem system, int index){
        
    }
    
    public void removeStructure(StructureSystem system){
        
    }
    
    public void removeStructure(int index){
        
    }
}
