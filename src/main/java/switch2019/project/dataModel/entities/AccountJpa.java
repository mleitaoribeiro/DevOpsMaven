package switch2019.project.dataModel.entities;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.person.Person;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountJpa jpa = (AccountJpa) o;
        return Objects.equals(accountKeyJpa, jpa.accountKeyJpa);
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
