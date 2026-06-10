package School.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DatabaseSystem { //TODO change this so it validates if theres already a school db and redo sql again if ever
    private String database = "schoolDB";
    private String connection = "jdbc:mysql://localhost:3306/";
    private Connection sql;
    
    public DatabaseSystem() {
        try{
            sql = DriverManager.getConnection(connection, "root", "");
            
            String statement = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?";
            PreparedStatement command = sql.prepareStatement(statement);
            command.setString(1, database);
            
            ResultSet result = command.executeQuery();
            if(!result.next()){
                System.out.println("Databse not found: \nExecuting... " + database + ".sql");
                this.createDatabase();
            }
            
            sql.close();
            System.out.println("Connecting... " + database);
            sql = DriverManager.getConnection(connection + database, "root", "");
        } catch(SQLException e){
            System.out.println("Check your mySQL connections");
            e.printStackTrace();
        }
    }
    
    public void createDatabase() {
        try {
            InputStream input = DatabaseSystem.class.getResourceAsStream("/School/Data/" + database + ".sql");
            if(input == null){
                input = new FileInputStream("src/School/Data/schoolDB.sql");
            }
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder statement = new StringBuilder();
            String line = "";

            System.out.println("Reading lines...");
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if(line.isBlank()){ 
                    continue; 
                }

                statement.append(line).append(" ");
                if(line.endsWith(";")){
                    this.executeCommand(statement.substring(0, statement.length() - 1));
                    statement.setLength(0);
                    continue;
                }
            }
        } catch(IOException | NullPointerException e){ 
            e.printStackTrace(); 
        }
    }
    
    
    private void executeCommand(String statement) {
        System.out.println("Executing... " + statement);
        
        try(PreparedStatement command = sql.prepareStatement(statement);){
            command.execute();
        } catch(SQLException e){ e.printStackTrace(); }
    }
    
    public static void main(String[] args) throws SQLException {
        DatabaseSystem db = new DatabaseSystem();
    }
}