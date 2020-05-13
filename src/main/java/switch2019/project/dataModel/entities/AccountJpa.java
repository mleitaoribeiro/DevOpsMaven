package switch2019.project.dataModel.entities;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity(name ="accounts")
public class AccountJpa {

    @EmbeddedId
    private AccountKeyJpa accountKeyJpa;
    private String description;
    private String balance;

    protected AccountJpa() {};

    public AccountJpa(String owner, String denomination, String description, String balance) {
        accountKeyJpa = new AccountKeyJpa(owner, denomination);
        this.description = description;
        this.balance = balance;
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

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public AccountKeyJpa getAccountKeyJpa() { return accountKeyJpa; }
}
