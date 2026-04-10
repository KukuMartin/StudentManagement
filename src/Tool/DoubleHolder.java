package Tool;

public class DoubleHolder {
    private double decimal;
    
    public DoubleHolder(int decimal){
        this.decimal = decimal;
    }
    
    public double getDouble(){
        return decimal;
    }
    
    public void setDouble(double decimal){
        this.decimal = decimal;
    }
}
