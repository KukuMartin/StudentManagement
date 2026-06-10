package School.Model.Account;

public class Address {
    private int accountId;

    private String houseNumber;
    private String street;
    private String barangay;
    private String city;
    private String province;
    private String zipCode;
    

    public Address(int accountId, String houseNumber,
               String street, String barangay, String city,
               String province, String zipCode) {

        this.accountId = accountId;
        
        this.houseNumber = houseNumber;
        this.street = street;
        this.barangay = barangay;
        this.city = city;
        this.province = province;
        this.zipCode = zipCode;
        
        this.accountId = accountId;
    }

    public int getAccountId() { return accountId; }
    public String getHouseNumber() { return houseNumber; }
    public String getStreet() { return street; }
    public String getBarangay() { return barangay; }
    public String getCity() { return city; }
    public String getProvince() { return province; }
    public String getZipCode() { return zipCode; }
    
    public void update(Address address) {
        this.houseNumber = address.getHouseNumber();
        this.street = address.getStreet();
        this.barangay = address.getBarangay();
        this.city = address.getCity();
        this.province = address.getProvince();
        this.zipCode = address.getZipCode();
    }
}