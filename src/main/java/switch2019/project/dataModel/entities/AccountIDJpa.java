package switch2019.project.dataModel.entities;

import java.io.Serializable;
import java.util.Objects;

public class AccountIDJpa implements Serializable {

    private String denomination;
    private String owner;

    protected AccountIDJpa() {};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountIDJpa key = (AccountIDJpa) o;
        return Objects.equals(denomination, key.denomination)
                && Objects.equals(owner, key.owner);
    }

    public AccountIDJpa(String owner, String denomination) {
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
