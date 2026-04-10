package School.Model.Account;

import java.time.LocalDate;
import java.util.UUID;



public abstract class Account{
    protected enum Type {
    ADVISOR,
    TEACHER;
    }
    
    private UUID accountId;
    private Credential credential;
    private Type type;

    public Account(UUID accountId, Credential credential, Type type) {
        this.accountId = accountId;
        this.credential = credential;
        this.type = type;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public Credential getCredential() {
        return credential;
    }

    public Type getType() {
        return type;
    }
}
