package School.Model.Account;

import java.time.LocalDate;
import java.util.UUID;



public abstract class Account{
    protected enum Type {
        ADVISOR,
        TEACHER;
    }
    
    String tableName;
    private UUID accountId;//TODO: maybe change it later
    private Type type;

    public Account(UUID accountId, Type type) {
        this.accountId = accountId;
        this.type = type;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public Credential getCredential() {
    }

    public Type getType() {
        return type;
    }
}
