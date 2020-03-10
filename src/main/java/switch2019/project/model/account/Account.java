package switch2019.project.model.account;

import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.MonetaryValue;

import java.text.Normalizer;
import java.util.Currency;
import java.util.Objects;

public class Account {
    /**
     * Private Instance Variables
     */

    private Denomination denomination;
    private Description description;
    private MonetaryValue balance;

    /**
     * Constructor of Account
     *
     * @param accountDenomination
     * @param accountDescription
     */

    public Account(Denomination accountDenomination, Description accountDescription) {
        this.denomination = accountDenomination;
        this.description= accountDescription;
        this.balance = new MonetaryValue(0.0,Currency.getInstance("EUR"));
    }

    /**
     * override equals method of Acccount object
     *
     * @param o
     * @return
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account oneAccount = (Account) o;
        return Objects.equals(this.denomination, oneAccount.denomination);
    }

    /**
     * override hascode of Account object
     *
     * @return hashcode
     */

    @Override
    public int hashCode() {
        return Objects.hash(denomination, description, balance);
    }

    /**
     * Develop @override of toString()
     */

    @Override
    public String toString() {
        return denomination.toString() + ", " + description.toString() + ", " + balance + "€";
    }

    /**
     * Public get for denomination
     *
     * @return denomination
     */

    public String denominationToString() {
        return denomination.toString();
    }

    /**
     * Public set for denomination: Can not be Null.
     *
     * @param newDenomination
     */

    public void setDenomination(String newDenomination) {

        if (newDenomination == null || newDenomination.length() == 0) {
            throw new IllegalArgumentException("The denomination can´t be null or empty!");

        } else {
            this.denomination = new Denomination(newDenomination);
        }
    }

    /**
     * Public get for description
     *
     * @return descritpion
     */
    public String getDescription() {
        return description.getDescription();
    }

    /**
     * Public set for description: Can not be Null.
     *
     * @param description
     */

    public void setDescription(String description) {
        if (description == null || description.length() == 0) {
            throw new IllegalArgumentException("The description can´t be null or empty!");
        } else {
            this.description = new Description(description);
        }
    }

    /**
     * Public get to access a clone of Account
     *
     * @return copy of Account
     */

    public Account getCopyOfAccount() {
        return new Account(this.denomination, this.description);
    }
}
