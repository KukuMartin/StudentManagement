package School.Data;

import java.lang.reflect.Field;

public class DatabaseTable {
    public static final String ACCOUNT = "Account";
    public static final String ADDRESS = "Address";
    public static final String ADMIN = "Admin";
    public static final String SECTION = "Section";
    public static final String ADVISOR = "Advisor";
    public static final String TEACHER = "Teacher";
    public static final String STUDENT = "Student";
    
    public static final String SUBJECT = "Subject";
    public static final String ASSESSMENT = "Assessment";
    public static final String ACTIVITY = "Activity";
    
    public static final String ATTENDANCE = "Attendance";
    public static final String DAY = "Day";
    
    public static boolean hasNull(Object object) {
        for(Field variable : object.getClass().getDeclaredFields()){
            variable.setAccessible(true);
            try {
                Object value = variable.get(object);
                if (value == null) {
                    return true;
                }
            } catch(IllegalAccessException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
