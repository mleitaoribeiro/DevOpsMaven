package switch2019.project.dataModel.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AccountIDJpa implements Serializable {

    private String denomination;
    private String owner;

    protected AccountIDJpa() {};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountIDJpa key = (AccountIDJpa) o;
        return denomination.equalsIgnoreCase(key.denomination)
                && owner.equalsIgnoreCase(key.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, owner);
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
