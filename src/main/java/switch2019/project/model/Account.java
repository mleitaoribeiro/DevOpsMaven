package switch2019.project.model;

import java.util.Objects;

public class Account {

    private String denomination;
    private String description;
    private double balance;

    public Account (String accountDenomination, String accountDescription) {
        setDenomination(accountDenomination);
        setDescription(accountDescription);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account oneAccount = (Account) o;
        return Objects.equals(this.denomination, oneAccount.denomination)
                && Objects.equals(this.description, oneAccount.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, description, balance);
    }

    /**
     * Public set for denomination: Can not be Null or numeric.
     * @param denomination
     */

    public void setDenomination (String denomination) {

    }

    /**
     * Public set for description: Can not be Null or numeric.
     * @param denomination
     */

    public void setDescription (String denomination) {

    }

}
