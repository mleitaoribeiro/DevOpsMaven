package switch2019.project.dataModel.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity(name ="accounts")
public class AccountJpa {

    @EmbeddedId
    private AccountIDJpa accountIDJpa;
    private String description;
    private Double amount;
    private String currency;

    protected AccountJpa() {};

    public AccountJpa(String owner, String denomination, String description, Double amount, String currency) {
        accountIDJpa = new AccountIDJpa(owner, denomination);
        this.description = description;
        this.amount = amount;
        this.currency = currency;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountJpa)) return false;
        AccountJpa that = (AccountJpa) o;
        return Objects.equals(accountIDJpa, that.accountIDJpa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountIDJpa);
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
