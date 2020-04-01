package switch2019.project.domain.domainEntities.account;

import switch2019.project.domain.domainEntities.shared.AccountID;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.MonetaryValue;
import switch2019.project.domain.domainEntities.frameworks.Entity;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.shared.Description;

import java.util.Currency;
import java.util.Objects;

public class Account implements Entity {

    // Private Instance Variables

    private AccountID accountID;
    private Description description;
    private MonetaryValue balance;

    /**
     * Constructor of Account (to delete later)
     *
     * @param accountDenomination
     * @param accountDescription
     */

   /* public Account(Denomination accountDenomination, Description accountDescription) {
        this.denomination = accountDenomination;
        this.description = accountDescription;
        this.balance = new MonetaryValue(0.0, Currency.getInstance("EUR"));
    }*/

    /**
     * Constructor of Account
     *
     * @param accountDenomination
     * @param accountDescription
     * @param ownerID
     */

    public Account(Denomination accountDenomination, Description accountDescription, OwnerID ownerID) {
        if (accountDescription == null) throw new IllegalArgumentException("Account Description can't be null.");
        else {
            accountID = new AccountID(accountDenomination, ownerID);
            description = accountDescription;
            balance = new MonetaryValue(0.0, Currency.getInstance("EUR"));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account oneAccount = (Account) o;
        return Objects.equals(accountID, oneAccount.accountID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID);
    }

    @Override
    public String toString() {
        return description.toString() + ", " + balance.toString() + "€" + ", " + accountID.toString();
    }


    /**
     * Get accountID
     */
    public AccountID getID() {
        return accountID;
    }

    /**
     * Get account by Owner ID
     */

    public OwnerID getOwnerID() {
        return this.accountID.getOwnerID();
    }

    /**
     * Public get for denomination in String
     *
     * @return denominationString
     */

    public String denominationToString() {
        return this.getID().getDenomination();
    }

    /**
     * Public get for description in String
     *
     * @return descriptionString
     */
    public String descriptionToString() {
        return description.toString();
    }


    /**
     * Public get to access a clone of Account
     *
     * @return copy of Account
     */

    public Account getCopyOfAccount() {
        return new Account(new Denomination(accountID.getDenomination()), description, accountID.getOwnerID());
    }
}
