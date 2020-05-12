package switch2019.project.dataModel.entities;

import java.io.Serializable;

public class AccountKeyJpa implements Serializable {

    private String denomination;
    private String owner;

    protected AccountKeyJpa() {};

    public AccountKeyJpa(String owner, String denomination) {
        this.owner = owner;
        this.denomination = denomination;
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
}
