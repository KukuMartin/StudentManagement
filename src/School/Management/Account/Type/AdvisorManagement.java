package School.Management.Account.Type;

import School.Data.DatabaseTable;
import School.Model.Account.Type.Advisor;
import School.System.Account.AccountSystem;
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
    private AccountSystem accountSystem;

    public AdvisorManagement(
            Connection sql,
            SectionSystem sectionSystem,
            AccountSystem accountSystem
    ) {
        this.sql = sql;
        this.sectionSystem = sectionSystem;
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
                        account.getAddress()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int add(Advisor advisor) {
        int result = 0;
        String query = "INSERT INTO " + table +
                " (username, password, accountId) VALUES (?, ?, ?)";

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setString(1, advisor.getUsername());
            command.setString(2, advisor.getPassword());
            command.setInt(3, advisor.getAccountId());

            result = command.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int remove(int id) {

        String query = "DELETE FROM " + table + " WHERE id = ?";

        int result = 0;

        try (PreparedStatement command = sql.prepareStatement(query)) {

            command.setInt(1, id);

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
                        account.getAddress()
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}