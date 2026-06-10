package School.System.Account;

import School.Management.Account.AddressManagement;
import School.Model.Account.Address;

import java.sql.Connection;

public class AddressSystem {

    private AddressManagement management;

    public AddressSystem(Connection sql) {
        management = new AddressManagement(sql);
    }

    public boolean createAddress(Address address) {
        if (isAddressInvalid(address)) {
            return false;
        }

        management.add(address);
        return true;
    }

    public boolean deleteAddress(int accountId) {
        if (accountId <= 0) {
            return false;
        }

        int result = management.remove(accountId);
        return result > 0;
    }

    public boolean updateAddress(Address address) {
        if (isAddressInvalid(address)) {
            return false;
        }

        int result = management.update(address);
        return result > 0;
    }

    public Address getAddress(int accountId) {
        if (accountId <= 0) {
            return null;
        }

        return management.search(accountId);
    }

    private boolean isAddressInvalid(Address address) {
        if (address == null) return true;

        if (address.getAccountId() <= 0) return true;
        if (address.getHouseNumber() == null || address.getHouseNumber().isBlank()) return true;
        if (address.getStreet() == null || address.getStreet().isBlank()) return true;
        if (address.getBarangay() == null || address.getBarangay().isBlank()) return true;
        if (address.getCity() == null || address.getCity().isBlank()) return true;
        if (address.getProvince() == null || address.getProvince().isBlank()) return true;
        if (address.getZipCode() == null || address.getZipCode().isBlank()) return true;

        return false;
    }
}