package School.System;

public class StudentSystem {
    
    private StudentSystem() {
    }
    
    public static StudentSystem getInstance() {
        return StudentSystemHolder.INSTANCE;
    }
    
    private static class StudentSystemHolder {
        private static final StudentSystem INSTANCE = new StudentSystem();
    }
}
