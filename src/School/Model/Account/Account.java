package School.Model.Account;

import java.time.LocalDate;

public class Account {

    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private String phoneNumber;
    private Address address;

    public Account(int id, String firstName, String middleName,
                   String lastName, String gender,
                   LocalDate birthDate, String phoneNumber,
                   Address address) {

        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getLastName() { return lastName; }
    public String getGender() { return gender; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getPhoneNumber() { return phoneNumber; }
    public Address getAddress() { return address; }

    public void update(Account account) {
        this.firstName = account.getFirstName();
        this.middleName = account.getMiddleName();
        this.lastName = account.getLastName();
        this.gender = account.getGender();
        this.birthDate = account.getBirthDate();
        this.phoneNumber = account.getPhoneNumber();
        this.address = account.getAddress();
    }
}