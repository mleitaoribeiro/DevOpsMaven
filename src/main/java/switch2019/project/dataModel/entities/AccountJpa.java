package switch2019.project.dataModel.entities;
import javax.persistence.*;
import java.util.Objects;

@Entity(name ="accounts")
public class AccountJpa {

    @EmbeddedId
    private AccountIDJpa accountIDJpa;
    private String description;
    private String balance;

    protected AccountJpa() {};

    public AccountJpa(String owner, String denomination, String description, String balance) {
        accountIDJpa = new AccountIDJpa(owner, denomination);
        this.description = description;
        this.balance = balance;
    }

    public AccountIDJpa getAccountIDJpa() {
        return accountIDJpa;
    }

    public void setAccountIDJpa(AccountIDJpa accountIDJpa) {
        this.accountIDJpa = accountIDJpa;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBalance() {
        return balance;
    }

    public String getAmount() {
        return balance.split(" ")[0];
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public AccountIDJpa getAccountKeyJpa() { return accountIDJpa; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountJpa)) return false;
        AccountJpa that = (AccountJpa) o;
        return Objects.equals(accountIDJpa, that.accountIDJpa) &&
                Objects.equals(description, that.description) &&
                Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountIDJpa, description, balance);
    }
}
