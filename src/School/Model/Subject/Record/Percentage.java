package School.Model.Subject.Record;

public class Percentage {
    private double decimal;
    
    public Percentage(int decimal){
        this.decimal = decimal;
    }
    
    public double getDouble(){
        return decimal;
    }
    
    public void setDouble(double decimal){
        this.decimal = decimal;
    }
}
