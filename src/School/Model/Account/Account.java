package School.Model.Account;

import java.time.LocalDate;

public class Account {

    private int id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private String phoneNumber;
    private String address;

    public Account(int id, String name, String gender,
                   LocalDate birthDate, String phoneNumber,
                   String address) {

        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }

    public void update(Account account) {
        this.name = account.getName();
        this.gender = account.getGender();
        this.birthDate = account.getBirthDate();
        this.phoneNumber = account.getPhoneNumber();
        this.address = account.getAddress();
    }
}