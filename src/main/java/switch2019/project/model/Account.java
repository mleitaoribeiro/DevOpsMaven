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
     * Public set for denomination: Can not be Null.
     * @param denomination
     */

    public void setDenomination (String denomination) {
        if (denomination == null) {
            throw new IllegalArgumentException("The denomination can´t be null. Please try again.");
        } else {
            this.denomination = denomination.toUpperCase();
        }
    }

    /**
     * Public get for denomination
     */

    public String getDenomination (){
        return this.denomination;
    }

    /**
     * Public set for description: Can not be Null.
     * @param description
     */

    public void setDescription (String description) {
        if (description == null) {
            throw new IllegalArgumentException("The description can´t be null. Please try again.");
        } else {
            this.description = description.toUpperCase();
        }
    }

    /**
     * Public get for description
     */

    public String getDescription (){
        return this.description;
    }

}
