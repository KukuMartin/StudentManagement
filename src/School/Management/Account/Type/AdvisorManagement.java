package School.Management.Account.Type;

import School.Data.DatabaseTable;
import School.Model.Account.Type.Advisor;
import School.System.Account.AccountSystem;
import School.System.Account.AddressSystem;
import School.System.Account.SectionSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdvisorManagement {

    private String table = DatabaseTable.ADVISOR;
    private Connection sql;

    private SectionSystem sectionSystem;
    private AddressSystem addressSystem;
    private AccountSystem accountSystem;

    public AdvisorManagement(
            Connection sql,
            SectionSystem sectionSystem,
            AddressSystem addressSystem,
            AccountSystem accountSystem
    ) {
        this.sql = sql;
        this.sectionSystem = sectionSystem;
        this.addressSystem = addressSystem;
        this.accountSystem = accountSystem;
    }

    public Advisor search(String username) {

        String query = "SELECT * FROM " + table + " WHERE username = ?";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, username);

            ResultSet result = command.executeQuery();

            if (result.next()) {

                int id = result.getInt("id");
                int accountId = result.getInt("accountId");

                var account = accountSystem.getAccount(accountId);

                return new Advisor(
                        id,
                        result.getString("username"),
                        result.getString("password"),
                        accountId,
                        sectionSystem.getAllSections(id),

                        account.getFirstName(),
                        account.getMiddleName(),
                        account.getLastName(),
                        account.getGender(),
                        account.getBirthDate(),
                        account.getPhoneNumber(),

                        addressSystem.getAddress(accountId)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void add(Advisor advisor) {

        String query = "INSERT INTO " + table +
                " (username, password, accountId) VALUES (?, ?, ?)";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, advisor.getUsername());
            command.setString(2, advisor.getPassword());
            command.setInt(3, advisor.getAccountId());

            command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int remove(Advisor advisor) {

        String query = "DELETE FROM " + table + " WHERE id = ?";

        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, advisor.getId());

            result = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int update(Advisor advisor) {

        String query = "UPDATE " + table +
                " SET username = ?, password = ? WHERE id = ?";

        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, advisor.getUsername());
            command.setString(2, advisor.getPassword());
            command.setInt(3, advisor.getId());

            result = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Advisor> getAdvisors() {

        List<Advisor> list = new ArrayList<>();

        String query = "SELECT * FROM " + table;

        try (PreparedStatement command = sql.prepareStatement(query);
             ResultSet result = command.executeQuery()) {

            while (result.next()) {

                int id = result.getInt("id");
                int accountId = result.getInt("accountId");

                var account = accountSystem.getAccount(accountId);

                list.add(new Advisor(
                        id,
                        result.getString("username"),
                        result.getString("password"),
                        accountId,
                        sectionSystem.getAllSections(id),

                        account.getFirstName(),
                        account.getMiddleName(),
                        account.getLastName(),
                        account.getGender(),
                        account.getBirthDate(),
                        account.getPhoneNumber(),

                        addressSystem.getAddress(accountId)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}