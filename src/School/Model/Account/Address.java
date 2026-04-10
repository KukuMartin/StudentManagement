package School.Model.Account;

public class Address {
    private String houseNumber;
    private String street;
    private String barangay;
    private String city;
    private String province;
    private String zipCode;
    private String country;

    public Address(String houseNumber, String street, String barangay, 
                   String city, String province, String zipCode, 
                   String country){
        this.houseNumber = houseNumber;
        this.street = street;
        this.barangay = barangay;
        this.city = city;
        this.province = province;
        this.zipCode = zipCode;
        this.country = country;
    }
    
    public String getHouseNumber(){
        return houseNumber;
    } 
    
    public String getStreet(){
        return street;
    }
    
    public String getBarangay(){
        return barangay;
    }
    
    public String getCity(){
        return city;
    }
    
    public String getProvince(){
        return province;
    }
    
    public String getZipCode(){
        return zipCode;
    }
    
    public String getCountry(){
        return country;
    }
    
    public void setHouseNumber(String houseNumber){
        this.houseNumber = houseNumber;
    } 
    
    public void setStreet(String street){
        this.street = street;
    }
    
    public void setBarangay(String barangay){
        this.barangay = barangay;
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
    public void setProvince(String province){
        this.province = province;
    }
    
    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }
    
    public void setCountry(String country){
        this.country = country;
    }
}
