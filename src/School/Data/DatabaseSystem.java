package School.Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DatabaseSystem {
    String loca
    public DatabaseSystem() {
        
    }
    
    private void executeCommand
    
    public void setUpDatabase() throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader("file.sql"))) {
            String sqlCommand = "";
            
            String line = "";
            while ((line = reader.readLine().trim()) != null) {
                int index = line.indexOf(";");
                
                if(index >= 0){
                    line = line.substring(index++);
                }
                sqlCommand += line;
            }
        }
    }
}