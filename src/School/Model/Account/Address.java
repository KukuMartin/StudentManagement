package School.Model.Account;

public class Address {
    private int accountId;

    private String houseNumber;
    private String street;
    private String barangay;
    private String city;
    private String province;
    private String zipCode;

    public Address(int accountId) {
        this.accountId = accountId;
    }

    public String getHouseNumber() { return houseNumber; }
    public String getStreet() { return street; }
    public String getBarangay() { return barangay; }
    public String getCity() { return city; }
    public String getProvince() { return province; }
    public String getZipCode() { return zipCode; }
    public int getAccountId() { return accountId; }

    public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }
    public void setStreet(String street) { this.street = street; }
    public void setBarangay(String barangay) { this.barangay = barangay; }
    public void setCity(String city) { this.city = city; }
    public void setProvince(String province) { this.province = province; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
}