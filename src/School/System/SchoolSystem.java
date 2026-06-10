package School.System;

import java.sql.Connection;

import School.Data.DatabaseSystem;
import School.System.Account.Type.AdminSystem;
import School.System.Account.Type.AdvisorSystem;
import School.System.Account.Type.TeacherSystem;

public class SchoolSystem {
    public static SchoolSystem getInstance() {
        return SchoolSystemHolder.INSTANCE;
    }
    
    private static class SchoolSystemHolder {
        private static final SchoolSystem INSTANCE = new SchoolSystem();
    }
    
    private AdminSystem admin;
    private AdvisorSystem advisor;
    private TeacherSystem teacher;
    
    private SchoolSystem() {
        DatabaseSystem databaseSystem = new DatabaseSystem();
        
        Connection sql = databaseSystem.getConnection();
        admin = new AdminSystem(sql);
        advisor = new AdvisorSystem(sql);
        teacher = new TeacherSystem(sql);
    }
    
    public AdminSystem getAdminSystem(){ return admin; }
    public AdvisorSystem getAdvisorSystem(){ return advisor; }
    public TeacherSystem getTeacherSystem(){ return teacher; }
}
