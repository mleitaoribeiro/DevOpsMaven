package switch2019.project.dataModel.entities;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity(name ="accounts")
public class AccountJpa {

    @Id
    private String denomination;
    // @Id
    private String owner;
    private String description;
    private String balance;

    protected AccountJpa() {};

    public AccountJpa(String owner, String denomination, String description, String balance) {
        this.owner = owner;
        this.denomination = denomination;
        this.description = description;
        this.balance = balance;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
}
